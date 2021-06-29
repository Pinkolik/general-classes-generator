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
        generalClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.Empty.class, com.github.pinkolik.general_classes_generator.test.converter.ver1.EmptyMapper.INSTANCE);
        versionedClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.ver1.Empty.class, com.github.pinkolik.general_classes_generator.test.converter.ver1.EmptyMapper.INSTANCE);
        generalClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.Enum.class, com.github.pinkolik.general_classes_generator.test.converter.ver1.EnumMapper.INSTANCE);
        versionedClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.ver1.Enum.class, com.github.pinkolik.general_classes_generator.test.converter.ver1.EnumMapper.INSTANCE);
        generalClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.InnerClass.class, com.github.pinkolik.general_classes_generator.test.converter.ver1.InnerClassMapper.INSTANCE);
        versionedClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.ver1.InnerClass.class, com.github.pinkolik.general_classes_generator.test.converter.ver1.InnerClassMapper.INSTANCE);
        generalClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.Simple.class, com.github.pinkolik.general_classes_generator.test.converter.ver1.SimpleMapper.INSTANCE);
        versionedClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.ver1.Simple.class, com.github.pinkolik.general_classes_generator.test.converter.ver1.SimpleMapper.INSTANCE);
        return new BaseConverterImpl(generalClassesToMappers, versionedClassesToMappers, "com.github.pinkolik.general_classes_generator.test.converter");
    }

    @Bean
    public BaseConverter baseConverterVer2() {
        Map<Class, BaseMapper> generalClassesToMappers = new HashMap<>();
        Map<Class, BaseMapper> versionedClassesToMappers = new HashMap<>();
        generalClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.Empty.class, com.github.pinkolik.general_classes_generator.test.converter.ver2.EmptyMapper.INSTANCE);
        versionedClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.ver2.Empty.class, com.github.pinkolik.general_classes_generator.test.converter.ver2.EmptyMapper.INSTANCE);
        generalClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.Enum.class, com.github.pinkolik.general_classes_generator.test.converter.ver2.EnumMapper.INSTANCE);
        versionedClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.ver2.Enum.class, com.github.pinkolik.general_classes_generator.test.converter.ver2.EnumMapper.INSTANCE);
        generalClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.InnerClass.class, com.github.pinkolik.general_classes_generator.test.converter.ver2.InnerClassMapper.INSTANCE);
        versionedClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.ver2.InnerClass.class, com.github.pinkolik.general_classes_generator.test.converter.ver2.InnerClassMapper.INSTANCE);
        generalClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.Simple.class, com.github.pinkolik.general_classes_generator.test.converter.ver2.SimpleMapper.INSTANCE);
        versionedClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.ver2.Simple.class, com.github.pinkolik.general_classes_generator.test.converter.ver2.SimpleMapper.INSTANCE);
        return new BaseConverterImpl(generalClassesToMappers, versionedClassesToMappers, "com.github.pinkolik.general_classes_generator.test.converter");
    }

    @Bean
    public BaseConverter baseConverterVer3() {
        Map<Class, BaseMapper> generalClassesToMappers = new HashMap<>();
        Map<Class, BaseMapper> versionedClassesToMappers = new HashMap<>();
        generalClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.Empty.class, com.github.pinkolik.general_classes_generator.test.converter.ver3.EmptyMapper.INSTANCE);
        versionedClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.ver3.Empty.class, com.github.pinkolik.general_classes_generator.test.converter.ver3.EmptyMapper.INSTANCE);
        generalClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.Enum.class, com.github.pinkolik.general_classes_generator.test.converter.ver3.EnumMapper.INSTANCE);
        versionedClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.ver3.Enum.class, com.github.pinkolik.general_classes_generator.test.converter.ver3.EnumMapper.INSTANCE);
        generalClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.InnerClass.class, com.github.pinkolik.general_classes_generator.test.converter.ver3.InnerClassMapper.INSTANCE);
        versionedClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.ver3.InnerClass.class, com.github.pinkolik.general_classes_generator.test.converter.ver3.InnerClassMapper.INSTANCE);
        generalClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.Simple.class, com.github.pinkolik.general_classes_generator.test.converter.ver3.SimpleMapper.INSTANCE);
        versionedClassesToMappers.put(com.github.pinkolik.general_classes_generator.test.converter.ver3.Simple.class, com.github.pinkolik.general_classes_generator.test.converter.ver3.SimpleMapper.INSTANCE);
        return new BaseConverterImpl(generalClassesToMappers, versionedClassesToMappers, "com.github.pinkolik.general_classes_generator.test.converter");
    }

}