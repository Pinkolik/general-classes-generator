package io.github.pinkolik.general_classes_generator.generators.impl;

import io.github.pinkolik.general_classes_generator.generators.Generator;
import io.github.pinkolik.general_classes_generator.util.ClassInfo;
import io.github.pinkolik.general_classes_generator.util.FieldInfo;
import io.github.pinkolik.general_classes_generator.util.GeneratorUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Generates generalized classes from multiple versions of this class.
 */
@Slf4j
public class GeneralClassesGeneratorImpl implements Generator {

    private static final String CLASS_NAME_HOLDER = "${class_name}";

    private static final String PACKAGE_NAME_HOLDER = "${package_name}";

    private static final String FIELDS_HOLDER = "${fields}";

    private static final Pattern FIELDS_SPACES_PATTERN = Pattern.compile("( *)\\$\\{fields}");

    private static final String TYPE_HOLDER = "${type}";

    private static final String IS_STATIC_HOLDER = " ${is_static}";

    private static final String INNER_CLASSES_HOLDER = "//${inner_classes}";

    private static final String DATA_ANNOTATION_HOLDER = "${data_annotation}";

    private static final String IMPLEMENTS_HOLDER = "${implements}";

    private static final Pattern INNER_CLASSES_SPACES_PATTERN = Pattern.compile("( *)//\\$\\{inner_classes}");

    private static final String GENERAL_CLASS_TEMPLATE_PATH = "templates/GeneralClassTemplate.java";

    private static final String PACKAGE_AND_IMPORT_TEMPLATE_PATH = "templates/PackageAndImport.java";

    private final String versionClassesBasePath;

    private final Pattern versionRegexPattern;

    private final String outputBasePath;

    private final boolean makeSerializable;

    /**
     * Constructor for {@link GeneralClassesGeneratorImpl}.
     *
     * @param versionClassesBasePath base path to the directory where versioned classes are stored.
     *                               Example: your project have multiple versions of class Example located at
     *                               "${project.basedir}/src/main/java/your_package/ver1/Example.java",
     *                               "${project.basedir}/src/main/java/your_package/ver2/Example.java",
     *                               "${project.basedir}/src/main/java/your_package/ver3/Example.java".
     *                               In that case the base path would be "${project.basedir}/src/main/java/your_package".
     * @param versionRegexPattern    regular expression to extract version part from the path.
     *                               Example: your project have multiple versions of class Example located at
     *                               "${project.basedir}/src/main/java/your_package/ver1/Example.java",
     *                               "${project.basedir}/src/main/java/your_package/ver2/Example.java",
     *                               "${project.basedir}/src/main/java/your_package/ver3/Example.java".
     *                               In that case version RegEx would be "ver\d+".
     * @param outputBasePath         base path to the directory where generalized classes will be generated.
     *                               Example: your project have multiple versions of class Example located at
     *                               "${project.basedir}/src/main/java/your_package/ver1/Example.java",
     *                               "${project.basedir}/src/main/java/your_package/ver2/Example.java",
     *                               "${project.basedir}/src/main/java/your_package/ver3/Example.java".
     *                               And outputBasePath is "${project.basedir}/src/main/java/another_package".
     * @param makeSerializable       If set to {@code true} makes generalized classes implement {@link java.io.Serializable} interface.
     */
    public GeneralClassesGeneratorImpl(final String versionClassesBasePath, final String versionRegexPattern,
                                       final String outputBasePath, final boolean makeSerializable) {
        this.versionClassesBasePath = versionClassesBasePath;
        this.versionRegexPattern = Pattern.compile(versionRegexPattern);
        this.outputBasePath = outputBasePath;
        this.makeSerializable = makeSerializable;
    }

    private static String getPathToClass(final String outputBasePath, final ClassInfo classInfo) {
        String generalClassName = classInfo.getName();
        return classInfo.isMember() ? outputBasePath + File.separator +
                generalClassName.substring(0, generalClassName.indexOf('$')).replace(".", File.separator) + ".java" :
               outputBasePath + File.separator + generalClassName.replace(".", File.separator) + ".java";
    }

    private static String getSimpleClassName(final ClassInfo generalClassInfo) {
        String generalClassName = generalClassInfo.getName();
        return generalClassInfo.isMember() ? generalClassName.substring(generalClassName.lastIndexOf("$") + 1) : generalClassName
                .substring(generalClassName.lastIndexOf(".") + 1);
    }

    private static String getClassTemplate(final ClassInfo classInfo) throws IOException {
        String generalClassTemplate = IOUtils.resourceToString(GENERAL_CLASS_TEMPLATE_PATH, StandardCharsets.UTF_8,
                                                               GeneralClassesGeneratorImpl.class.getClassLoader());
        if (classInfo.isMember()) {
            return generalClassTemplate;
        }
        String packageAndImportTemplate = IOUtils.resourceToString(PACKAGE_AND_IMPORT_TEMPLATE_PATH, StandardCharsets.UTF_8,
                                                                   GeneralClassesGeneratorImpl.class.getClassLoader());
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
                prettyPrintedClassTemplate = prettyPrintedClassTemplate.replace("\n", "\n" + spacesPrefix);
            }
            classTemplateToWrite = classTemplateToWrite.replace(INNER_CLASSES_HOLDER, prettyPrintedClassTemplate);
        }
        classTemplateToWrite = classTemplateToWrite.replaceAll(" +\\n", "\n");
        FileUtils.writeStringToFile(classFile, classTemplateToWrite, StandardCharsets.UTF_8);
    }

    private static String getStaticFinalValueString(final FieldInfo fieldInfo) {
        if (fieldInfo.isStatic() && fieldInfo.isFinal()) {
            String value = String.class.getName().equals(fieldInfo.getType()) ?
                           "\"" + fieldInfo.getStaticFinalValue() + "\"" : fieldInfo.getStaticFinalValue().toString();
            return " = " + value;
        }
        return "";
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
                fieldsStringBuilder.append(fieldInfo.isStatic() && fieldInfo.isFinal() ? "@Getter\n" + spacesPrefix : "")
                                   .append("private ")
                                   .append(fieldInfo.isStatic() ? "static " : "")
                                   .append(fieldInfo.isFinal() ? "final " : "")
                                   .append(fieldInfo.getType())
                                   .append(" ")
                                   .append(fieldInfo.getName())
                                   .append(getStaticFinalValueString(fieldInfo))
                                   .append(";\n");
                //@formatter:on
            }
            else {
                //@formatter:off
                fieldsStringBuilder.append(fieldInfo.getName())
                                   .append(",\n");
                //@formatter:on
            }
        }
        //@formatter:off
        if (fieldsStringBuilder.length() == 0) {
            return "";
        }
        return generalClassInfo.isEnum() ?
               fieldsStringBuilder.substring(0, fieldsStringBuilder.length() - 2) :
               fieldsStringBuilder.substring(0, fieldsStringBuilder.length() - 1);
        //@formatter:on
    }

    private static String getImplementsString(final ClassInfo generalClassInfo, final boolean makeSerializable) {
        if (generalClassInfo.isEnum()) {
            return "";
        }
        return makeSerializable ? "implements java.io.Serializable " : "";
    }

    private static void writeGeneralClass(final String outputBasePath, final ClassInfo generalClassInfo,
                                          final Set<FieldInfo> fieldInfos, final boolean makeSerializable) throws IOException {
        String generalClassName = generalClassInfo.getName();
        String pathToClass = getPathToClass(outputBasePath, generalClassInfo);
        String packageName = generalClassName.substring(0, generalClassName.lastIndexOf("."));
        String simpleClassName = getSimpleClassName(generalClassInfo);
        String classTemplate = getClassTemplate(generalClassInfo);
        String fieldsString = getFieldsString(classTemplate, generalClassInfo, fieldInfos);
        String implementsString = getImplementsString(generalClassInfo, makeSerializable);
        classTemplate = classTemplate.replace(PACKAGE_NAME_HOLDER, packageName);
        classTemplate = classTemplate.replace(CLASS_NAME_HOLDER, simpleClassName);
        classTemplate = classTemplate.replace(FIELDS_HOLDER, fieldsString);
        classTemplate = classTemplate.replace(TYPE_HOLDER, generalClassInfo.isEnum() ? "enum" : "class");
        classTemplate = classTemplate.replace(DATA_ANNOTATION_HOLDER, generalClassInfo.isEnum() ? "" : "@Data");
        classTemplate = classTemplate.replace(IS_STATIC_HOLDER, generalClassInfo.isStatic() ? " static" : "");
        classTemplate = classTemplate.replace(IMPLEMENTS_HOLDER, implementsString);
        writeTemplateToFile(pathToClass, classTemplate, generalClassInfo);
    }

    private static void writeGeneralClasses(final Map<ClassInfo, Set<FieldInfo>> generalClassInfoToFieldInfosMap,
                                            final String outputBasePath, final boolean makeSerializable) throws IOException {
        for (Map.Entry<ClassInfo, Set<FieldInfo>> entry : generalClassInfoToFieldInfosMap.entrySet()) {
            ClassInfo generalClassInfo = entry.getKey();
            Set<FieldInfo> fieldInfos = entry.getValue();
            writeGeneralClass(outputBasePath, generalClassInfo, fieldInfos, makeSerializable);
        }
    }

    @Override
    public void generate() throws IOException, IllegalAccessException {
        Map<ClassInfo, Set<FieldInfo>> generalClassInfoToFieldInfosMap =
                GeneratorUtil.buildGeneralClassNameToFieldInfoMap(versionClassesBasePath, versionRegexPattern);
        writeGeneralClasses(generalClassInfoToFieldInfosMap, outputBasePath, makeSerializable);
    }

}
