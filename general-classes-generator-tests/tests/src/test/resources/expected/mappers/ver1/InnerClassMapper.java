package io.github.pinkolik.general_classes_generator.test.mappers.ver1;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import io.github.pinkolik.general_classes_generator.conversion.BaseMapper;

@Mapper(uses = {InnerClass_InnerMapper.class, })
public interface InnerClassMapper extends
        BaseMapper<io.github.pinkolik.general_classes_generator.test.ver1.InnerClass, io.github.pinkolik.general_classes_generator.test.InnerClass> {

    InnerClassMapper INSTANCE = Mappers.getMapper(InnerClassMapper.class);


}