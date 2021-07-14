package io.github.pinkolik.general_classes_generator.test;

import lombok.Data;
import lombok.Getter;
import lombok.EqualsAndHashCode;
import io.github.pinkolik.general_classes_generator.conversion.Generalized;

@Data
@EqualsAndHashCode(callSuper = true)
public class InheritanceTestClass extends io.github.pinkolik.general_classes_generator.test.ParentClass implements Generalized {
    //GENERATED FIELDS START
    private int a;
    private java.lang.String b;
    private io.github.pinkolik.general_classes_generator.test.InheritanceTestClass c;
    //GENERATED FIELDS END
    //GENERATED INNER CLASSES START
    //${inner_classes}
    //GENERATED INNER CLASSES END
}