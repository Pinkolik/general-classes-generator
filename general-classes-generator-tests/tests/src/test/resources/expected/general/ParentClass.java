package io.github.pinkolik.general_classes_generator.test;

import lombok.Data;
import lombok.Getter;
import lombok.EqualsAndHashCode;
import io.github.pinkolik.general_classes_generator.conversion.Generalized;

@Data
public class ParentClass implements Generalized {
    //GENERATED FIELDS START
    private int parentA;
    private java.lang.String parentB;
    private io.github.pinkolik.general_classes_generator.test.ParentClass parentC;
    //GENERATED FIELDS END
    //GENERATED INNER CLASSES START
    //${inner_classes}
    //GENERATED INNER CLASSES END
}