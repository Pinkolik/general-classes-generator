package io.github.pinkolik.general_classes_generator.test.general.ver1;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import io.github.pinkolik.general_classes_generator.conversion.BaseMapper;

@Mapper(uses = {})
public interface InnerClassWithGenericField_InnerMapper extends
        BaseMapper<io.github.pinkolik.general_classes_generator.test.general.ver1.InnerClassWithGenericField.Inner, io.github.pinkolik.general_classes_generator.test.general.InnerClassWithGenericField.Inner> {

    InnerClassWithGenericField_InnerMapper INSTANCE = Mappers.getMapper(InnerClassWithGenericField_InnerMapper.class);


}