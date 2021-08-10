package io.github.pinkolik.general_classes_generator.test.ver3;

public enum EnumWithField {
    C("C");

    private final String value;

    EnumWithField(final String value) {this.value = value;}

    public String getValue() {
        return value;
    }
}
