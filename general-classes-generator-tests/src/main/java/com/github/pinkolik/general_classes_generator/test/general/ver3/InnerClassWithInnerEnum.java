package com.github.pinkolik.general_classes_generator.test.general.ver3;

public class InnerClassWithInnerEnum {

    private InnerClassWithEnum c;

    private InnerClassWithEnum.InnerEnum d;

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
