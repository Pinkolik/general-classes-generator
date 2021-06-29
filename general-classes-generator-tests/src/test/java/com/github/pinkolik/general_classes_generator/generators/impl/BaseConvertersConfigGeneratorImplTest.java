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

class BaseConvertersConfigGeneratorImplTest {

    private final Logger log = LoggerFactory.getLogger(BaseConvertersConfigGeneratorImplTest.class);

    private static final String ACTUAL_PATH = "src/test/resources/actual/src/main/java/";

    private static final String EXPECTED_PATH = "src/test/resources/expected/converter/";

    private static final String VERSION_REGEX_PATTERN = "ver\\d+";

    private static final String BASE_PACKAGE_PATH = "com/github/pinkolik/general_classes_generator/test/converter/";

    private static final String VERSION_CLASSES_BASE_PATH =
            "src/main/java/com/github/pinkolik/general_classes_generator/test/converter";

    private void baseCompareTwoFilesTest(final String filename) throws IOException, IllegalAccessException {
        Generator mappersGenerator = new MappersGeneratorImpl(VERSION_CLASSES_BASE_PATH, VERSION_REGEX_PATTERN, ACTUAL_PATH);
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
}
