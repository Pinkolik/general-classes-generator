package ${package_name};

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ${base_mapper_class_name};
import ${base_converter_class_name};
import ${base_converter_impl_class_name};

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("ALL")
@Configuration
public class BaseConvertersConfig {
    ${converters_configs}
}