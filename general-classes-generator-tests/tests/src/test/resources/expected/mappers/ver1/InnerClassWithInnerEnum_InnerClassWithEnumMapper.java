package io.github.pinkolik.general_classes_generator.test.mappers.ver1;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;
import io.github.pinkolik.general_classes_generator.conversion.BaseMapper;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = {InnerClassWithInnerEnum_InnerClassWithEnum_InnerEnumMapper.class, })
public interface InnerClassWithInnerEnum_InnerClassWithEnumMapper extends
        BaseMapper<io.github.pinkolik.general_classes_generator.test.ver1.InnerClassWithInnerEnum.InnerClassWithEnum, io.github.pinkolik.general_classes_generator.test.InnerClassWithInnerEnum.InnerClassWithEnum> {

    InnerClassWithInnerEnum_InnerClassWithEnumMapper INSTANCE = Mappers.getMapper(InnerClassWithInnerEnum_InnerClassWithEnumMapper.class);


}