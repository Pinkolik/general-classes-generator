package io.github.pinkolik.general_classes_generator.test.ver3;

import lombok.Data;

import java.util.Map;

@Data
public class InnerClassWithGenericField {

    private Map<String, Inner> c;

    @Data
    public static class Inner {

    }
}
