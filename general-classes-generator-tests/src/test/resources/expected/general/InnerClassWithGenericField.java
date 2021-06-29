package com.github.pinkolik.general_classes_generator.test.general;

import lombok.Data;
import lombok.Getter;

@Data
public class InnerClassWithGenericField {
    //GENERATED FIELDS START
    private java.util.List<com.github.pinkolik.general_classes_generator.test.general.InnerClassWithGenericField.Inner> a;
    private java.util.Set<com.github.pinkolik.general_classes_generator.test.general.InnerClassWithGenericField.Inner> b;
    private java.util.Map<java.lang.String, com.github.pinkolik.general_classes_generator.test.general.InnerClassWithGenericField.Inner> c;
    //GENERATED FIELDS END
    //GENERATED INNER CLASSES START
    @Data
    public static class Inner {
        //GENERATED FIELDS START

        //GENERATED FIELDS END
        //GENERATED INNER CLASSES START
        //${inner_classes}
        //GENERATED INNER CLASSES END
    }
    //GENERATED INNER CLASSES END
}