package io.github.pinkolik.general_classes_generator.test.general.ver2;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import io.github.pinkolik.general_classes_generator.conversion.BaseMapper;

@Mapper(uses = {})
public interface SimpleMapper extends
        BaseMapper<io.github.pinkolik.general_classes_generator.test.general.ver2.Simple, io.github.pinkolik.general_classes_generator.test.general.Simple> {

    SimpleMapper INSTANCE = Mappers.getMapper(SimpleMapper.class);


}