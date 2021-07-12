package io.github.pinkolik.general_classes_generator.test.general.ver1;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import io.github.pinkolik.general_classes_generator.conversion.BaseMapper;

@Mapper(uses = {})
public interface InheritanceWithSerializableTestClassMapper extends
        BaseMapper<io.github.pinkolik.general_classes_generator.test.general.ver1.InheritanceWithSerializableTestClass, io.github.pinkolik.general_classes_generator.test.general.InheritanceWithSerializableTestClass> {

    InheritanceWithSerializableTestClassMapper INSTANCE = Mappers.getMapper(InheritanceWithSerializableTestClassMapper.class);


}