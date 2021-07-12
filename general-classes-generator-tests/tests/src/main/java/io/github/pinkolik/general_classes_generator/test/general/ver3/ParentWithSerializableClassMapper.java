package io.github.pinkolik.general_classes_generator.test.general.ver3;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import io.github.pinkolik.general_classes_generator.conversion.BaseMapper;

@Mapper(uses = {})
public interface ParentWithSerializableClassMapper extends
        BaseMapper<io.github.pinkolik.general_classes_generator.test.general.ver3.ParentWithSerializableClass, io.github.pinkolik.general_classes_generator.test.general.ParentWithSerializableClass> {

    ParentWithSerializableClassMapper INSTANCE = Mappers.getMapper(ParentWithSerializableClassMapper.class);


}