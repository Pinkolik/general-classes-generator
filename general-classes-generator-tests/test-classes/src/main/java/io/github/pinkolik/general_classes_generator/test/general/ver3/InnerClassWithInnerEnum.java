package io.github.pinkolik.general_classes_generator.test.general.ver3;

import lombok.Data;

@Data
public class InnerClassWithInnerEnum {

    private InnerClassWithEnum c;

    private InnerClassWithEnum.InnerEnum d;

    @Data
    public static class InnerClassWithEnum {

        private InnerClassWithEnum innerC;

        private InnerEnum innerD;

        public enum InnerEnum {
            G,
            H,
            I
        }
    }
}
