package io.github.pinkolik.general_classes_generator.generators.impl;

import io.github.pinkolik.general_classes_generator.conversion.BaseConverter;
import io.github.pinkolik.general_classes_generator.conversion.BaseMapper;
import io.github.pinkolik.general_classes_generator.conversion.impl.BaseConverterImpl;
import io.github.pinkolik.general_classes_generator.generators.Generator;
import io.github.pinkolik.general_classes_generator.util.ConverterInfo;
import io.github.pinkolik.general_classes_generator.util.GeneratorUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Generates a spring-based converter named {@link BaseConverter}.
 *
 * @see BaseConverter
 */
@Slf4j
public class BaseConvertersConfigGeneratorImpl implements Generator {

    private static final String BASE_CONVERTERS_CONFIG_TEMPLATE_PATH = "templates/BaseConvertersConfigTemplate.java";

    private static final String BASE_CONVERTER_BEAN_TEMPLATE_PATH = "templates/BaseConverterBeanTemplate.java";

    private static final String PUT_TO_MAPS_HOLDER = "${put_to_maps}";

    private static final Pattern PUT_TO_MAPS_SPACES_PATTERN = Pattern.compile("( *)\\$\\{put_to_maps}");

    private static final String VERSION_HOLDER = "${version}";

    private static final String CONVERTERS_CONFIGS_HOLDER = "${converters_configs}";

    private static final Pattern CONVERTERS_CONFIGS_SPACES_PATTERN = Pattern.compile("( *)\\$\\{converters_configs}");

    private static final String PACKAGE_NAME_HOLDER = "${package_name}";

    private static final String BASE_CONVERTERS_CONFIG_FILE_NAME = "BaseConvertersConfig.java";

    private static final String BASE_MAPPER_CLASS_NAME_HOLDER = "${base_mapper_class_name}";

    private static final String BASE_CONVERTER_CLASS_NAME_HOLDER = "${base_converter_class_name}";

    private static final String BASE_CONVERTER_IMPL_CLASS_NAME_HOLDER = "${base_converter_impl_class_name}";

    private static final String GENERAL_CLASSES_BASE_PACKAGE_HOLDER = "${general_classes_base_package}";

    private static final Class<?> BASE_MAPPER_CLASS = BaseMapper.class;

    private static final Class<?> BASE_CONVERTER_CLASS = BaseConverter.class;

    private static final Class<?> BASE_CONVERTER_IMPL_CLASS = BaseConverterImpl.class;

    private final String versionClassesBasePath;

    private final Pattern versionRegexPattern;

    private final String mappersBasePath;

    private final String outputPath;

    private Set<Pattern> includeClassesRegex;

    /**
     * Constructor for {@link BaseConvertersConfigGeneratorImpl}.
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
     * @param mappersBasePath        base path to the directory where mapper interfaces are stored.
     *                               Example: your have generated mappers for Example located at
     *                               "${project.basedir}/src/main/java/your_package/ver1/ExampleMapper.java",
     *                               "${project.basedir}/src/main/java/your_package/ver2/ExampleMapper.java",
     *                               "${project.basedir}/src/main/java/your_package/ver3/ExampleMapper.java".
     *                               In that case the base path would be "${project.basedir}/src/main/java/your_package".
     * @param outputPath             path to the directory where BaseConvertersConfig.java will be put.
     */
    public BaseConvertersConfigGeneratorImpl(final String versionClassesBasePath, final String versionRegexPattern,
                                             final String mappersBasePath, final String outputPath) {
        this.versionClassesBasePath = versionClassesBasePath;
        this.versionRegexPattern = Pattern.compile(versionRegexPattern);
        this.mappersBasePath = mappersBasePath;
        this.outputPath = outputPath;
    }

    private String buildBeansString(final Map<String, Set<ConverterInfo>> versionToConverterInfoMap,
                                    final String generalClassesBasePackage) throws IOException {
        String baseConverterBeanTemplate = IOUtils.resourceToString(BASE_CONVERTER_BEAN_TEMPLATE_PATH, StandardCharsets.UTF_8,
                                                                    BaseConvertersConfigGeneratorImpl.class.getClassLoader());
        StringBuilder beansStringBuilder = new StringBuilder();
        String spacesPrefix = "";
        Matcher matcher = PUT_TO_MAPS_SPACES_PATTERN.matcher(baseConverterBeanTemplate);
        if (matcher.find()) {
            spacesPrefix = matcher.group(1);
        }
        for (Map.Entry<String, Set<ConverterInfo>> entry : versionToConverterInfoMap.entrySet()) {
            String version = entry.getKey();
            Set<ConverterInfo> converterInfoSet = entry.getValue();
            String currentVersionConverter = baseConverterBeanTemplate;
            StringBuilder putToMaps = new StringBuilder();
            boolean first = true;
            for (ConverterInfo converterInfo : converterInfoSet) {
                if (first) {
                    first = false;
                }
                else {
                    putToMaps.append(spacesPrefix);
                }
                //@formatter:off
                putToMaps.append("generalClassesToMappers.put(")
                         .append(converterInfo.getGeneralClassName())
                         .append(".class, ")
                         .append(converterInfo.getMapperClassName())
                         .append(".INSTANCE);\n")
                         .append(spacesPrefix)
                         .append("versionedClassesToMappers.put(")
                         .append(converterInfo.getVersionedClassName())
                         .append(".class, ")
                         .append(converterInfo.getMapperClassName())
                         .append(".INSTANCE);\n");
                //@formatter:on
            }
            String putToMapsString =
                    putToMaps.length() > 0 ? putToMaps.substring(0, putToMaps.length() - 1) : putToMaps.toString();
            currentVersionConverter = currentVersionConverter.replace(PUT_TO_MAPS_HOLDER, putToMapsString);
            currentVersionConverter = currentVersionConverter.replace(VERSION_HOLDER, version);
            currentVersionConverter =
                    currentVersionConverter.replace(GENERAL_CLASSES_BASE_PACKAGE_HOLDER, generalClassesBasePackage);
            beansStringBuilder.append(currentVersionConverter);
        }
        return beansStringBuilder.toString();
    }

    private void writeBeansConverterConfig(final Map<String, Set<ConverterInfo>> versionToConverterInfoMap,
                                           final String generalClassesBasePackage) throws IOException {
        String baseConvertersConfigTemplate =
                IOUtils.resourceToString(BASE_CONVERTERS_CONFIG_TEMPLATE_PATH, StandardCharsets.UTF_8,
                                         BaseConvertersConfigGeneratorImpl.class.getClassLoader());
        String pathToClass = outputPath + File.separator + BASE_CONVERTERS_CONFIG_FILE_NAME;
        String fullClassName = GeneratorUtil.convertPathToClassToClassName(pathToClass);
        String packageName = fullClassName.substring(0, fullClassName.lastIndexOf("."));
        String beansString = buildBeansString(versionToConverterInfoMap, generalClassesBasePackage);
        baseConvertersConfigTemplate = baseConvertersConfigTemplate.replace(PACKAGE_NAME_HOLDER, packageName);
        Matcher matcher = CONVERTERS_CONFIGS_SPACES_PATTERN.matcher(baseConvertersConfigTemplate);
        if (matcher.find()) {
            String convertersSpaces = matcher.group(1);
            beansString = beansString.replaceAll(".+\\n", convertersSpaces + "$0");
        }
        baseConvertersConfigTemplate =
                baseConvertersConfigTemplate.replaceFirst(".*\\Q" + CONVERTERS_CONFIGS_HOLDER + "\\E", beansString);
        baseConvertersConfigTemplate =
                baseConvertersConfigTemplate.replace(BASE_CONVERTER_CLASS_NAME_HOLDER, BASE_CONVERTER_CLASS.getName());
        baseConvertersConfigTemplate =
                baseConvertersConfigTemplate.replace(BASE_CONVERTER_IMPL_CLASS_NAME_HOLDER, BASE_CONVERTER_IMPL_CLASS.getName());
        baseConvertersConfigTemplate =
                baseConvertersConfigTemplate.replace(BASE_MAPPER_CLASS_NAME_HOLDER, BASE_MAPPER_CLASS.getName());
        FileUtils.writeStringToFile(new File(pathToClass), baseConvertersConfigTemplate, StandardCharsets.UTF_8);
    }

    @Override
    public void generate() throws IOException {
        Map<String, Set<ConverterInfo>> versionToConverterInfoMap =
                GeneratorUtil.buildVersionToConverterInfoMap(versionClassesBasePath, versionRegexPattern, mappersBasePath,
                                                             includeClassesRegex);
        String generalClassesBasePackage = GeneratorUtil.convertPathToPackageName(versionClassesBasePath);
        writeBeansConverterConfig(versionToConverterInfoMap, generalClassesBasePackage);
    }

    /**
     * @param includeClassesRegex List of regex expressions of classes to include for generation.
     *                            If not set all classes are generated.
     */
    @Override
    public void setIncludeClassesRegex(final Set<String> includeClassesRegex) {
        log.info("Set includeClassesRegex to {}", includeClassesRegex);
        if (includeClassesRegex == null || includeClassesRegex.isEmpty()) {
            return;
        }
        this.includeClassesRegex = includeClassesRegex.stream().map(Pattern::compile).collect(Collectors.toSet());
    }
}
