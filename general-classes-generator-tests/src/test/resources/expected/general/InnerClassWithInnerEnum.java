package io.github.pinkolik.general_classes_generator.test.general;

import lombok.Data;
import lombok.Getter;

@Data
public class InnerClassWithInnerEnum {
    //GENERATED FIELDS START
    private int a;
    private java.lang.String b;
    private io.github.pinkolik.general_classes_generator.test.general.InnerClassWithInnerEnum.InnerClassWithEnum c;
    private io.github.pinkolik.general_classes_generator.test.general.InnerClassWithInnerEnum.InnerClassWithEnum.InnerEnum d;
    //GENERATED FIELDS END
    //GENERATED INNER CLASSES START
    @Data
    public static class InnerClassWithEnum {
        //GENERATED FIELDS START
        private int innerA;
        private java.lang.String innerB;
        private io.github.pinkolik.general_classes_generator.test.general.InnerClassWithInnerEnum.InnerClassWithEnum innerC;
        private io.github.pinkolik.general_classes_generator.test.general.InnerClassWithInnerEnum.InnerClassWithEnum.InnerEnum innerD;
        //GENERATED FIELDS END
        //GENERATED INNER CLASSES START

        public static enum InnerEnum {
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