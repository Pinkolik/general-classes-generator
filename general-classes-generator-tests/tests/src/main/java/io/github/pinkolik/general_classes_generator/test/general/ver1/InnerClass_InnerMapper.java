package io.github.pinkolik.general_classes_generator.test.general.ver1;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import io.github.pinkolik.general_classes_generator.conversion.BaseMapper;

@Mapper(uses = {})
public interface InnerClass_InnerMapper extends
        BaseMapper<io.github.pinkolik.general_classes_generator.test.general.ver1.InnerClass.Inner, io.github.pinkolik.general_classes_generator.test.general.InnerClass.Inner> {

    InnerClass_InnerMapper INSTANCE = Mappers.getMapper(InnerClass_InnerMapper.class);


}