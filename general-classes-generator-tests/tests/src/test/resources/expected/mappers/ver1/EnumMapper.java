package io.github.pinkolik.general_classes_generator.test.ver1;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import io.github.pinkolik.general_classes_generator.conversion.BaseMapper;

@Mapper(uses = {})
public interface EnumMapper extends
        BaseMapper<io.github.pinkolik.general_classes_generator.test.ver1.Enum, io.github.pinkolik.general_classes_generator.test.Enum> {

    EnumMapper INSTANCE = Mappers.getMapper(EnumMapper.class);

    default io.github.pinkolik.general_classes_generator.test.ver1.Enum mapGeneralToVersioned(io.github.pinkolik.general_classes_generator.test.Enum other) {
        return io.github.pinkolik.general_classes_generator.test.ver1.Enum.valueOf(other.name());
    }

    default io.github.pinkolik.general_classes_generator.test.Enum mapVersionedToGeneral(io.github.pinkolik.general_classes_generator.test.ver1.Enum other) {
        return io.github.pinkolik.general_classes_generator.test.Enum.valueOf(other.name());
    }

}