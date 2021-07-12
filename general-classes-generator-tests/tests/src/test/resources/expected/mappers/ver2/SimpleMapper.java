package io.github.pinkolik.general_classes_generator.test.ver2;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import io.github.pinkolik.general_classes_generator.conversion.BaseMapper;

@Mapper
public interface SimpleMapper extends
        BaseMapper<io.github.pinkolik.general_classes_generator.test.ver2.Simple, io.github.pinkolik.general_classes_generator.test.Simple> {

    SimpleMapper INSTANCE = Mappers.getMapper(SimpleMapper.class);

}