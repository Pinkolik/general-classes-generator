package com.github.pinkolik.general_classes_generator.test.mappers.ver1;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.github.pinkolik.general_classes_generator.conversion.BaseMapper;

@Mapper
public interface SimpleMapper extends
        BaseMapper<com.github.pinkolik.general_classes_generator.test.mappers.ver1.Simple, com.github.pinkolik.general_classes_generator.test.mappers.Simple> {

    SimpleMapper INSTANCE = Mappers.getMapper(SimpleMapper.class);

}