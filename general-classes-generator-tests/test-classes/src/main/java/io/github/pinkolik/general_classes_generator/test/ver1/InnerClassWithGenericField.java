package io.github.pinkolik.general_classes_generator.test.ver1;

import lombok.Data;

import java.util.List;

@Data
public class InnerClassWithGenericField {

    private List<Inner> a;

    @Data
    public static class Inner {

        private String a;

    }
}
