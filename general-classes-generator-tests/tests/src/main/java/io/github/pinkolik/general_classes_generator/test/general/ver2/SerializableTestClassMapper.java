package io.github.pinkolik.general_classes_generator.test.general.ver2;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import io.github.pinkolik.general_classes_generator.conversion.BaseMapper;

@Mapper(uses = {})
public interface SerializableTestClassMapper extends
        BaseMapper<io.github.pinkolik.general_classes_generator.test.general.ver2.SerializableTestClass, io.github.pinkolik.general_classes_generator.test.general.SerializableTestClass> {

    SerializableTestClassMapper INSTANCE = Mappers.getMapper(SerializableTestClassMapper.class);


}