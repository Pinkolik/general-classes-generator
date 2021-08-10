package io.github.pinkolik.general_classes_generator.generators.impl;


import io.github.pinkolik.general_classes_generator.generators.Generator;
import io.github.pinkolik.general_classes_generator.util.GeneratorUtil;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

class GeneralClassesGeneratorImplTest {

    private final Logger log = LoggerFactory.getLogger(GeneralClassesGeneratorImplTest.class);

    private static final String ACTUAL_PATH = "src/test/resources/actual/src/main/java/";

    private static final String EXPECTED_PATH = "src/test/resources/expected/general/";

    private static final String VERSION_REGEX_PATTERN = "ver\\d+";

    private static final String BASE_PACKAGE_PATH = "io/github/pinkolik/general_classes_generator/test/";

    private static final String VERSION_CLASSES_BASE_PATH =
            "../test-classes/src/main/java/io/github/pinkolik/general_classes_generator/test/";

    private void baseCompareTwoFilesTest(final String[] filenames, final boolean makeSerializable)
            throws IOException, IllegalAccessException {
        FileUtils.deleteDirectory(new File(ACTUAL_PATH));
        GeneralClassesGeneratorImpl generator =
                new GeneralClassesGeneratorImpl(VERSION_CLASSES_BASE_PATH, VERSION_REGEX_PATTERN, ACTUAL_PATH);
        generator.setMakeSerializable(makeSerializable);
        generator.generate();

        for (String filename : filenames) {
            String expected = FileUtils.readFileToString(new File(EXPECTED_PATH + filename), StandardCharsets.UTF_8);

            String actual =
                    FileUtils.readFileToString(new File(ACTUAL_PATH + BASE_PACKAGE_PATH + filename), StandardCharsets.UTF_8);
            Assertions.assertEquals(expected, actual);
        }
    }

    private void baseCompareTwoFilesTest(final String[] filenames) throws IOException, IllegalAccessException {
        baseCompareTwoFilesTest(filenames, false);
    }

    @Test
    void simpleFieldsMergeTest() throws IOException, IllegalAccessException {
        baseCompareTwoFilesTest(new String[] {"Simple.java"});
    }

    @Test
    void simpleEnumsMergeTest() throws IOException, IllegalAccessException {
        baseCompareTwoFilesTest(new String[] {"Enum.java"});
    }

    @Test
    void singleInnerClassTest() throws IOException, IllegalAccessException {
        baseCompareTwoFilesTest(new String[] {"InnerClass.java"});
    }

    @Test
    void emptyClassTest() throws IOException, IllegalAccessException {
        baseCompareTwoFilesTest(new String[] {"Empty.java"});
    }

    @Test
    void innerClassWithInnerEnumTest() throws IOException, IllegalAccessException {
        baseCompareTwoFilesTest(new String[] {"InnerClassWithInnerEnum.java"});
    }

    @Test
    void innerClassWithGenericFieldTest() throws IOException, IllegalAccessException {
        baseCompareTwoFilesTest(new String[] {"InnerClassWithGenericField.java"});
    }

    @Test
    void classWithSimpleConstantTest() throws IOException, IllegalAccessException {
        baseCompareTwoFilesTest(new String[] {"ClassWithSimpleConstant.java"});
    }

    @Test
    void makeSerializableTrueTest() throws IOException, IllegalAccessException {
        baseCompareTwoFilesTest(new String[] {"SerializableTestClass.java"}, true);
    }

    @Test
    void enumIsNotSerializableWhenMakeSerializableTrueTest() throws IOException, IllegalAccessException {
        baseCompareTwoFilesTest(new String[] {"EnumIsNotSerializable.java"}, true);
    }

    @Test
    void inheritanceTest() throws IOException, IllegalAccessException {
        baseCompareTwoFilesTest(new String[] {"InheritanceTestClass.java", "ParentClass.java"});
    }

    @Test
    void inheritanceWithMakeSerializableTest() throws IOException, IllegalAccessException {
        baseCompareTwoFilesTest(new String[] {"InheritanceWithSerializableTestClass.java", "ParentWithSerializableClass.java"},
                                true);
    }

    @Test
    void enumWithField_fieldNotGenerated_Test() throws IOException, IllegalAccessException {
        baseCompareTwoFilesTest(new String[] {"EnumWithField.java"});
    }

    @Test
    void includeClassesRegexTest() throws IOException, IllegalAccessException {
        FileUtils.deleteDirectory(new File(ACTUAL_PATH));
        Set<String> includeClassesRegex = new HashSet<>();
        includeClassesRegex.add(".*Empty");
        includeClassesRegex.add(".*Simple");
        Generator generator = new GeneralClassesGeneratorImpl(VERSION_CLASSES_BASE_PATH, VERSION_REGEX_PATTERN, ACTUAL_PATH);
        generator.setIncludeClassesRegex(includeClassesRegex);

        generator.generate();

        Collection<File> actual = FileUtils.listFiles(new File(ACTUAL_PATH), new String[] {"java"}, true);
        Assertions.assertEquals(2, actual.size());
        for (String classesRegex : includeClassesRegex) {
            Pattern pattern = Pattern.compile(classesRegex);
            Assertions.assertTrue(actual.stream().anyMatch(
                    file -> pattern.matcher(GeneratorUtil.convertPathToClassToClassName(file.getAbsolutePath())).matches()));
        }
    }
}
