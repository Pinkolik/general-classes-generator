package io.github.pinkolik.general_classes_generator.test.ver3;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import io.github.pinkolik.general_classes_generator.conversion.BaseMapper;

@Mapper(uses = {})
public interface SimpleMapper extends
        BaseMapper<io.github.pinkolik.general_classes_generator.test.ver3.Simple, io.github.pinkolik.general_classes_generator.test.Simple> {

    SimpleMapper INSTANCE = Mappers.getMapper(SimpleMapper.class);


}