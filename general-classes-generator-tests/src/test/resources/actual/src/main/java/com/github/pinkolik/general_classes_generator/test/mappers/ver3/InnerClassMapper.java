package com.github.pinkolik.general_classes_generator.test.mappers.ver3;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.github.pinkolik.general_classes_generator.conversion.BaseMapper;

@Mapper
public interface InnerClassMapper extends
        BaseMapper<com.github.pinkolik.general_classes_generator.test.mappers.ver3.InnerClass, com.github.pinkolik.general_classes_generator.test.mappers.InnerClass> {

    InnerClassMapper INSTANCE = Mappers.getMapper(InnerClassMapper.class);

}