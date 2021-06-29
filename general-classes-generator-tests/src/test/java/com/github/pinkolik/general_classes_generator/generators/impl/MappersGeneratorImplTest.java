package com.github.pinkolik.general_classes_generator.generators.impl;


import com.github.pinkolik.general_classes_generator.generators.Generator;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

class MappersGeneratorImplTest {

    private final Logger log = LoggerFactory.getLogger(MappersGeneratorImplTest.class);

    private static final String ACTUAL_PATH = "src/test/resources/actual/src/main/java/";

    private static final String EXPECTED_PATH = "src/test/resources/expected/mappers/";

    private static final String VERSION_REGEX_PATTERN = "ver\\d+";

    private static final String BASE_PACKAGE_PATH = "com/github/pinkolik/general_classes_generator/test/mappers/";

    private static final String VERSION_CLASSES_BASE_PATH = "src/main/java/com/github/pinkolik/general_classes_generator/test/mappers";

    private void baseCompareTwoFilesTestForAllVersions(final String filename) throws IOException {
        Generator generator = new MappersGeneratorImpl(VERSION_CLASSES_BASE_PATH, VERSION_REGEX_PATTERN, ACTUAL_PATH);
        generator.generate();
        for (int i = 1; i < 4; i++) {
            String ver = String.format("ver%d/", i);
            String expected = FileUtils.readFileToString(new File(EXPECTED_PATH + ver + filename), StandardCharsets.UTF_8);

            String actual = FileUtils
                    .readFileToString(new File(ACTUAL_PATH + BASE_PACKAGE_PATH + ver + filename), StandardCharsets.UTF_8);
            Assertions.assertEquals(expected, actual);
        }
    }

    @Test
    void mappersGeneratedTest() throws IOException {
        baseCompareTwoFilesTestForAllVersions("SimpleMapper.java");
    }

    @Test
    void mappersForInnerClassNotGeneratedTest() throws IOException {
        Generator generator = new MappersGeneratorImpl(VERSION_CLASSES_BASE_PATH, VERSION_REGEX_PATTERN, ACTUAL_PATH);
        generator.generate();

        for (int i = 1; i < 4; i++) {
            String ver = String.format("ver%d/", i);
            File file = new File(ACTUAL_PATH + BASE_PACKAGE_PATH + ver + "InnerClass/InnerMapper.java");
            if (file.exists()) {
                Assertions.assertTrue(file.delete());
            }
            Assertions.assertFalse(file.exists());
        }
    }
}
