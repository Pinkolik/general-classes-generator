package com.github.pinkolik.general_classes_generator.test.mappers.ver3;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.github.pinkolik.general_classes_generator.conversion.BaseMapper;

@Mapper
public interface EmptyMapper extends
        BaseMapper<com.github.pinkolik.general_classes_generator.test.mappers.ver3.Empty, com.github.pinkolik.general_classes_generator.test.mappers.Empty> {

    EmptyMapper INSTANCE = Mappers.getMapper(EmptyMapper.class);

}