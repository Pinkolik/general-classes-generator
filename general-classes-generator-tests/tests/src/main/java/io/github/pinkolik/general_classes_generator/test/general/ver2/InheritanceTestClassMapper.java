package io.github.pinkolik.general_classes_generator.test.general.ver2;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import io.github.pinkolik.general_classes_generator.conversion.BaseMapper;

@Mapper(uses = {})
public interface InheritanceTestClassMapper extends
        BaseMapper<io.github.pinkolik.general_classes_generator.test.general.ver2.InheritanceTestClass, io.github.pinkolik.general_classes_generator.test.general.InheritanceTestClass> {

    InheritanceTestClassMapper INSTANCE = Mappers.getMapper(InheritanceTestClassMapper.class);


}