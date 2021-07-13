package io.github.pinkolik.general_classes_generator.conversion.impl;

import io.github.pinkolik.general_classes_generator.conversion.BaseConverter;
import io.github.pinkolik.general_classes_generator.test.Simple;
import io.github.pinkolik.general_classes_generator.test.converter.BaseConvertersConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BaseConvertersConfig.class})
@ExtendWith(SpringExtension.class)
public class BaseConverterImplTest {

    @Autowired
    private BaseConverter baseConverterVer1;

    /*********************************************
     * 1. Wrong package test                      *
     * 2. Only generalized can be passed test     *
     * 3. Simple convert                          *
     * 4. Convert when unboxed to object          *
     * 5. Collection convert                      *
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
}
