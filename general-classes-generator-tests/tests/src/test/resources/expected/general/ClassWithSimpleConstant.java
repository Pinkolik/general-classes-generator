package io.github.pinkolik.general_classes_generator.test;

import lombok.Data;
import lombok.Getter;
import lombok.EqualsAndHashCode;
import io.github.pinkolik.general_classes_generator.conversion.Generalized;

@Data
public class ClassWithSimpleConstant implements Generalized {
    //GENERATED FIELDS START
    @Getter
    private static final int A_ver1 = 1;
    @Getter
    private static final java.lang.String B_ver2 = "String";
    @Getter
    private static final double C_ver3 = 0.25;
    //GENERATED FIELDS END
    //GENERATED INNER CLASSES START
    //${inner_classes}
    //GENERATED INNER CLASSES END
}