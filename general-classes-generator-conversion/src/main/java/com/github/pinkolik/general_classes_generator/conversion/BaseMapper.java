package com.github.pinkolik.general_classes_generator.conversion;

public interface BaseMapper <VERSIONED, GENERAL> {

    GENERAL mapVersionedToGeneral(VERSIONED versioned);

    VERSIONED mapGeneralToVersioned(GENERAL general);
}

