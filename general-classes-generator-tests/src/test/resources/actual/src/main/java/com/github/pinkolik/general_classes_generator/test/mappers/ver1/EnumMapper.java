package com.github.pinkolik.general_classes_generator.test.mappers.ver1;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.github.pinkolik.general_classes_generator.conversion.BaseMapper;

@Mapper
public interface EnumMapper extends
        BaseMapper<com.github.pinkolik.general_classes_generator.test.mappers.ver1.Enum, com.github.pinkolik.general_classes_generator.test.mappers.Enum> {

    EnumMapper INSTANCE = Mappers.getMapper(EnumMapper.class);

}