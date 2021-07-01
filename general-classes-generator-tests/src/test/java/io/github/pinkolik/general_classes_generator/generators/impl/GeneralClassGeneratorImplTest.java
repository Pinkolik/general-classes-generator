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

class GeneralClassGeneratorImplTest {

    private final Logger log = LoggerFactory.getLogger(GeneralClassGeneratorImplTest.class);

    private static final String ACTUAL_PATH = "src/test/resources/actual/src/main/java/";

    private static final String EXPECTED_PATH = "src/test/resources/expected/general/";

    private static final String VERSION_REGEX_PATTERN = "ver\\d+";

    private static final String BASE_PACKAGE_PATH = "io/github/pinkolik/general_classes_generator/test/general/";

    private static final String VERSION_CLASSES_BASE_PATH =
            "src/main/java/io/github/pinkolik/general_classes_generator/test/general";

    private void baseCompareTwoFilesTest(final String filename) throws IOException, IllegalAccessException {
        Generator generator = new GeneralClassGeneratorImpl(VERSION_CLASSES_BASE_PATH, VERSION_REGEX_PATTERN, ACTUAL_PATH);
        String expected = FileUtils.readFileToString(new File(EXPECTED_PATH + filename), StandardCharsets.UTF_8);

        generator.generate();

        String actual = FileUtils.readFileToString(new File(ACTUAL_PATH + BASE_PACKAGE_PATH + filename), StandardCharsets.UTF_8);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void simpleFieldsMergeTest() throws IOException, IllegalAccessException {
        baseCompareTwoFilesTest("Simple.java");
    }

    @Test
    void simpleEnumsMergeTest() throws IOException, IllegalAccessException {
        baseCompareTwoFilesTest("Enum.java");
    }

    @Test
    void singleInnerClassTest() throws IOException, IllegalAccessException {
        baseCompareTwoFilesTest("InnerClass.java");
    }

    @Test
    void emptyClassTest() throws IOException, IllegalAccessException {
        baseCompareTwoFilesTest("Empty.java");
    }

    @Test
    void innerClassWithInnerEnumTest() throws IOException, IllegalAccessException {
        baseCompareTwoFilesTest("InnerClassWithInnerEnum.java");
    }

    @Test
    void innerClassWithGenericFieldTest() throws IOException, IllegalAccessException {
        baseCompareTwoFilesTest("InnerClassWithGenericField.java");
    }

    @Test
    void classWithSimpleConstantTest() throws IOException, IllegalAccessException {
        baseCompareTwoFilesTest("ClassWithSimpleConstant.java");
    }
}
