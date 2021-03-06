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

class MappersGeneratorImplTest {

    private final Logger log = LoggerFactory.getLogger(MappersGeneratorImplTest.class);

    private static final String ACTUAL_PATH =
            "src/test/resources/actual/src/main/java/io/github/pinkolik/general_classes_generator/test/mappers/";

    private static final String ACTUAL_BASE_PATH = "src/test/resources/actual/src/main/java/";

    private static final String EXPECTED_PATH = "src/test/resources/expected/mappers/";

    private static final String VERSION_REGEX_PATTERN = "ver\\d+";

    private static final String VERSION_CLASSES_BASE_PATH =
            "../test-classes/src/main/java/io/github/pinkolik/general_classes_generator/test/";

    private void baseCompareTwoFilesTestForAllVersions(final String[] filenames) throws IOException, IllegalAccessException {
        FileUtils.deleteDirectory(new File(ACTUAL_BASE_PATH));
        Generator generator = new MappersGeneratorImpl(VERSION_CLASSES_BASE_PATH, VERSION_REGEX_PATTERN, ACTUAL_PATH);
        generator.generate();
        for (String filename : filenames) {
            for (int i = 1; i < 4; i++) {
                String ver = String.format("ver%d/", i);
                String expected = FileUtils.readFileToString(new File(EXPECTED_PATH + ver + filename), StandardCharsets.UTF_8);

                String actual = FileUtils.readFileToString(new File(ACTUAL_PATH + ver + filename), StandardCharsets.UTF_8);
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

    @Test
    void enumMapperGeneratedTest() throws IOException, IllegalAccessException {
        baseCompareTwoFilesTestForAllVersions(new String[] {"EnumMapper.java"});
    }

    @Test
    void innerClassMapperGeneratedTest() throws IOException, IllegalAccessException {
        baseCompareTwoFilesTestForAllVersions(new String[] {"InnerClassMapper.java", "InnerClass_InnerMapper.java"});
    }

    @Test
    void innerClassWithInnerEnumGeneratedTest() throws IOException, IllegalAccessException {
        baseCompareTwoFilesTestForAllVersions(
                new String[] {"InnerClassWithInnerEnumMapper.java", "InnerClassWithInnerEnum_InnerClassWithEnumMapper.java",
                              "InnerClassWithInnerEnum_InnerClassWithEnum_InnerEnumMapper.java"});
    }

    @Test
    void includeClassesRegexTest() throws IOException, IllegalAccessException {
        FileUtils.deleteDirectory(new File(ACTUAL_PATH));
        Set<String> includeClassesRegex = new HashSet<>();
        includeClassesRegex.add(".*Empty");
        includeClassesRegex.add(".*Simple");
        Generator generator = new MappersGeneratorImpl(VERSION_CLASSES_BASE_PATH, VERSION_REGEX_PATTERN, ACTUAL_PATH);
        generator.setIncludeClassesRegex(includeClassesRegex);

        generator.generate();

        Collection<File> actual = FileUtils.listFiles(new File(ACTUAL_PATH), new String[] {"java"}, true);
        Assertions.assertEquals(6, actual.size());
        for (String classesRegex : includeClassesRegex) {
            Pattern pattern = Pattern.compile(classesRegex + "Mapper");
            Assertions.assertEquals(3, actual.stream().filter(file -> pattern.matcher(
                    GeneratorUtil.convertPathToClassToClassName(file.getAbsolutePath())).matches()).count());
        }
    }

}
