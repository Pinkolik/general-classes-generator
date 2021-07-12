package io.github.pinkolik.general_classes_generator.test.ver2;

import lombok.Data;

@Data
public class InnerClass {

    private Inner inner;

    @Data
    public static class Inner {

        private int b;
    }
}
