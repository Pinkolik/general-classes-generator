package com.github.pinkolik.general_classes_generator.generators.impl;

import com.github.pinkolik.general_classes_generator.conversion.BaseMapper;
import com.github.pinkolik.general_classes_generator.generators.Generator;
import com.github.pinkolik.general_classes_generator.util.ClassInfo;
import com.github.pinkolik.general_classes_generator.util.GeneratorUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class MappersGeneratorImpl implements Generator {

    private static final Class<?> BASE_MAPPER_CLASS = BaseMapper.class;

    private static final String MAPPER_TEMPLATE_PATH = "templates/MapperTemplate.java";

    private static final String PACKAGE_NAME_HOLDER = "${package_name}";

    private static final String BASE_MAPPER_NAME_HOLDER = "${base_mapper_name}";

    private static final String BASE_MAPPER_SIMPLE_NAME_HOLDER = "${base_mapper_simple_name}";

    private static final String VERSIONED_CLASS_NAME_HOLDER = "${versioned_class_name}";

    private static final String GENERAL_CLASS_NAME_HOLDER = "${general_class_name}";

    private static final String MAPPER_NAME_HOLDER = "${mapper_name}";

    private final String versionClassesBasePath;

    private final Pattern versionRegexPattern;

    private final String outputBasePath;

    public MappersGeneratorImpl(final String versionClassesBasePath, final String versionRegexPattern,
                                final String outputBasePath) {
        this.versionClassesBasePath = versionClassesBasePath;
        this.versionRegexPattern = Pattern.compile(versionRegexPattern);
        this.outputBasePath = outputBasePath;
    }

    private static String getPathToMapper(final String mappersBasePath, final Class<?> aClass) {
        return mappersBasePath + File.separator + (aClass.getName() + GeneratorUtil.MAPPER_POSTFIX).replace(".", File.separator) +
                ".java";
    }

    private static void writeMappers(final Map<ClassInfo, Set<Class<?>>> generalClassInfoToVersionClassesMap,
                                     final String outputBasePath) throws IOException {
        for (Map.Entry<ClassInfo, Set<Class<?>>> entry : generalClassInfoToVersionClassesMap.entrySet()) {
            ClassInfo generalClassInfo = entry.getKey();
            if (generalClassInfo.isMember()) {
                continue;
            }
            String generalClassName = generalClassInfo.getName();
            Set<Class<?>> classes = entry.getValue();
            for (Class<?> aClass : classes) {
                String mapperName = aClass.getSimpleName() + GeneratorUtil.MAPPER_POSTFIX;
                String packageName = aClass.getPackage().getName();
                String pathToMapper = getPathToMapper(outputBasePath, aClass);
                String mapperTemplate = IOUtils.resourceToString(MAPPER_TEMPLATE_PATH, StandardCharsets.UTF_8,
                                                                 MappersGeneratorImpl.class.getClassLoader());
                mapperTemplate = mapperTemplate.replace(PACKAGE_NAME_HOLDER, packageName);
                mapperTemplate = mapperTemplate.replace(BASE_MAPPER_NAME_HOLDER, BASE_MAPPER_CLASS.getName());
                mapperTemplate = mapperTemplate.replace(BASE_MAPPER_SIMPLE_NAME_HOLDER, BASE_MAPPER_CLASS.getSimpleName());
                mapperTemplate = mapperTemplate.replace(VERSIONED_CLASS_NAME_HOLDER, aClass.getName());
                mapperTemplate = mapperTemplate.replace(GENERAL_CLASS_NAME_HOLDER, generalClassName);
                mapperTemplate = mapperTemplate.replace(MAPPER_NAME_HOLDER, mapperName);
                FileUtils.writeStringToFile(new File(pathToMapper), mapperTemplate, StandardCharsets.UTF_8);
            }
        }
    }

    @Override
    public void generate() throws IOException {
        Map<ClassInfo, Set<Class<?>>> generalClassInfoToVersionClassesMap =
                GeneratorUtil.buildGeneralClassInfoToVersionClassesMap(versionClassesBasePath, versionRegexPattern);
        writeMappers(generalClassInfoToVersionClassesMap, outputBasePath);
    }
}
