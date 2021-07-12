package ${package_name};

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ${base_mapper_name};

@Mapper(uses = {${additional_mappers}})
public interface ${mapper_name} extends
        ${base_mapper_simple_name}<${versioned_class_name}, ${general_class_name}> {

    ${mapper_name} INSTANCE = Mappers.getMapper(${mapper_name}.class);

${additional_mappings}
}