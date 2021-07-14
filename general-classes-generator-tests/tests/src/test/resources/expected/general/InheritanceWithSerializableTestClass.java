package io.github.pinkolik.general_classes_generator.test;

import lombok.Data;
import lombok.Getter;
import lombok.EqualsAndHashCode;
import io.github.pinkolik.general_classes_generator.conversion.Generalized;

@Data
@EqualsAndHashCode(callSuper = true)
public class InheritanceWithSerializableTestClass extends io.github.pinkolik.general_classes_generator.test.ParentWithSerializableClass implements Generalized, java.io.Serializable {
    //GENERATED FIELDS START
    private int a;
    private java.lang.String b;
    private io.github.pinkolik.general_classes_generator.test.InheritanceWithSerializableTestClass c;
    //GENERATED FIELDS END
    //GENERATED INNER CLASSES START
    //${inner_classes}
    //GENERATED INNER CLASSES END
}