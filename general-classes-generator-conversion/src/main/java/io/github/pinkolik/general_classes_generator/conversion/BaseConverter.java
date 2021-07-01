package io.github.pinkolik.general_classes_generator.conversion;

/**
 * Used for converting general classes to versioned and vice versa.
 * It is different from {@link BaseMapper} as it can convert
 * multiple classes from the same version package.
 */
public interface BaseConverter {

    /**
     * Convert general class to versioned.
     * @param general general class.
     * @return versioned class.
     */
    Object convertToVersionedClass(final Object general);

    /**
     * Convert versioned class to general.
     * @param versioned versioned class.
     * @return general class.
     */
    Object convertToGeneralClass(final Object versioned);
}
