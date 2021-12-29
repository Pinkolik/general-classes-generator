package io.github.pinkolik.general_classes_generator.conversion.impl;

import io.github.pinkolik.general_classes_generator.conversion.BaseConverter;
import io.github.pinkolik.general_classes_generator.conversion.Generalized;
import io.github.pinkolik.general_classes_generator.test.IgnoreNullTest;
import io.github.pinkolik.general_classes_generator.test.InnerClassWithGenericField;
import io.github.pinkolik.general_classes_generator.test.Simple;
import io.github.pinkolik.general_classes_generator.test.UpcastingTestClass;
import io.github.pinkolik.general_classes_generator.test.converter.BaseConvertersConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@ContextConfiguration(classes = {BaseConvertersConfig.class})
@ExtendWith(SpringExtension.class)
public class BaseConverterImplTest {

    @Autowired
    @Qualifier("baseConverterVer1")
    private BaseConverter baseConverterVer1;

    /*********************************************
     * 1. Wrong package test                      *
     * 2. Only generalized can be passed test     *
     * 3. Simple convert                          *
     * 4. Convert when upcasting to object        *
     * 5. Collection convert                      *
     * 6. Null check                              *
     **********************************************/


    @Test
    void simpleGeneralToVersionedTest() {
        Simple simple = new Simple();
        simple.setA("a");
        simple.setB(5);
        simple.setC(new Simple());

        Object result = baseConverterVer1.convertToVersionedClass(simple);

        Assertions.assertTrue(result instanceof io.github.pinkolik.general_classes_generator.test.ver1.Simple);
        Assertions.assertEquals(simple.getA(), ((io.github.pinkolik.general_classes_generator.test.ver1.Simple) result).getA());
    }

    @Test
    void simpleVersionedToGeneralTest() {
        io.github.pinkolik.general_classes_generator.test.ver1.Simple simple =
                new io.github.pinkolik.general_classes_generator.test.ver1.Simple();
        simple.setA("a");

        Object result = baseConverterVer1.convertToGeneralClass(simple);

        Assertions.assertTrue(result instanceof Simple);
        Assertions.assertEquals(simple.getA(), ((Simple) result).getA());
    }

    @Test
    void upcastingGeneralToVersionedTest() {
        UpcastingTestClass any = new UpcastingTestClass();
        any.setAnyA("a");
        UpcastingTestClass upcastingTestClass = new UpcastingTestClass();
        upcastingTestClass.setAnyA(any);

        Object result = baseConverterVer1.convertToVersionedClass(upcastingTestClass);

        Assertions.assertTrue(result instanceof io.github.pinkolik.general_classes_generator.test.ver1.UpcastingTestClass);
        Object resultA = ((io.github.pinkolik.general_classes_generator.test.ver1.UpcastingTestClass) result).getAnyA();
        Assertions.assertTrue(resultA instanceof io.github.pinkolik.general_classes_generator.test.ver1.UpcastingTestClass);
        Object anyA = ((io.github.pinkolik.general_classes_generator.test.ver1.UpcastingTestClass) resultA).getAnyA();
        Assertions.assertTrue(anyA instanceof String);
        Assertions.assertEquals("a", anyA);
    }

    @Test
    void upcastingVersionedToGeneralTest() {
        io.github.pinkolik.general_classes_generator.test.ver1.UpcastingTestClass any =
                new io.github.pinkolik.general_classes_generator.test.ver1.UpcastingTestClass();
        any.setAnyA("a");
        io.github.pinkolik.general_classes_generator.test.ver1.UpcastingTestClass upcastingTestClass =
                new io.github.pinkolik.general_classes_generator.test.ver1.UpcastingTestClass();
        upcastingTestClass.setAnyA(any);

        Object result = baseConverterVer1.convertToGeneralClass(upcastingTestClass);

        Assertions.assertTrue(result instanceof UpcastingTestClass);
        Object resultA = ((UpcastingTestClass) result).getAnyA();
        Assertions.assertTrue(resultA instanceof UpcastingTestClass);
        Object anyA = ((UpcastingTestClass) resultA).getAnyA();
        Assertions.assertTrue(anyA instanceof String);
        Assertions.assertEquals("a", anyA);
    }

    @Test
    void mapperNotFoundIsThrownVersionedToGeneralTest() {
        io.github.pinkolik.general_classes_generator.test.ver2.Simple simple =
                new io.github.pinkolik.general_classes_generator.test.ver2.Simple();
        simple.setB(10);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            baseConverterVer1.convertToGeneralClass(simple);
        }, "Mapper for class io.github.pinkolik.general_classes_generator.test.ver2.Simple not found");
    }

    @Test
    void mapperNotFoundIsThrownGeneralToVersionedTest() {
        ClassWithoutMapper obj = new ClassWithoutMapper();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
                                    baseConverterVer1.convertToVersionedClass(obj);
                                },
                                "Mapper for class io.github.pinkolik.general_classes_generator.conversion.impl.BaseConverterImplTest.ClassWithoutMapper not found");
    }

    @Test
    void onlyGeneralizedAllowedIsThrownGeneralToVersionedTest() {
        io.github.pinkolik.general_classes_generator.test.ver2.Simple simple =
                new io.github.pinkolik.general_classes_generator.test.ver2.Simple();
        simple.setB(10);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            baseConverterVer1.convertToVersionedClass(simple);
        }, "Only classes implementing io.github.pinkolik.general_classes_generator.conversion.Generalized are allowed.");
    }

    @Test
    void versionedToGeneralReturnsNullWhenNullPassedTest() {
        Assertions.assertNull(baseConverterVer1.convertToGeneralClass(null));
    }

    @Test
    void generalToVersionedReturnsNullWhenNullPassedTest() {
        Assertions.assertNull(baseConverterVer1.convertToVersionedClass(null));
    }

    @Test
    void generalToVersionedCollectionTest() {
        InnerClassWithGenericField obj = new InnerClassWithGenericField();
        List<InnerClassWithGenericField.Inner> innerList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            InnerClassWithGenericField.Inner inner = new InnerClassWithGenericField.Inner();
            inner.setA(Integer.toString(i));
            innerList.add(inner);
        }
        obj.setA(innerList);

        Object result = baseConverterVer1.convertToVersionedClass(obj);

        Assertions.assertTrue(
                result instanceof io.github.pinkolik.general_classes_generator.test.ver1.InnerClassWithGenericField);
        List<io.github.pinkolik.general_classes_generator.test.ver1.InnerClassWithGenericField.Inner> resultList =
                ((io.github.pinkolik.general_classes_generator.test.ver1.InnerClassWithGenericField) result).getA();
        Assertions.assertEquals(3, resultList.size());
        for (int i = 0; i < 3; i++) {
            InnerClassWithGenericField.Inner inner = innerList.get(i);
            io.github.pinkolik.general_classes_generator.test.ver1.InnerClassWithGenericField.Inner innerResult =
                    resultList.get(i);
            Assertions.assertEquals(inner.getA(), innerResult.getA());
        }
    }

    @Test
    void versionedToGeneralCollectionTest() {
        io.github.pinkolik.general_classes_generator.test.ver1.InnerClassWithGenericField obj =
                new io.github.pinkolik.general_classes_generator.test.ver1.InnerClassWithGenericField();
        List<io.github.pinkolik.general_classes_generator.test.ver1.InnerClassWithGenericField.Inner> innerList =
                new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            io.github.pinkolik.general_classes_generator.test.ver1.InnerClassWithGenericField.Inner inner =
                    new io.github.pinkolik.general_classes_generator.test.ver1.InnerClassWithGenericField.Inner();
            inner.setA(Integer.toString(i));
            innerList.add(inner);
        }
        obj.setA(innerList);

        Object result = baseConverterVer1.convertToGeneralClass(obj);

        Assertions.assertTrue(result instanceof InnerClassWithGenericField);
        List<InnerClassWithGenericField.Inner> resultList = ((InnerClassWithGenericField) result).getA();
        Assertions.assertEquals(3, resultList.size());
        for (int i = 0; i < 3; i++) {
            io.github.pinkolik.general_classes_generator.test.ver1.InnerClassWithGenericField.Inner inner = innerList.get(i);
            InnerClassWithGenericField.Inner innerResult = resultList.get(i);
            Assertions.assertEquals(inner.getA(), innerResult.getA());
        }
    }

    @Test
    void generalToVersionedUpcastingCollectionTest() {
        UpcastingTestClass obj = new UpcastingTestClass();
        List<Object> list = new ArrayList<>();
        obj.setList(list);
        for (int i = 0; i < 3; i++) {
            Simple simple = new Simple();
            simple.setA(Integer.toString(i));
            list.add(simple);
        }

        Object result = baseConverterVer1.convertToVersionedClass(obj);

        Assertions.assertTrue(result instanceof io.github.pinkolik.general_classes_generator.test.ver1.UpcastingTestClass);
        List<Object> resultList = ((io.github.pinkolik.general_classes_generator.test.ver1.UpcastingTestClass) result).getList();
        Assertions.assertEquals(3, resultList.size());
        for (int i = 0; i < 3; i++) {
            Simple simple = (Simple) list.get(i);
            Object listObj = resultList.get(i);
            Assertions.assertTrue(listObj instanceof io.github.pinkolik.general_classes_generator.test.ver1.Simple);
            io.github.pinkolik.general_classes_generator.test.ver1.Simple simpleResult =
                    (io.github.pinkolik.general_classes_generator.test.ver1.Simple) listObj;
            Assertions.assertEquals(simple.getA(), simpleResult.getA());
        }
    }

    @Test
    void versionedToGeneralUpcastingCollectionTest() {
        io.github.pinkolik.general_classes_generator.test.ver1.UpcastingTestClass obj =
                new io.github.pinkolik.general_classes_generator.test.ver1.UpcastingTestClass();
        List<Object> list = new ArrayList<>();
        obj.setList(list);
        for (int i = 0; i < 3; i++) {
            io.github.pinkolik.general_classes_generator.test.ver1.Simple simple =
                    new io.github.pinkolik.general_classes_generator.test.ver1.Simple();
            simple.setA(Integer.toString(i));
            list.add(simple);
        }

        Object result = baseConverterVer1.convertToGeneralClass(obj);

        Assertions.assertTrue(result instanceof UpcastingTestClass);
        List<Object> resultList = ((UpcastingTestClass) result).getList();
        Assertions.assertEquals(3, resultList.size());
        for (int i = 0; i < 3; i++) {
            io.github.pinkolik.general_classes_generator.test.ver1.Simple simple =
                    (io.github.pinkolik.general_classes_generator.test.ver1.Simple) list.get(i);
            Object listObj = resultList.get(i);
            Assertions.assertTrue(listObj instanceof Simple);
            Simple simpleResult = (Simple) listObj;
            Assertions.assertEquals(simple.getA(), simpleResult.getA());
        }
    }

    @Test
    void generalToVersionedIgnoreNullTest() {
        IgnoreNullTest ignoreNullTest = new IgnoreNullTest();
        ignoreNullTest.setD("test");

        io.github.pinkolik.general_classes_generator.test.ver1.IgnoreNullTest versioned =
                (io.github.pinkolik.general_classes_generator.test.ver1.IgnoreNullTest) baseConverterVer1.convertToVersionedClass(
                        ignoreNullTest);

        Assertions.assertEquals(ignoreNullTest.getD(), versioned.getD());
        Assertions.assertTrue(io.github.pinkolik.general_classes_generator.test.ver1.IgnoreNullTest.aWasNotSet);
    }

    public static class ClassWithoutMapper implements Generalized {}
}
