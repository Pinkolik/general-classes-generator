package io.github.pinkolik.general_classes_generator.test.mappers.ver3;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import io.github.pinkolik.general_classes_generator.conversion.BaseMapper;

@Mapper
public interface ParentClassMapper extends
        BaseMapper<io.github.pinkolik.general_classes_generator.test.mappers.ver3.ParentClass, io.github.pinkolik.general_classes_generator.test.mappers.ParentClass> {

    ParentClassMapper INSTANCE = Mappers.getMapper(ParentClassMapper.class);

}