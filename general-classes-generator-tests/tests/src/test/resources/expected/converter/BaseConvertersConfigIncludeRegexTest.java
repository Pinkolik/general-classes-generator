package io.github.pinkolik.general_classes_generator.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.github.pinkolik.general_classes_generator.conversion.BaseMapper;
import io.github.pinkolik.general_classes_generator.conversion.BaseConverter;
import io.github.pinkolik.general_classes_generator.conversion.impl.BaseConverterImpl;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("ALL")
@Configuration
public class BaseConvertersConfig {

    @Bean
    public BaseConverter baseConverterVer1() {
        Map<Class, BaseMapper> generalClassesToMappers = new HashMap<>();
        Map<Class, BaseMapper> versionedClassesToMappers = new HashMap<>();
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.Empty.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.EmptyMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver1.Empty.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.EmptyMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.Simple.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.SimpleMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver1.Simple.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.SimpleMapper.INSTANCE);
        return new BaseConverterImpl(generalClassesToMappers, versionedClassesToMappers, "io.github.pinkolik.general_classes_generator.test");
    }

    @Bean
    public BaseConverter baseConverterVer2() {
        Map<Class, BaseMapper> generalClassesToMappers = new HashMap<>();
        Map<Class, BaseMapper> versionedClassesToMappers = new HashMap<>();
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.Empty.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.EmptyMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver2.Empty.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.EmptyMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.Simple.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.SimpleMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver2.Simple.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.SimpleMapper.INSTANCE);
        return new BaseConverterImpl(generalClassesToMappers, versionedClassesToMappers, "io.github.pinkolik.general_classes_generator.test");
    }

    @Bean
    public BaseConverter baseConverterVer3() {
        Map<Class, BaseMapper> generalClassesToMappers = new HashMap<>();
        Map<Class, BaseMapper> versionedClassesToMappers = new HashMap<>();
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.Empty.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.EmptyMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver3.Empty.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.EmptyMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.Simple.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.SimpleMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver3.Simple.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.SimpleMapper.INSTANCE);
        return new BaseConverterImpl(generalClassesToMappers, versionedClassesToMappers, "io.github.pinkolik.general_classes_generator.test");
    }

}