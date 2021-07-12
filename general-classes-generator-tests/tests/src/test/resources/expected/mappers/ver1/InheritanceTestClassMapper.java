package io.github.pinkolik.general_classes_generator.test.ver1;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import io.github.pinkolik.general_classes_generator.conversion.BaseMapper;

@Mapper
public interface InheritanceTestClassMapper extends
        BaseMapper<io.github.pinkolik.general_classes_generator.test.ver1.InheritanceTestClass, io.github.pinkolik.general_classes_generator.test.InheritanceTestClass> {

    InheritanceTestClassMapper INSTANCE = Mappers.getMapper(InheritanceTestClassMapper.class);

}