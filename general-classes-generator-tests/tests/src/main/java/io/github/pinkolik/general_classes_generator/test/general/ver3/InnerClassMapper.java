package io.github.pinkolik.general_classes_generator.test.general.ver3;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import io.github.pinkolik.general_classes_generator.conversion.BaseMapper;

@Mapper(uses = {InnerClass_InnerMapper.class, })
public interface InnerClassMapper extends
        BaseMapper<io.github.pinkolik.general_classes_generator.test.general.ver3.InnerClass, io.github.pinkolik.general_classes_generator.test.general.InnerClass> {

    InnerClassMapper INSTANCE = Mappers.getMapper(InnerClassMapper.class);


}