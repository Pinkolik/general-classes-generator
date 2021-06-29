package com.github.pinkolik.general_classes_generator.generators.impl;

import com.github.pinkolik.general_classes_generator.generators.Generator;
import com.github.pinkolik.general_classes_generator.util.ClassInfo;
import com.github.pinkolik.general_classes_generator.util.FieldInfo;
import com.github.pinkolik.general_classes_generator.util.GeneratorUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class GeneralClassGeneratorImpl implements Generator {

    private static final String CLASS_NAME_HOLDER = "${class_name}";

    private static final String PACKAGE_NAME_HOLDER = "${package_name}";

    private static final String FIELDS_HOLDER = "${fields}";

    private static final Pattern FIELDS_SPACES_PATTERN = Pattern.compile("( *)\\$\\{fields}");

    private static final String TYPE_HOLDER = "${type}";

    private static final String IS_STATIC_HOLDER = " ${is_static}";

    private static final String INNER_CLASSES_HOLDER = "//${inner_classes}";

    private static final Pattern INNER_CLASSES_SPACES_PATTERN = Pattern.compile("( *)//\\$\\{inner_classes}");

    private static final String GENERAL_CLASS_TEMPLATE_PATH = "templates/GeneralClassTemplate.java";

    private static final String PACKAGE_AND_IMPORT_TEMPLATE_PATH = "templates/PackageAndImport.java";

    private final String versionClassesBasePath;

    private final Pattern versionRegexPattern;

    private final String outputBasePath;

    public GeneralClassGeneratorImpl(final String versionClassesBasePath, final String versionRegexPattern,
                                     final String outputBasePath) {
        this.versionClassesBasePath = versionClassesBasePath;
        this.versionRegexPattern = Pattern.compile(versionRegexPattern);
        this.outputBasePath = outputBasePath;
    }

    private static String getPathToClass(final String outputBasePath, final ClassInfo classInfo) {
        String generalClassName = classInfo.getName();
        return classInfo.isMember() ? outputBasePath + File.separator +
                generalClassName.substring(0, generalClassName.lastIndexOf('$')).replace(".", File.separator) + ".java" :
               outputBasePath + File.separator + generalClassName.replace(".", File.separator) + ".java";
    }

    private static String getSimpleClassName(final ClassInfo generalClassInfo) {
        String generalClassName = generalClassInfo.getName();
        return generalClassInfo.isMember() ? generalClassName.substring(generalClassName.lastIndexOf("$") + 1) : generalClassName
                .substring(generalClassName.lastIndexOf(".") + 1);
    }

    private static String getClassTemplate(final ClassInfo classInfo) throws IOException {
        String generalClassTemplate = IOUtils.resourceToString(GENERAL_CLASS_TEMPLATE_PATH, StandardCharsets.UTF_8,
                                                               GeneralClassGeneratorImpl.class.getClassLoader());
        if (classInfo.isMember()) {
            return generalClassTemplate;
        }
        String packageAndImportTemplate = IOUtils.resourceToString(PACKAGE_AND_IMPORT_TEMPLATE_PATH, StandardCharsets.UTF_8,
                                                                   GeneralClassGeneratorImpl.class.getClassLoader());
        return packageAndImportTemplate + generalClassTemplate;
    }

    private static void writeTemplateToFile(final String pathToClass, final String classTemplate, final ClassInfo classInfo)
            throws IOException {
        File classFile = new File(pathToClass);
        String classTemplateToWrite;
        if (!classInfo.isMember()) {
            classTemplateToWrite = classTemplate;
        }
        else {
            classTemplateToWrite = FileUtils.readFileToString(classFile, StandardCharsets.UTF_8);
            Matcher matcher = INNER_CLASSES_SPACES_PATTERN.matcher(classTemplateToWrite);
            String prettyPrintedClassTemplate = classTemplate;
            if (matcher.find()) {
                String spacesPrefix = matcher.group(1);
                prettyPrintedClassTemplate = prettyPrintedClassTemplate.replace("\r\n", "\r\n" + spacesPrefix);
            }
            classTemplateToWrite = classTemplateToWrite.replace(INNER_CLASSES_HOLDER, prettyPrintedClassTemplate);
        }
        FileUtils.writeStringToFile(classFile, classTemplateToWrite, StandardCharsets.UTF_8);
    }

    private static String getFieldsString(final String classTemplate, final ClassInfo generalClassInfo,
                                          final Set<FieldInfo> fieldInfos) {
        StringBuilder fieldsStringBuilder = new StringBuilder();
        List<FieldInfo> sortedFieldInfo = new ArrayList<>(fieldInfos);
        sortedFieldInfo.sort(Comparator.comparing(FieldInfo::getName));
        Matcher matcher = FIELDS_SPACES_PATTERN.matcher(classTemplate);
        String spacesPrefix = "";
        if (matcher.find()) {
            spacesPrefix = matcher.group(1);
        }
        boolean first = true;
        for (FieldInfo fieldInfo : sortedFieldInfo) {
            if (first) {
                first = false;
            }
            else {
                fieldsStringBuilder.append(spacesPrefix);
            }
            if (!fieldInfo.isEnum()) {
                //@formatter:off
                fieldsStringBuilder.append("private ")
                                   .append(fieldInfo.isStatic() ? "static " : "")
                                   .append(fieldInfo.getType())
                                   .append(" ")
                                   .append(fieldInfo.getName())
                                   .append(";\r\n");
                //@formatter:on
            }
            else {
                //@formatter:off
                fieldsStringBuilder.append(fieldInfo.getName())
                                   .append(",\r\n");
                //@formatter:on
            }
        }
        //@formatter:off
        if (fieldsStringBuilder.length() == 0) {
            return "";
        }
        return generalClassInfo.isEnum() ?
               fieldsStringBuilder.substring(0, fieldsStringBuilder.length() - 3) :
               fieldsStringBuilder.substring(0, fieldsStringBuilder.length() - 2);
        //@formatter:on
    }

    private static void writeGeneralClass(final String outputBasePath, final ClassInfo generalClassInfo,
                                          final Set<FieldInfo> fieldInfos) throws IOException {
        String generalClassName = generalClassInfo.getName();
        String pathToClass = getPathToClass(outputBasePath, generalClassInfo);
        String packageName = generalClassName.substring(0, generalClassName.lastIndexOf("."));
        String simpleClassName = getSimpleClassName(generalClassInfo);
        String classTemplate = getClassTemplate(generalClassInfo);
        String fieldsString = getFieldsString(classTemplate, generalClassInfo, fieldInfos);
        classTemplate = classTemplate.replace(PACKAGE_NAME_HOLDER, packageName);
        classTemplate = classTemplate.replace(CLASS_NAME_HOLDER, simpleClassName);
        classTemplate = classTemplate.replace(FIELDS_HOLDER, fieldsString);
        classTemplate = classTemplate.replace(TYPE_HOLDER, generalClassInfo.isEnum() ? "enum" : "class");
        classTemplate = classTemplate.replace(IS_STATIC_HOLDER, generalClassInfo.isStatic() ? " static" : "");
        writeTemplateToFile(pathToClass, classTemplate, generalClassInfo);
    }

    private static void writeGeneralClasses(final Map<ClassInfo, Set<FieldInfo>> generalClassInfoToFieldInfosMap,
                                            final String outputBasePath) throws IOException {
        for (Map.Entry<ClassInfo, Set<FieldInfo>> entry : generalClassInfoToFieldInfosMap.entrySet()) {
            ClassInfo generalClassInfo = entry.getKey();
            Set<FieldInfo> fieldInfos = entry.getValue();
            writeGeneralClass(outputBasePath, generalClassInfo, fieldInfos);
        }
    }

    @Override
    public void generate() throws IOException {
        Map<ClassInfo, Set<FieldInfo>> generalClassInfoToFieldInfosMap =
                GeneratorUtil.buildGeneralClassNameToFieldInfoMap(versionClassesBasePath, versionRegexPattern);
        writeGeneralClasses(generalClassInfoToFieldInfosMap, outputBasePath);
    }

}
