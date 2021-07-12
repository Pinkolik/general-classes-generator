package io.github.pinkolik.general_classes_generator.test.general.ver1;

import lombok.Data;

@Data
public class InnerClass {

    private Inner inner;

    @Data
    public static class Inner {

        private String a;
    }
}
