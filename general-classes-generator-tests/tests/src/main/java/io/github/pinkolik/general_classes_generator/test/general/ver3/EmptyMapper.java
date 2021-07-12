package io.github.pinkolik.general_classes_generator.test.general.ver3;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import io.github.pinkolik.general_classes_generator.conversion.BaseMapper;

@Mapper(uses = {})
public interface EmptyMapper extends
        BaseMapper<io.github.pinkolik.general_classes_generator.test.general.ver3.Empty, io.github.pinkolik.general_classes_generator.test.general.Empty> {

    EmptyMapper INSTANCE = Mappers.getMapper(EmptyMapper.class);


}