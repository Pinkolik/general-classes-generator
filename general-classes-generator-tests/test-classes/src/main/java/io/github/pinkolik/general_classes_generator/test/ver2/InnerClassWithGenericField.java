package io.github.pinkolik.general_classes_generator.test.ver2;

import lombok.Data;

import java.util.Set;

@Data
public class InnerClassWithGenericField {

    private Set<Inner> b;

    @Data
    public static class Inner {

        private int b;

    }
}
