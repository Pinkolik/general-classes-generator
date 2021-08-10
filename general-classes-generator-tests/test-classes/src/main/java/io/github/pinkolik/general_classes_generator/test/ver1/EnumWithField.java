package io.github.pinkolik.general_classes_generator.test.ver1;

public enum EnumWithField {
    A("A");

    private final String value;

    EnumWithField(final String value) {this.value = value;}

    public String getValue() {
        return value;
    }
}
