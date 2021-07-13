package io.github.pinkolik.general_classes_generator.test;

import lombok.Data;
import lombok.Getter;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class InheritanceTestClass extends io.github.pinkolik.general_classes_generator.test.ParentClass {
    //GENERATED FIELDS START
    private int a;
    private java.lang.String b;
    private io.github.pinkolik.general_classes_generator.test.InheritanceTestClass c;
    //GENERATED FIELDS END
    //GENERATED INNER CLASSES START
    //${inner_classes}
    //GENERATED INNER CLASSES END
}