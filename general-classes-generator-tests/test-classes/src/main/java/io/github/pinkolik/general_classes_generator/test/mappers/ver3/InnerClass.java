package io.github.pinkolik.general_classes_generator.test.mappers.ver3;

import lombok.Data;

@Data
public class InnerClass {

    private Inner inner;

    @Data
    public static class Inner {

        private Inner c;
    }
}
