package com.github.pinkolik.general_classes_generator.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public final class GeneratorUtil {

    public static final String MAPPER_POSTFIX = "Mapper";

    private GeneratorUtil() {}

    public static String convertPathToPackageName(final String absolutePath) {
        return Paths.get(absolutePath).toString() //system independent
                    .replaceFirst(".*?java.", "") //replace everything up until package
                    .replace(File.separator, "."); //replace separators with dots
    }

    private static Map<ClassInfo, Set<Class<?>>> getClassInfosByName(final String className, final Pattern versionRegexPattern)
            throws ClassNotFoundException {
        Map<ClassInfo, Set<Class<?>>> result = new LinkedHashMap<>();
        Class<?> aClass = Class.forName(className);
        String generalClassName = aClass.getName();
        Matcher matcher = versionRegexPattern.matcher(generalClassName);
        if (matcher.find()) {
            String version = matcher.group(0);
            generalClassName = generalClassName.replace("." + version, "");
        }
        else {
            throw new IllegalArgumentException(
                    String.format("Class name %s doesn't contain version pattern \"%s\"", generalClassName, versionRegexPattern));
        }
        ClassInfo generalClassInfo = new ClassInfo(generalClassName, aClass.isEnum(), aClass.isMemberClass(),
                                                   Modifier.isStatic(aClass.getModifiers()));
        Set<Class<?>> versions = result.computeIfAbsent(generalClassInfo, key -> new HashSet<>());
        versions.add(aClass);
        for (Class<?> declaredClass : aClass.getDeclaredClasses()) {
            result.putAll(getClassInfosByName(declaredClass.getName(), versionRegexPattern));
        }
        return result;
    }

    public static String convertPathToClassToClassName(final String absolutePath) {
        return Paths.get(absolutePath).toString() //system independent
                    .replaceFirst(".*?java.", "") //replace everything up until package
                    .replaceFirst("\\.java", "") //replace file extension
                    .replace(File.separator, "."); //replace separators with dots
    }

    public static Map<ClassInfo, Set<Class<?>>> buildGeneralClassInfoToVersionClassesMap(final String versionClassesBasePath,
                                                                                         final Pattern versionRegexPattern) {
        Map<ClassInfo, Set<Class<?>>> result = new LinkedHashMap<>();
        Collection<File> files = FileUtils.listFiles(new File(versionClassesBasePath), new String[] {"java"}, true);
        for (File file : files) {
            String absolutePath = file.getAbsolutePath();
            String className = convertPathToClassToClassName(absolutePath);
            try {
                Map<ClassInfo, Set<Class<?>>> generalClassInfos = getClassInfosByName(className, versionRegexPattern);
                generalClassInfos.forEach((classInfo, classes) -> {
                    Set<Class<?>> versions = result.computeIfAbsent(classInfo, key -> new HashSet<>());
                    versions.addAll(classes);
                });
            }
            catch (ClassNotFoundException e) {
                log.error("Class not found: ", e);
            }
        }
        return result;
    }

    private static List<Field> getAllFields(final Class<?> aClass) {
        List<Field> fields = new ArrayList<>(Arrays.asList(aClass.getDeclaredFields()));
        if (aClass.getSuperclass() != null) {
            fields.addAll(getAllFields(aClass.getSuperclass()));
        }
        return fields;
    }

    private static Set<FieldInfo> getFieldInfosForClass(final Pattern versionRegexPattern, final Class<?> aClass)
            throws IllegalAccessException {
        List<Field> fields = getAllFields(aClass);
        Set<FieldInfo> fieldInfos = new HashSet<>();
        for (Field field : fields) {
            if (aClass.isEnum() && !field.isEnumConstant()) {
                continue;
            }
            Type type = field.getGenericType();
            String genericTypeName;
            if (type instanceof Class) {
                genericTypeName = ((Class<?>) type).getCanonicalName();
            }
            else if (type instanceof ParameterizedType) {
                genericTypeName = type.getTypeName().replace("$", ".");
            }
            else {
                genericTypeName = field.getGenericType().getTypeName();
            }
            Matcher versionMatcher = versionRegexPattern.matcher(genericTypeName);
            if (versionMatcher.find()) {
                String version = versionMatcher.group(0);
                genericTypeName = genericTypeName.replace("." + version, "");
            }
            boolean isStatic = Modifier.isStatic(field.getModifiers());
            boolean isFinal = Modifier.isFinal(field.getModifiers());
            Object staticFinalValue = null;
            if (isFinal && isStatic) {
                field.setAccessible(true);
                staticFinalValue = field.get(null);
            }
            FieldInfo fieldInfo =
                    new FieldInfo(field.getName(), genericTypeName, isStatic, isFinal, aClass.isEnum(), staticFinalValue);
            fieldInfos.add(fieldInfo);
        }
        return fieldInfos;
    }

    public static Map<ClassInfo, Set<FieldInfo>> buildGeneralClassNameToFieldInfoMap(final String versionClassesBasePath,
                                                                                     final Pattern versionRegexPattern)
            throws IllegalAccessException {
        Map<ClassInfo, Set<Class<?>>> generalClassInfoToVersionClassesMap =
                buildGeneralClassInfoToVersionClassesMap(versionClassesBasePath, versionRegexPattern);
        Map<ClassInfo, Set<FieldInfo>> result = new LinkedHashMap<>();
        for (Map.Entry<ClassInfo, Set<Class<?>>> entry : generalClassInfoToVersionClassesMap.entrySet()) {
            ClassInfo generalClassInfo = entry.getKey();
            Set<Class<?>> classes = entry.getValue();
            for (Class<?> aClass : classes) {
                Set<FieldInfo> fieldInfos = result.computeIfAbsent(generalClassInfo, key -> new HashSet<>());
                fieldInfos.addAll(getFieldInfosForClass(versionRegexPattern, aClass));
            }
        }
        return result;
    }

    public static Map<String, Set<ConverterInfo>> buildVersionToConverterInfoMap(final String versionClassesBasePath,
                                                                                 final Pattern versionRegexPattern,
                                                                                 final String mappersBasePath) {
        Map<ClassInfo, Set<Class<?>>> generalClassInfoToVersionClassesMap =
                buildGeneralClassInfoToVersionClassesMap(versionClassesBasePath, versionRegexPattern);
        Collection<File> mapperFiles = FileUtils.listFiles(new File(mappersBasePath), new String[] {"java"}, true);
        Map<String, Set<ConverterInfo>> result = new TreeMap<>();
        for (Map.Entry<ClassInfo, Set<Class<?>>> entry : generalClassInfoToVersionClassesMap.entrySet()) {
            ClassInfo generalClassInfo = entry.getKey();
            if (generalClassInfo.isMember()) {
                continue;
            }
            String generalClassName = generalClassInfo.getName();
            Set<Class<?>> classes = entry.getValue();
            for (Class<?> aClass : classes) {
                Matcher versionMatcher = versionRegexPattern.matcher(aClass.getCanonicalName());
                String version;
                if (versionMatcher.find()) {
                    version = versionMatcher.group(0);
                }
                else {
                    throw new IllegalArgumentException("Wrong version regex.");
                }
                String mapperSimpleName = aClass.getSimpleName() + MAPPER_POSTFIX;
                //@formatter:off
                String mapperClassName = mapperFiles.stream()
                                              .filter(f ->
                                                              f.getName().equals(mapperSimpleName + ".java") &&
                                                                      f.getAbsolutePath().contains(version))
                                              .map(file -> convertPathToClassToClassName(file.getAbsolutePath()))
                                              .findFirst()
                                              .orElseThrow(
                                () -> new IllegalArgumentException("Couldn't find mapper " + mapperSimpleName));
                //@formatter:on
                Set<ConverterInfo> converterInfoSet = result.computeIfAbsent(StringUtils.capitalize(version),
                                                                             key -> new TreeSet<>(Comparator.comparing(
                                                                                     ConverterInfo::getGeneralClassName)));
                converterInfoSet.add(new ConverterInfo(generalClassName, aClass.getCanonicalName(), mapperClassName));
            }
        }
        return result;
    }
}
