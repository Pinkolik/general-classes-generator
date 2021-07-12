package io.github.pinkolik.general_classes_generator.test.general.ver2;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import io.github.pinkolik.general_classes_generator.conversion.BaseMapper;

@Mapper(uses = {})
public interface EnumMapper extends
        BaseMapper<io.github.pinkolik.general_classes_generator.test.general.ver2.Enum, io.github.pinkolik.general_classes_generator.test.general.Enum> {

    EnumMapper INSTANCE = Mappers.getMapper(EnumMapper.class);

    default io.github.pinkolik.general_classes_generator.test.general.ver2.Enum mapGeneralToVersioned(io.github.pinkolik.general_classes_generator.test.general.Enum other) {
        return io.github.pinkolik.general_classes_generator.test.general.ver2.Enum.valueOf(other.name());
    }

    default io.github.pinkolik.general_classes_generator.test.general.Enum mapVersionedToGeneral(io.github.pinkolik.general_classes_generator.test.general.ver2.Enum other) {
        return io.github.pinkolik.general_classes_generator.test.general.Enum.valueOf(other.name());
    }

}