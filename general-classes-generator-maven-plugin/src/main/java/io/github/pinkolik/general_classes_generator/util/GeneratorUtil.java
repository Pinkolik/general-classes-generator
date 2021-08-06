package io.github.pinkolik.general_classes_generator.util;

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

    public static boolean needInclude(final Set<Pattern> includeClassesRegex, final ClassInfo generalClassInfo) {
        if (includeClassesRegex == null || includeClassesRegex.isEmpty()) {
            return true;
        }
        return includeClassesRegex.stream().anyMatch(pattern -> pattern.matcher(generalClassInfo.getName()).matches());
    }

    public static String convertPathToPackageName(final String absolutePath) {
        return Paths.get(absolutePath).toString() //system independent
                    .replaceFirst(".*?java.", "") //replace everything up until package
                    .replace(File.separator, "."); //replace separators with dots
    }

    public static Optional<String> extractVersionStringFromClassName(final Pattern versionRegexPattern, final String className) {
        Matcher matcher = versionRegexPattern.matcher(className);
        if (matcher.find()) {
            return Optional.of(matcher.group(0));
        }
        return Optional.empty();
    }

    public static String extractVersionStringFromClassNameOrThrow(final Pattern versionRegexPattern, final String className) {
        return extractVersionStringFromClassName(versionRegexPattern, className).orElseThrow(() -> new IllegalArgumentException(
                String.format("Class name %s doesn't contain version pattern \"%s\"", className, versionRegexPattern)));
    }

    private static String convertClassToGeneralClassName(final Pattern versionRegexPattern, final Class<?> aClass) {
        String versionString = extractVersionStringFromClassNameOrThrow(versionRegexPattern, aClass.getName());
        return aClass.getName().replace("." + versionString, "");
    }

    private static Map<ClassInfo, Set<Class<?>>> getClassInfosByName(final String className, final Pattern versionRegexPattern)
            throws ClassNotFoundException {
        Map<ClassInfo, Set<Class<?>>> result = new LinkedHashMap<>();
        Class<?> aClass = Class.forName(className);
        String generalClassName = convertClassToGeneralClassName(versionRegexPattern, aClass);
        String superclassGeneralClassName = null;
        Class<?> superclass = aClass.getSuperclass();
        if (!superclass.isAssignableFrom(Object.class) && !superclass.isAssignableFrom(Enum.class)) {
            superclassGeneralClassName = convertClassToGeneralClassName(versionRegexPattern, superclass);
        }
        ClassInfo generalClassInfo =
                new ClassInfo(generalClassName, aClass.isEnum(), aClass.isMemberClass(), Modifier.isStatic(aClass.getModifiers()),
                              superclassGeneralClassName);
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

    public static String getGeneralTypeName(final Pattern versionRegexPattern, final Field field, final Type type) {
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
        String finalGenericTypeName = genericTypeName;
        genericTypeName = extractVersionStringFromClassName(versionRegexPattern, genericTypeName).map(
                versionString -> finalGenericTypeName.replace("." + versionString, "")).orElse(genericTypeName);
        return genericTypeName;
    }

    private static Set<FieldInfo> getFieldInfosForClass(final Pattern versionRegexPattern, final Class<?> aClass)
            throws IllegalAccessException {
        Field[] fields = aClass.getDeclaredFields();
        Set<FieldInfo> fieldInfos = new HashSet<>();
        for (Field field : fields) {
            if (field.isSynthetic()) {
                continue;
            }
            Type type = field.getGenericType();
            String genericTypeName = getGeneralTypeName(versionRegexPattern, field, type);
            boolean isStatic = Modifier.isStatic(field.getModifiers());
            boolean isFinal = Modifier.isFinal(field.getModifiers());
            boolean isEnumConstant = field.isEnumConstant();
            Object staticFinalValue = null;
            String fieldName = field.getName();
            if (isFinal && isStatic && !isEnumConstant) {
                field.setAccessible(true);
                String versionString = extractVersionStringFromClassNameOrThrow(versionRegexPattern, aClass.getName());
                fieldName = fieldName + "_" + versionString;
                staticFinalValue = field.get(null);
            }
            FieldInfo fieldInfo = new FieldInfo(fieldName, genericTypeName, isStatic, isFinal, isEnumConstant, staticFinalValue);
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

    public static String getMapperName(final Class<?> aClass) {
        String mapperName = aClass.getName().replace("$", "_") + GeneratorUtil.MAPPER_POSTFIX;
        return mapperName.substring(mapperName.lastIndexOf(".") + 1);
    }

    public static Map<String, Set<ConverterInfo>> buildVersionToConverterInfoMap(final String versionClassesBasePath,
                                                                                 final Pattern versionRegexPattern,
                                                                                 final String mappersBasePath,
                                                                                 final Set<Pattern> includeClassesRegex) {
        Map<ClassInfo, Set<Class<?>>> generalClassInfoToVersionClassesMap =
                buildGeneralClassInfoToVersionClassesMap(versionClassesBasePath, versionRegexPattern);
        Collection<File> mapperFiles = FileUtils.listFiles(new File(mappersBasePath), new String[] {"java"}, true);
        Map<String, Set<ConverterInfo>> result = new TreeMap<>();
        for (Map.Entry<ClassInfo, Set<Class<?>>> entry : generalClassInfoToVersionClassesMap.entrySet()) {
            ClassInfo generalClassInfo = entry.getKey();
            if (!needInclude(includeClassesRegex, generalClassInfo)) {
                continue;
            }
            String generalClassName = generalClassInfo.getName().replace("$", ".");
            Set<Class<?>> classes = entry.getValue();
            for (Class<?> aClass : classes) {
                String version = extractVersionStringFromClassNameOrThrow(versionRegexPattern, aClass.getCanonicalName());
                String mapperSimpleName = getMapperName(aClass);
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
