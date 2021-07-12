package io.github.pinkolik.general_classes_generator.generators.impl;

import io.github.pinkolik.general_classes_generator.conversion.BaseMapper;
import io.github.pinkolik.general_classes_generator.generators.Generator;
import io.github.pinkolik.general_classes_generator.util.ClassInfo;
import io.github.pinkolik.general_classes_generator.util.GeneratorUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Generates mappers which can be used to convert generalized classes to versioned and vice versa.
 *
 * @see BaseMapper
 */
public class MappersGeneratorImpl implements Generator {

    private static final Class<?> BASE_MAPPER_CLASS = BaseMapper.class;

    private static final String MAPPER_TEMPLATE_PATH = "templates/MapperTemplate.java";

    private static final String ENUM_MAPPING_TEMPLATE_PATH = "templates/EnumMappingTemplate.java";

    private static final String PACKAGE_NAME_HOLDER = "${package_name}";

    private static final String BASE_MAPPER_NAME_HOLDER = "${base_mapper_name}";

    private static final String BASE_MAPPER_SIMPLE_NAME_HOLDER = "${base_mapper_simple_name}";

    private static final String VERSIONED_CLASS_NAME_HOLDER = "${versioned_class_name}";

    private static final String GENERAL_CLASS_NAME_HOLDER = "${general_class_name}";

    private static final String MAPPER_NAME_HOLDER = "${mapper_name}";

    private static final String ADDITIONAL_MAPPINGS_HOLDER = "${additional_mappings}";

    private static final String ADDITIONAL_MAPPERS_HOLDER = "${additional_mappers}";

    private static final String VERSIONED_ENUM_HOLDER = "${versioned_enum}";

    private static final String GENERAL_ENUM_HOLDER = "${general_enum}";

    private final String versionClassesBasePath;

    private final Pattern versionRegexPattern;

    private final String outputBasePath;

    /**
     * Constructor for {@link MappersGeneratorImpl}.
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
     * @param outputBasePath         base path to the directory where mapper interfaces will be generated.
     *                               Example: your project have multiple versions of class Example located at
     *                               "${project.basedir}/src/main/java/your_package/ver1/Example.java",
     *                               "${project.basedir}/src/main/java/your_package/ver2/Example.java",
     *                               "${project.basedir}/src/main/java/your_package/ver3/Example.java".
     *                               And outputBasePath is "${project.basedir}/src/main/java/another_package".
     *                               Then mappers for Example will be put in
     *                               "${project.basedir}/src/main/java/another_package/ver1/ExampleMapper.java",
     *                               "${project.basedir}/src/main/java/another_package/ver2/ExampleMapper.java",
     *                               "${project.basedir}/src/main/java/another_package/ver3/ExampleMapper.java".
     */
    public MappersGeneratorImpl(final String versionClassesBasePath, final String versionRegexPattern,
                                final String outputBasePath) {
        this.versionClassesBasePath = versionClassesBasePath;
        this.versionRegexPattern = Pattern.compile(versionRegexPattern);
        this.outputBasePath = outputBasePath;
    }

    private static String getPathToMapper(final String mappersBasePath, final Class<?> aClass) {
        return mappersBasePath + File.separator +
                (aClass.getName().replace("$", "_") + GeneratorUtil.MAPPER_POSTFIX).replace(".", File.separator) + ".java";
    }

    private static String getMapperName(final Class<?> aClass) {
        String mapperName = aClass.getName().replace("$", "_") + GeneratorUtil.MAPPER_POSTFIX;
        return mapperName.substring(mapperName.lastIndexOf(".") + 1);
    }

    private static String getAdditionalMappingsString(final Class<?> aClass, final Pattern versionRegexPattern)
            throws IOException {
        String enumMappingTemplate = IOUtils.resourceToString(ENUM_MAPPING_TEMPLATE_PATH, StandardCharsets.UTF_8,
                                                              MappersGeneratorImpl.class.getClassLoader());
        StringBuilder sb = new StringBuilder();
        Set<Class<?>> processedEnums = new HashSet<>();
        for (Field field : aClass.getDeclaredFields()) {
            if (field.isSynthetic()) {
                continue;
            }
            Class<?> type = field.getType();
            if ((type.isMemberClass() && !aClass.equals(type)) || !Enum.class.isAssignableFrom(type) ||
                    processedEnums.contains(type)) {
                continue;
            }
            processedEnums.add(type);
            String versionedEnum = type.getCanonicalName();
            String generalEnum = GeneratorUtil.getGeneralTypeName(versionRegexPattern, field, type);
            String enumMapping = enumMappingTemplate.replace(VERSIONED_ENUM_HOLDER, versionedEnum);
            enumMapping = enumMapping.replace(GENERAL_ENUM_HOLDER, generalEnum);
            sb.append(enumMapping);
        }
        return sb.toString();
    }

    private static String getAdditionalMappersString(final Class<?> aClass) {
        StringBuilder sb = new StringBuilder();
        for (Class<?> declaredClass : aClass.getDeclaredClasses()) {
            //@formatter:off
            sb.append(getMapperName(declaredClass))
              .append(".class, ")
              .append(getAdditionalMappersString(declaredClass))
              .append(", ");
            //@formatter:on
        }
        return sb.length() == 0 ? "" : sb.substring(0, sb.length() - 2);
    }

    private static void writeMappers(final Map<ClassInfo, Set<Class<?>>> generalClassInfoToVersionClassesMap,
                                     final String outputBasePath, final Pattern versionRegexPattern) throws IOException {
        for (Map.Entry<ClassInfo, Set<Class<?>>> entry : generalClassInfoToVersionClassesMap.entrySet()) {
            ClassInfo generalClassInfo = entry.getKey();
            String generalClassName = generalClassInfo.getName();
            Set<Class<?>> classes = entry.getValue();
            for (Class<?> aClass : classes) {
                String mapperName = getMapperName(aClass);
                String packageName = aClass.getPackage().getName();
                String pathToMapper = getPathToMapper(outputBasePath, aClass);
                String mapperTemplate = IOUtils.resourceToString(MAPPER_TEMPLATE_PATH, StandardCharsets.UTF_8,
                                                                 MappersGeneratorImpl.class.getClassLoader());
                mapperTemplate = mapperTemplate.replace(PACKAGE_NAME_HOLDER, packageName);
                mapperTemplate = mapperTemplate.replace(BASE_MAPPER_NAME_HOLDER, BASE_MAPPER_CLASS.getName());
                mapperTemplate = mapperTemplate.replace(BASE_MAPPER_SIMPLE_NAME_HOLDER, BASE_MAPPER_CLASS.getSimpleName());
                mapperTemplate = mapperTemplate.replace(VERSIONED_CLASS_NAME_HOLDER, aClass.getCanonicalName());
                mapperTemplate = mapperTemplate.replace(GENERAL_CLASS_NAME_HOLDER, generalClassName.replace("$", "."));
                mapperTemplate = mapperTemplate.replace(MAPPER_NAME_HOLDER, mapperName);
                mapperTemplate = mapperTemplate
                        .replace(ADDITIONAL_MAPPINGS_HOLDER, getAdditionalMappingsString(aClass, versionRegexPattern));
                mapperTemplate = mapperTemplate.replace(ADDITIONAL_MAPPERS_HOLDER, getAdditionalMappersString(aClass));
                FileUtils.writeStringToFile(new File(pathToMapper), mapperTemplate, StandardCharsets.UTF_8);
            }
        }
    }

    @Override
    public void generate() throws IOException {
        Map<ClassInfo, Set<Class<?>>> generalClassInfoToVersionClassesMap =
                GeneratorUtil.buildGeneralClassInfoToVersionClassesMap(versionClassesBasePath, versionRegexPattern);
        writeMappers(generalClassInfoToVersionClassesMap, outputBasePath, versionRegexPattern);
    }
}
