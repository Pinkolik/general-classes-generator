package io.github.pinkolik.general_classes_generator.test.general.ver2;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import io.github.pinkolik.general_classes_generator.conversion.BaseMapper;

@Mapper(uses = {})
public interface ClassWithSimpleConstantMapper extends
        BaseMapper<io.github.pinkolik.general_classes_generator.test.general.ver2.ClassWithSimpleConstant, io.github.pinkolik.general_classes_generator.test.general.ClassWithSimpleConstant> {

    ClassWithSimpleConstantMapper INSTANCE = Mappers.getMapper(ClassWithSimpleConstantMapper.class);


}