package io.github.pinkolik.general_classes_generator.test.mappers.ver3;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;
import io.github.pinkolik.general_classes_generator.conversion.BaseMapper;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = {})
public interface InheritanceTestClassMapper extends
        BaseMapper<io.github.pinkolik.general_classes_generator.test.ver3.InheritanceTestClass, io.github.pinkolik.general_classes_generator.test.InheritanceTestClass> {

    InheritanceTestClassMapper INSTANCE = Mappers.getMapper(InheritanceTestClassMapper.class);


}