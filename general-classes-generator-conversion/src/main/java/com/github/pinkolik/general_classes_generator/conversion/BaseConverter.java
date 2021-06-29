package com.github.pinkolik.general_classes_generator.conversion;

public interface BaseConverter {

    Object convertToVersionedClass(final Object general);

    Object convertToGeneralClass(final Object versioned);
}
