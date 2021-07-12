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
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.converter.Empty.class, io.github.pinkolik.general_classes_generator.test.converter.ver1.EmptyMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.converter.ver1.Empty.class, io.github.pinkolik.general_classes_generator.test.converter.ver1.EmptyMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.converter.InheritanceTestClass.class, io.github.pinkolik.general_classes_generator.test.converter.ver1.InheritanceTestClassMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.converter.ver1.InheritanceTestClass.class, io.github.pinkolik.general_classes_generator.test.converter.ver1.InheritanceTestClassMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.converter.InnerClass.class, io.github.pinkolik.general_classes_generator.test.converter.ver1.InnerClassMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.converter.ver1.InnerClass.class, io.github.pinkolik.general_classes_generator.test.converter.ver1.InnerClassMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.converter.ParentClass.class, io.github.pinkolik.general_classes_generator.test.converter.ver1.ParentClassMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.converter.ver1.ParentClass.class, io.github.pinkolik.general_classes_generator.test.converter.ver1.ParentClassMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.converter.Simple.class, io.github.pinkolik.general_classes_generator.test.converter.ver1.SimpleMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.converter.ver1.Simple.class, io.github.pinkolik.general_classes_generator.test.converter.ver1.SimpleMapper.INSTANCE);
        return new BaseConverterImpl(generalClassesToMappers, versionedClassesToMappers, "io.github.pinkolik.general_classes_generator.test.converter");
    }

    @Bean
    public BaseConverter baseConverterVer2() {
        Map<Class, BaseMapper> generalClassesToMappers = new HashMap<>();
        Map<Class, BaseMapper> versionedClassesToMappers = new HashMap<>();
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.converter.Empty.class, io.github.pinkolik.general_classes_generator.test.converter.ver2.EmptyMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.converter.ver2.Empty.class, io.github.pinkolik.general_classes_generator.test.converter.ver2.EmptyMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.converter.InheritanceTestClass.class, io.github.pinkolik.general_classes_generator.test.converter.ver2.InheritanceTestClassMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.converter.ver2.InheritanceTestClass.class, io.github.pinkolik.general_classes_generator.test.converter.ver2.InheritanceTestClassMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.converter.InnerClass.class, io.github.pinkolik.general_classes_generator.test.converter.ver2.InnerClassMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.converter.ver2.InnerClass.class, io.github.pinkolik.general_classes_generator.test.converter.ver2.InnerClassMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.converter.ParentClass.class, io.github.pinkolik.general_classes_generator.test.converter.ver2.ParentClassMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.converter.ver2.ParentClass.class, io.github.pinkolik.general_classes_generator.test.converter.ver2.ParentClassMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.converter.Simple.class, io.github.pinkolik.general_classes_generator.test.converter.ver2.SimpleMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.converter.ver2.Simple.class, io.github.pinkolik.general_classes_generator.test.converter.ver2.SimpleMapper.INSTANCE);
        return new BaseConverterImpl(generalClassesToMappers, versionedClassesToMappers, "io.github.pinkolik.general_classes_generator.test.converter");
    }

    @Bean
    public BaseConverter baseConverterVer3() {
        Map<Class, BaseMapper> generalClassesToMappers = new HashMap<>();
        Map<Class, BaseMapper> versionedClassesToMappers = new HashMap<>();
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.converter.Empty.class, io.github.pinkolik.general_classes_generator.test.converter.ver3.EmptyMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.converter.ver3.Empty.class, io.github.pinkolik.general_classes_generator.test.converter.ver3.EmptyMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.converter.InheritanceTestClass.class, io.github.pinkolik.general_classes_generator.test.converter.ver3.InheritanceTestClassMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.converter.ver3.InheritanceTestClass.class, io.github.pinkolik.general_classes_generator.test.converter.ver3.InheritanceTestClassMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.converter.InnerClass.class, io.github.pinkolik.general_classes_generator.test.converter.ver3.InnerClassMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.converter.ver3.InnerClass.class, io.github.pinkolik.general_classes_generator.test.converter.ver3.InnerClassMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.converter.ParentClass.class, io.github.pinkolik.general_classes_generator.test.converter.ver3.ParentClassMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.converter.ver3.ParentClass.class, io.github.pinkolik.general_classes_generator.test.converter.ver3.ParentClassMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.converter.Simple.class, io.github.pinkolik.general_classes_generator.test.converter.ver3.SimpleMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.converter.ver3.Simple.class, io.github.pinkolik.general_classes_generator.test.converter.ver3.SimpleMapper.INSTANCE);
        return new BaseConverterImpl(generalClassesToMappers, versionedClassesToMappers, "io.github.pinkolik.general_classes_generator.test.converter");
    }

}