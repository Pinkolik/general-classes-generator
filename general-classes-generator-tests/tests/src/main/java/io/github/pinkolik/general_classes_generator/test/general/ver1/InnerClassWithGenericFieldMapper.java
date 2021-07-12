package io.github.pinkolik.general_classes_generator.test.general.ver1;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import io.github.pinkolik.general_classes_generator.conversion.BaseMapper;

@Mapper(uses = {InnerClassWithGenericField_InnerMapper.class, })
public interface InnerClassWithGenericFieldMapper extends
        BaseMapper<io.github.pinkolik.general_classes_generator.test.general.ver1.InnerClassWithGenericField, io.github.pinkolik.general_classes_generator.test.general.InnerClassWithGenericField> {

    InnerClassWithGenericFieldMapper INSTANCE = Mappers.getMapper(InnerClassWithGenericFieldMapper.class);


}