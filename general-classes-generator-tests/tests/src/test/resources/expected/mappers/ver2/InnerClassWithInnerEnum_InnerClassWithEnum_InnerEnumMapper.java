package io.github.pinkolik.general_classes_generator.test.mappers.ver2;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;
import io.github.pinkolik.general_classes_generator.conversion.BaseMapper;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = {})
public interface InnerClassWithInnerEnum_InnerClassWithEnum_InnerEnumMapper extends
        BaseMapper<io.github.pinkolik.general_classes_generator.test.ver2.InnerClassWithInnerEnum.InnerClassWithEnum.InnerEnum, io.github.pinkolik.general_classes_generator.test.InnerClassWithInnerEnum.InnerClassWithEnum.InnerEnum> {

    InnerClassWithInnerEnum_InnerClassWithEnum_InnerEnumMapper INSTANCE = Mappers.getMapper(InnerClassWithInnerEnum_InnerClassWithEnum_InnerEnumMapper.class);

    default io.github.pinkolik.general_classes_generator.test.ver2.InnerClassWithInnerEnum.InnerClassWithEnum.InnerEnum mapGeneralToVersioned(io.github.pinkolik.general_classes_generator.test.InnerClassWithInnerEnum.InnerClassWithEnum.InnerEnum other) {
        return io.github.pinkolik.general_classes_generator.test.ver2.InnerClassWithInnerEnum.InnerClassWithEnum.InnerEnum.valueOf(other.name());
    }

    default io.github.pinkolik.general_classes_generator.test.InnerClassWithInnerEnum.InnerClassWithEnum.InnerEnum mapVersionedToGeneral(io.github.pinkolik.general_classes_generator.test.ver2.InnerClassWithInnerEnum.InnerClassWithEnum.InnerEnum other) {
        return io.github.pinkolik.general_classes_generator.test.InnerClassWithInnerEnum.InnerClassWithEnum.InnerEnum.valueOf(other.name());
    }

}