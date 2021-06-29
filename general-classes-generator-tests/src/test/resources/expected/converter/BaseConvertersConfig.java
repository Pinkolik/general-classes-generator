package com.github.pinkolik.general_classes_generator.test.converter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.github.pinkolik.general_classes_generator.conversion.BaseMapper;
import com.github.pinkolik.general_classes_generator.conversion.BaseConverter;
import com.github.pinkolik.general_classes_generator.conversion.impl.BaseConverterImpl;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("ALL")
@Configuration
public class BaseConvertersConfig {

    @Bean
    public BaseConverter baseConverterVer1() {
        Map<Class, BaseMapper> generalClassesToMappers = new HashMap<>();
        Map<Class, BaseMapper> versionedClassesToMappers = new HashMap<>();
        generalClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.Empty.class, com.github.pinkolik.general_classes_generator.test.converter.EmptyMapper.INSTANCE);
        versionedClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.ver1.Empty.class, com.github.pinkolik.general_classes_generator.test.converter.EmptyMapper.INSTANCE);
        generalClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.Enum.class, com.github.pinkolik.general_classes_generator.test.converter.EnumMapper.INSTANCE);
        versionedClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.ver1.Enum.class, com.github.pinkolik.general_classes_generator.test.converter.EnumMapper.INSTANCE);
        generalClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.InnerClass.class, com.github.pinkolik.general_classes_generator.test.converter.InnerClassMapper.INSTANCE);
        versionedClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.ver1.InnerClass.class, com.github.pinkolik.general_classes_generator.test.converter.InnerClassMapper.INSTANCE);
        generalClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.Simple.class, com.github.pinkolik.general_classes_generator.test.converter.SimpleMapper.INSTANCE);
        versionedClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.ver1.Simple.class, com.github.pinkolik.general_classes_generator.test.converter.SimpleMapper.INSTANCE);
        return new BaseConverterImpl(generalClassesToMappers, versionedClassesToMappers);
    }

    @Bean
    public BaseConverter baseConverterVer2() {
        Map<Class, BaseMapper> generalClassesToMappers = new HashMap<>();
        Map<Class, BaseMapper> versionedClassesToMappers = new HashMap<>();
        generalClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.Empty.class, com.github.pinkolik.general_classes_generator.test.converter.EmptyMapper.INSTANCE);
        versionedClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.ver2.Empty.class, com.github.pinkolik.general_classes_generator.test.converter.EmptyMapper.INSTANCE);
        generalClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.Enum.class, com.github.pinkolik.general_classes_generator.test.converter.EnumMapper.INSTANCE);
        versionedClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.ver2.Enum.class, com.github.pinkolik.general_classes_generator.test.converter.EnumMapper.INSTANCE);
        generalClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.InnerClass.class, com.github.pinkolik.general_classes_generator.test.converter.InnerClassMapper.INSTANCE);
        versionedClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.ver2.InnerClass.class, com.github.pinkolik.general_classes_generator.test.converter.InnerClassMapper.INSTANCE);
        generalClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.Simple.class, com.github.pinkolik.general_classes_generator.test.converter.SimpleMapper.INSTANCE);
        versionedClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.ver2.Simple.class, com.github.pinkolik.general_classes_generator.test.converter.SimpleMapper.INSTANCE);
        return new BaseConverterImpl(generalClassesToMappers, versionedClassesToMappers);
    }

    @Bean
    public BaseConverter baseConverterVer3() {
        Map<Class, BaseMapper> generalClassesToMappers = new HashMap<>();
        Map<Class, BaseMapper> versionedClassesToMappers = new HashMap<>();
        generalClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.Empty.class, com.github.pinkolik.general_classes_generator.test.converter.EmptyMapper.INSTANCE);
        versionedClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.ver3.Empty.class, com.github.pinkolik.general_classes_generator.test.converter.EmptyMapper.INSTANCE);
        generalClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.Enum.class, com.github.pinkolik.general_classes_generator.test.converter.EnumMapper.INSTANCE);
        versionedClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.ver3.Enum.class, com.github.pinkolik.general_classes_generator.test.converter.EnumMapper.INSTANCE);
        generalClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.InnerClass.class, com.github.pinkolik.general_classes_generator.test.converter.InnerClassMapper.INSTANCE);
        versionedClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.ver3.InnerClass.class, com.github.pinkolik.general_classes_generator.test.converter.InnerClassMapper.INSTANCE);
        generalClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.Simple.class, com.github.pinkolik.general_classes_generator.test.converter.SimpleMapper.INSTANCE);
        versionedClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.ver3.Simple.class, com.github.pinkolik.general_classes_generator.test.converter.SimpleMapper.INSTANCE);
        return new BaseConverterImpl(generalClassesToMappers, versionedClassesToMappers);
    }

}