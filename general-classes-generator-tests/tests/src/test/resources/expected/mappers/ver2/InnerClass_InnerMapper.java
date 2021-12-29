package io.github.pinkolik.general_classes_generator.test.mappers.ver2;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;
import io.github.pinkolik.general_classes_generator.conversion.BaseMapper;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = {})
public interface InnerClass_InnerMapper extends
        BaseMapper<io.github.pinkolik.general_classes_generator.test.ver2.InnerClass.Inner, io.github.pinkolik.general_classes_generator.test.InnerClass.Inner> {

    InnerClass_InnerMapper INSTANCE = Mappers.getMapper(InnerClass_InnerMapper.class);


}