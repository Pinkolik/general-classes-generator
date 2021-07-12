package io.github.pinkolik.general_classes_generator.test.general.ver2;

import lombok.Data;

@Data
public class InnerClassWithInnerEnum {

    private String b;

    @Data
    public static class InnerClassWithEnum {

        private String innerB;

        public enum InnerEnum {
            D,
            E,
            F
        }
    }
}
