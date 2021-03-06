package io.github.pinkolik.general_classes_generator.test;

import lombok.Data;
import lombok.Getter;
import lombok.EqualsAndHashCode;
import io.github.pinkolik.general_classes_generator.conversion.Generalized;

@Data
public class InnerClassWithGenericField implements Generalized {
    //GENERATED FIELDS START
    private java.util.List<io.github.pinkolik.general_classes_generator.test.InnerClassWithGenericField.Inner> a;
    private java.util.Set<io.github.pinkolik.general_classes_generator.test.InnerClassWithGenericField.Inner> b;
    private java.util.Map<java.lang.String, io.github.pinkolik.general_classes_generator.test.InnerClassWithGenericField.Inner> c;
    //GENERATED FIELDS END
    //GENERATED INNER CLASSES START
    @Data
    public static class Inner implements Generalized {
        //GENERATED FIELDS START
        private java.lang.String a;
        private int b;
        private io.github.pinkolik.general_classes_generator.test.InnerClassWithGenericField.Inner c;
        //GENERATED FIELDS END
        //GENERATED INNER CLASSES START
        //${inner_classes}
        //GENERATED INNER CLASSES END
    }
    //GENERATED INNER CLASSES END
}