package io.github.pinkolik.general_classes_generator.test.general;

import lombok.Data;
import lombok.Getter;

@Data
public class InheritanceWithSerializableTestClass extends io.github.pinkolik.general_classes_generator.test.general.ParentWithSerializableClass implements java.io.Serializable {
    //GENERATED FIELDS START
    private int a;
    private java.lang.String b;
    private io.github.pinkolik.general_classes_generator.test.general.InheritanceWithSerializableTestClass c;
    //GENERATED FIELDS END
    //GENERATED INNER CLASSES START
    //${inner_classes}
    //GENERATED INNER CLASSES END
}