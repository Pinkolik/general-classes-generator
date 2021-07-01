package io.github.pinkolik.general_classes_generator.conversion;

/**
 * Interface for converting general classes to versioned and vice versa.
 * @param <VERSIONED> versioned class.
 * @param <GENERAL> general class.
 */
public interface BaseMapper <VERSIONED, GENERAL> {

    /**
     * Convert versioned class to general.
     * @param versioned versioned class.
     * @return general class.
     */
    GENERAL mapVersionedToGeneral(VERSIONED versioned);

    /**
     * Convert general class to versioned.
     * @param general general class.
     * @return versioned class.
     */
    VERSIONED mapGeneralToVersioned(GENERAL general);
}

