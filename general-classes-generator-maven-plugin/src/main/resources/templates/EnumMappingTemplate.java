    default ${versioned_enum} mapGeneralToVersioned(${general_enum} other) {
        return ${versioned_enum}.valueOf(other.name());
    }

    default ${general_enum} mapVersionedToGeneral(${versioned_enum} other) {
        return ${general_enum}.valueOf(other.name());
    }
