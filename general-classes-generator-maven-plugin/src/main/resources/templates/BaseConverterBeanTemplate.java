
@Bean
public BaseConverter baseConverter${version}() {
    Map<Class, BaseMapper> generalClassesToMappers = new HashMap<>();
    Map<Class, BaseMapper> versionedClassesToMappers = new HashMap<>();
    ${put_to_maps}
    return new BaseConverterImpl(generalClassesToMappers, versionedClassesToMappers, "${general_classes_base_package}");
}
