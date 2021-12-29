package io.github.pinkolik.general_classes_generator.test.ver1;

import lombok.Data;

@Data
public class IgnoreNullTest {

    public static boolean aWasNotSet = true;

    private String a;

    private String d;

    public void setA(final String a) {
        this.a = a;
        aWasNotSet = false;
    }
}
