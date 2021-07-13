package io.github.pinkolik.general_classes_generator.test.ver2;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import io.github.pinkolik.general_classes_generator.conversion.BaseMapper;

@Mapper(uses = {})
public interface ParentClassMapper extends
        BaseMapper<io.github.pinkolik.general_classes_generator.test.ver2.ParentClass, io.github.pinkolik.general_classes_generator.test.ParentClass> {

    ParentClassMapper INSTANCE = Mappers.getMapper(ParentClassMapper.class);


}