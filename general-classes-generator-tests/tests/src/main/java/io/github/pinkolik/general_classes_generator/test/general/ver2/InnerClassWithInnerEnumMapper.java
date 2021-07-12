package io.github.pinkolik.general_classes_generator.test.general.ver2;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import io.github.pinkolik.general_classes_generator.conversion.BaseMapper;

@Mapper(uses = {InnerClassWithInnerEnum_InnerClassWithEnumMapper.class, InnerClassWithInnerEnum_InnerClassWithEnum_InnerEnumMapper.class, })
public interface InnerClassWithInnerEnumMapper extends
        BaseMapper<io.github.pinkolik.general_classes_generator.test.general.ver2.InnerClassWithInnerEnum, io.github.pinkolik.general_classes_generator.test.general.InnerClassWithInnerEnum> {

    InnerClassWithInnerEnumMapper INSTANCE = Mappers.getMapper(InnerClassWithInnerEnumMapper.class);


}