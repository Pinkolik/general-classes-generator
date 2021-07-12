package io.github.pinkolik.general_classes_generator.test.ver1;

import lombok.Data;

@Data
public class InnerClassWithInnerEnum {

    private int a;

    @Data
    public static class InnerClassWithEnum {

        private int innerA;

        public enum InnerEnum {
            A,
            B,
            C
        }
    }
}
