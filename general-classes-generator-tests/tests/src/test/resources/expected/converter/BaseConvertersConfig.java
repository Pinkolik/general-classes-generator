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
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ClassWithSimpleConstant.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.ClassWithSimpleConstantMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver1.ClassWithSimpleConstant.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.ClassWithSimpleConstantMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.Empty.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.EmptyMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver1.Empty.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.EmptyMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.Enum.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.EnumMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver1.Enum.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.EnumMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.EnumIsNotSerializable.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.EnumIsNotSerializableMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver1.EnumIsNotSerializable.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.EnumIsNotSerializableMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.EnumWithField.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.EnumWithFieldMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver1.EnumWithField.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.EnumWithFieldMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.IgnoreNullTest.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.IgnoreNullTestMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver1.IgnoreNullTest.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.IgnoreNullTestMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.InheritanceTestClass.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.InheritanceTestClassMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver1.InheritanceTestClass.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.InheritanceTestClassMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.InheritanceWithSerializableTestClass.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.InheritanceWithSerializableTestClassMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver1.InheritanceWithSerializableTestClass.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.InheritanceWithSerializableTestClassMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.InnerClass.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.InnerClassMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver1.InnerClass.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.InnerClassMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.InnerClass.Inner.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.InnerClass_InnerMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver1.InnerClass.Inner.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.InnerClass_InnerMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.InnerClassWithGenericField.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.InnerClassWithGenericFieldMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver1.InnerClassWithGenericField.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.InnerClassWithGenericFieldMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.InnerClassWithGenericField.Inner.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.InnerClassWithGenericField_InnerMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver1.InnerClassWithGenericField.Inner.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.InnerClassWithGenericField_InnerMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.InnerClassWithInnerEnum.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.InnerClassWithInnerEnumMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver1.InnerClassWithInnerEnum.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.InnerClassWithInnerEnumMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.InnerClassWithInnerEnum.InnerClassWithEnum.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.InnerClassWithInnerEnum_InnerClassWithEnumMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver1.InnerClassWithInnerEnum.InnerClassWithEnum.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.InnerClassWithInnerEnum_InnerClassWithEnumMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.InnerClassWithInnerEnum.InnerClassWithEnum.InnerEnum.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.InnerClassWithInnerEnum_InnerClassWithEnum_InnerEnumMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver1.InnerClassWithInnerEnum.InnerClassWithEnum.InnerEnum.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.InnerClassWithInnerEnum_InnerClassWithEnum_InnerEnumMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ParentClass.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.ParentClassMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver1.ParentClass.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.ParentClassMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ParentWithSerializableClass.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.ParentWithSerializableClassMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver1.ParentWithSerializableClass.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.ParentWithSerializableClassMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.SerializableTestClass.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.SerializableTestClassMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver1.SerializableTestClass.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.SerializableTestClassMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.Simple.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.SimpleMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver1.Simple.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.SimpleMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.UpcastingTestClass.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.UpcastingTestClassMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver1.UpcastingTestClass.class, io.github.pinkolik.general_classes_generator.test.mappers.ver1.UpcastingTestClassMapper.INSTANCE);
        return new BaseConverterImpl(generalClassesToMappers, versionedClassesToMappers, "io.github.pinkolik.general_classes_generator.test");
    }

    @Bean
    public BaseConverter baseConverterVer2() {
        Map<Class, BaseMapper> generalClassesToMappers = new HashMap<>();
        Map<Class, BaseMapper> versionedClassesToMappers = new HashMap<>();
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ClassWithSimpleConstant.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.ClassWithSimpleConstantMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver2.ClassWithSimpleConstant.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.ClassWithSimpleConstantMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.Empty.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.EmptyMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver2.Empty.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.EmptyMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.Enum.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.EnumMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver2.Enum.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.EnumMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.EnumIsNotSerializable.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.EnumIsNotSerializableMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver2.EnumIsNotSerializable.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.EnumIsNotSerializableMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.EnumWithField.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.EnumWithFieldMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver2.EnumWithField.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.EnumWithFieldMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.IgnoreNullTest.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.IgnoreNullTestMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver2.IgnoreNullTest.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.IgnoreNullTestMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.InheritanceTestClass.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.InheritanceTestClassMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver2.InheritanceTestClass.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.InheritanceTestClassMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.InheritanceWithSerializableTestClass.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.InheritanceWithSerializableTestClassMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver2.InheritanceWithSerializableTestClass.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.InheritanceWithSerializableTestClassMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.InnerClass.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.InnerClassMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver2.InnerClass.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.InnerClassMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.InnerClass.Inner.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.InnerClass_InnerMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver2.InnerClass.Inner.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.InnerClass_InnerMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.InnerClassWithGenericField.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.InnerClassWithGenericFieldMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver2.InnerClassWithGenericField.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.InnerClassWithGenericFieldMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.InnerClassWithGenericField.Inner.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.InnerClassWithGenericField_InnerMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver2.InnerClassWithGenericField.Inner.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.InnerClassWithGenericField_InnerMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.InnerClassWithInnerEnum.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.InnerClassWithInnerEnumMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver2.InnerClassWithInnerEnum.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.InnerClassWithInnerEnumMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.InnerClassWithInnerEnum.InnerClassWithEnum.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.InnerClassWithInnerEnum_InnerClassWithEnumMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver2.InnerClassWithInnerEnum.InnerClassWithEnum.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.InnerClassWithInnerEnum_InnerClassWithEnumMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.InnerClassWithInnerEnum.InnerClassWithEnum.InnerEnum.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.InnerClassWithInnerEnum_InnerClassWithEnum_InnerEnumMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver2.InnerClassWithInnerEnum.InnerClassWithEnum.InnerEnum.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.InnerClassWithInnerEnum_InnerClassWithEnum_InnerEnumMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ParentClass.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.ParentClassMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver2.ParentClass.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.ParentClassMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ParentWithSerializableClass.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.ParentWithSerializableClassMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver2.ParentWithSerializableClass.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.ParentWithSerializableClassMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.SerializableTestClass.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.SerializableTestClassMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver2.SerializableTestClass.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.SerializableTestClassMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.Simple.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.SimpleMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver2.Simple.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.SimpleMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.UpcastingTestClass.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.UpcastingTestClassMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver2.UpcastingTestClass.class, io.github.pinkolik.general_classes_generator.test.mappers.ver2.UpcastingTestClassMapper.INSTANCE);
        return new BaseConverterImpl(generalClassesToMappers, versionedClassesToMappers, "io.github.pinkolik.general_classes_generator.test");
    }

    @Bean
    public BaseConverter baseConverterVer3() {
        Map<Class, BaseMapper> generalClassesToMappers = new HashMap<>();
        Map<Class, BaseMapper> versionedClassesToMappers = new HashMap<>();
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ClassWithSimpleConstant.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.ClassWithSimpleConstantMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver3.ClassWithSimpleConstant.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.ClassWithSimpleConstantMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.Empty.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.EmptyMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver3.Empty.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.EmptyMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.Enum.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.EnumMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver3.Enum.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.EnumMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.EnumIsNotSerializable.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.EnumIsNotSerializableMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver3.EnumIsNotSerializable.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.EnumIsNotSerializableMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.EnumWithField.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.EnumWithFieldMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver3.EnumWithField.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.EnumWithFieldMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.IgnoreNullTest.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.IgnoreNullTestMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver3.IgnoreNullTest.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.IgnoreNullTestMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.InheritanceTestClass.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.InheritanceTestClassMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver3.InheritanceTestClass.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.InheritanceTestClassMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.InheritanceWithSerializableTestClass.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.InheritanceWithSerializableTestClassMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver3.InheritanceWithSerializableTestClass.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.InheritanceWithSerializableTestClassMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.InnerClass.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.InnerClassMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver3.InnerClass.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.InnerClassMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.InnerClass.Inner.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.InnerClass_InnerMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver3.InnerClass.Inner.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.InnerClass_InnerMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.InnerClassWithGenericField.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.InnerClassWithGenericFieldMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver3.InnerClassWithGenericField.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.InnerClassWithGenericFieldMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.InnerClassWithGenericField.Inner.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.InnerClassWithGenericField_InnerMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver3.InnerClassWithGenericField.Inner.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.InnerClassWithGenericField_InnerMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.InnerClassWithInnerEnum.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.InnerClassWithInnerEnumMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver3.InnerClassWithInnerEnum.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.InnerClassWithInnerEnumMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.InnerClassWithInnerEnum.InnerClassWithEnum.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.InnerClassWithInnerEnum_InnerClassWithEnumMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver3.InnerClassWithInnerEnum.InnerClassWithEnum.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.InnerClassWithInnerEnum_InnerClassWithEnumMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.InnerClassWithInnerEnum.InnerClassWithEnum.InnerEnum.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.InnerClassWithInnerEnum_InnerClassWithEnum_InnerEnumMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver3.InnerClassWithInnerEnum.InnerClassWithEnum.InnerEnum.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.InnerClassWithInnerEnum_InnerClassWithEnum_InnerEnumMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ParentClass.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.ParentClassMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver3.ParentClass.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.ParentClassMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ParentWithSerializableClass.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.ParentWithSerializableClassMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver3.ParentWithSerializableClass.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.ParentWithSerializableClassMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.SerializableTestClass.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.SerializableTestClassMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver3.SerializableTestClass.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.SerializableTestClassMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.Simple.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.SimpleMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver3.Simple.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.SimpleMapper.INSTANCE);
        generalClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.UpcastingTestClass.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.UpcastingTestClassMapper.INSTANCE);
        versionedClassesToMappers.put(io.github.pinkolik.general_classes_generator.test.ver3.UpcastingTestClass.class, io.github.pinkolik.general_classes_generator.test.mappers.ver3.UpcastingTestClassMapper.INSTANCE);
        return new BaseConverterImpl(generalClassesToMappers, versionedClassesToMappers, "io.github.pinkolik.general_classes_generator.test");
    }

}