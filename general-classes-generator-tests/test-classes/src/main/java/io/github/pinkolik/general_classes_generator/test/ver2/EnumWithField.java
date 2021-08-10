package io.github.pinkolik.general_classes_generator.test.ver2;

public enum EnumWithField {
    B("B");

    private final String value;

    EnumWithField(final String value) {this.value = value;}

    public String getValue() {
        return value;
    }
}
