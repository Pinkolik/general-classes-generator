package io.github.pinkolik.general_classes_generator.test.general;

import lombok.Data;
import lombok.Getter;

@Data
public class InnerClass {
    //GENERATED FIELDS START
    private io.github.pinkolik.general_classes_generator.test.general.InnerClass.Inner inner;
    //GENERATED FIELDS END
    //GENERATED INNER CLASSES START
    @Data
    public static class Inner {
        //GENERATED FIELDS START
        private java.lang.String a;
        private int b;
        private io.github.pinkolik.general_classes_generator.test.general.InnerClass.Inner c;
        //GENERATED FIELDS END
        //GENERATED INNER CLASSES START
        //${inner_classes}
        //GENERATED INNER CLASSES END
    }
    //GENERATED INNER CLASSES END
}