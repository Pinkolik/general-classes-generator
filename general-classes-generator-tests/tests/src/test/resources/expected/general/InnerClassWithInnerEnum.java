package io.github.pinkolik.general_classes_generator.test;

import lombok.Data;
import lombok.Getter;
import lombok.EqualsAndHashCode;
import io.github.pinkolik.general_classes_generator.conversion.Generalized;

@Data
public class InnerClassWithInnerEnum implements Generalized {
    //GENERATED FIELDS START
    private int a;
    private java.lang.String b;
    private io.github.pinkolik.general_classes_generator.test.InnerClassWithInnerEnum.InnerClassWithEnum c;
    private io.github.pinkolik.general_classes_generator.test.InnerClassWithInnerEnum.InnerClassWithEnum.InnerEnum d;
    //GENERATED FIELDS END
    //GENERATED INNER CLASSES START
    @Data
    public static class InnerClassWithEnum implements Generalized {
        //GENERATED FIELDS START
        private int innerA;
        private java.lang.String innerB;
        private io.github.pinkolik.general_classes_generator.test.InnerClassWithInnerEnum.InnerClassWithEnum innerC;
        private io.github.pinkolik.general_classes_generator.test.InnerClassWithInnerEnum.InnerClassWithEnum.InnerEnum innerD;
        //GENERATED FIELDS END
        //GENERATED INNER CLASSES START
        public static enum InnerEnum implements Generalized {
            //GENERATED FIELDS START
            A,
            B,
            C,
            D,
            E,
            F,
            G,
            H,
            I
            //GENERATED FIELDS END
            //GENERATED INNER CLASSES START
            //${inner_classes}
            //GENERATED INNER CLASSES END
        }
        //GENERATED INNER CLASSES END
    }
    //GENERATED INNER CLASSES END
}