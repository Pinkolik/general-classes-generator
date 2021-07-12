package io.github.pinkolik.general_classes_generator.test.general.ver3;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import io.github.pinkolik.general_classes_generator.conversion.BaseMapper;

@Mapper(uses = {})
public interface EnumIsNotSerializableMapper extends
        BaseMapper<io.github.pinkolik.general_classes_generator.test.general.ver3.EnumIsNotSerializable, io.github.pinkolik.general_classes_generator.test.general.EnumIsNotSerializable> {

    EnumIsNotSerializableMapper INSTANCE = Mappers.getMapper(EnumIsNotSerializableMapper.class);

    default io.github.pinkolik.general_classes_generator.test.general.ver3.EnumIsNotSerializable mapGeneralToVersioned(io.github.pinkolik.general_classes_generator.test.general.EnumIsNotSerializable other) {
        return io.github.pinkolik.general_classes_generator.test.general.ver3.EnumIsNotSerializable.valueOf(other.name());
    }

    default io.github.pinkolik.general_classes_generator.test.general.EnumIsNotSerializable mapVersionedToGeneral(io.github.pinkolik.general_classes_generator.test.general.ver3.EnumIsNotSerializable other) {
        return io.github.pinkolik.general_classes_generator.test.general.EnumIsNotSerializable.valueOf(other.name());
    }

}