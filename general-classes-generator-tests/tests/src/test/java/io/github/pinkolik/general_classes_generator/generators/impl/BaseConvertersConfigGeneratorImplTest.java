package io.github.pinkolik.general_classes_generator.generators.impl;


import io.github.pinkolik.general_classes_generator.generators.Generator;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

class BaseConvertersConfigGeneratorImplTest {

    private final Logger log = LoggerFactory.getLogger(BaseConvertersConfigGeneratorImplTest.class);

    private static final String ACTUAL_PATH = "src/test/resources/actual/src/main/java/";

    private static final String MAPPERS_PACKAGE_PATH = "io/github/pinkolik/general_classes_generator/test/mappers/";

    private static final String EXPECTED_PATH = "src/test/resources/expected/converter/";

    private static final String VERSION_REGEX_PATTERN = "ver\\d+";

    private static final String BASE_PACKAGE_PATH = "io/github/pinkolik/general_classes_generator/test/";

    private static final String VERSION_CLASSES_BASE_PATH =
            "../test-classes/src/main/java/io/github/pinkolik/general_classes_generator/test/";

    private void baseCompareTwoFilesTest(final String filename) throws IOException, IllegalAccessException {
        FileUtils.deleteDirectory(new File(ACTUAL_PATH));
        Generator mappersGenerator =
                new MappersGeneratorImpl(VERSION_CLASSES_BASE_PATH, VERSION_REGEX_PATTERN, ACTUAL_PATH + MAPPERS_PACKAGE_PATH);
        Generator baseConvertersConfigGenerator =
                new BaseConvertersConfigGeneratorImpl(VERSION_CLASSES_BASE_PATH, VERSION_REGEX_PATTERN, ACTUAL_PATH,
                                                      ACTUAL_PATH + BASE_PACKAGE_PATH);
        String expected = FileUtils.readFileToString(new File(EXPECTED_PATH + filename), StandardCharsets.UTF_8);

        mappersGenerator.generate();
        baseConvertersConfigGenerator.generate();

        String actual = FileUtils.readFileToString(new File(ACTUAL_PATH + BASE_PACKAGE_PATH + filename), StandardCharsets.UTF_8);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void baseConvertersConfigGeneratedTest() throws IOException, IllegalAccessException {
        baseCompareTwoFilesTest("BaseConvertersConfig.java");
    }

    @Test
    void includeClassesRegexTest() throws IOException, IllegalAccessException {
        FileUtils.deleteDirectory(new File(ACTUAL_PATH));
        Set<String> includeClassesRegex = new HashSet<>();
        includeClassesRegex.add(".*Empty");
        includeClassesRegex.add(".*Simple");
        String expectedFilename = "BaseConvertersConfigIncludeRegexTest.java";
        String actualFileName = "BaseConvertersConfig.java";
        Generator mappersGenerator =
                new MappersGeneratorImpl(VERSION_CLASSES_BASE_PATH, VERSION_REGEX_PATTERN, ACTUAL_PATH + MAPPERS_PACKAGE_PATH);
        Generator baseConvertersConfigGenerator =
                new BaseConvertersConfigGeneratorImpl(VERSION_CLASSES_BASE_PATH, VERSION_REGEX_PATTERN, ACTUAL_PATH,
                                                      ACTUAL_PATH + BASE_PACKAGE_PATH);
        baseConvertersConfigGenerator.setIncludeClassesRegex(includeClassesRegex);
        String expected = FileUtils.readFileToString(new File(EXPECTED_PATH + expectedFilename), StandardCharsets.UTF_8);

        mappersGenerator.generate();
        baseConvertersConfigGenerator.generate();

        String actual =
                FileUtils.readFileToString(new File(ACTUAL_PATH + BASE_PACKAGE_PATH + actualFileName), StandardCharsets.UTF_8);
        Assertions.assertEquals(expected, actual);
    }
}
