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
import java.nio.file.Files;

class MappersGeneratorImplTest {

    private final Logger log = LoggerFactory.getLogger(MappersGeneratorImplTest.class);

    private static final String ACTUAL_PATH = "src/test/resources/actual/src/main/java/";

    private static final String EXPECTED_PATH = "src/test/resources/expected/mappers/";

    private static final String VERSION_REGEX_PATTERN = "ver\\d+";

    private static final String BASE_PACKAGE_PATH = "io/github/pinkolik/general_classes_generator/test/";

    private static final String VERSION_CLASSES_BASE_PATH =
            "../test-classes/src/main/java/io/github/pinkolik/general_classes_generator/test/";

    private void baseCompareTwoFilesTestForAllVersions(final String[] filenames) throws IOException, IllegalAccessException {
        Generator generator = new MappersGeneratorImpl(VERSION_CLASSES_BASE_PATH, VERSION_REGEX_PATTERN, ACTUAL_PATH);
        generator.generate();
        for (String filename : filenames) {
            for (int i = 1; i < 4; i++) {
                String ver = String.format("ver%d/", i);
                String expected = FileUtils.readFileToString(new File(EXPECTED_PATH + ver + filename), StandardCharsets.UTF_8);

                String actual = FileUtils
                        .readFileToString(new File(ACTUAL_PATH + BASE_PACKAGE_PATH + ver + filename), StandardCharsets.UTF_8);
                Assertions.assertEquals(expected, actual);
            }
        }
    }

    @Test
    void simpleMapperGeneratedTest() throws IOException, IllegalAccessException {
        baseCompareTwoFilesTestForAllVersions(new String[] {"SimpleMapper.java"});
    }

    @Test
    void parentAndChildMappersGeneratedTest() throws IOException, IllegalAccessException {
        baseCompareTwoFilesTestForAllVersions(new String[] {"ParentClassMapper.java", "InheritanceTestClassMapper.java"});
    }

    // TODO: 12.07.2021 enum mapper
    // TODO: 12.07.2021 inner class mapper
    // TODO: 12.07.2021 inner class with inner enum
}
