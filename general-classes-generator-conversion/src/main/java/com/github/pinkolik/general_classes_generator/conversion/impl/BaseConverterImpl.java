package com.github.pinkolik.general_classes_generator.conversion.impl;


import com.github.pinkolik.general_classes_generator.conversion.BaseConverter;
import com.github.pinkolik.general_classes_generator.conversion.BaseMapper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Implementation of {@link BaseConverter} which uses
 * two maps: general classes to mappers and versioned classes to mappers.
 */
public class BaseConverterImpl implements BaseConverter {

    private final Map<Class, BaseMapper> generalClassesToMappers;

    private final Map<Class, BaseMapper> versionedClassesToMappers;

    private final String generalClassesBasePackage;

    /**
     * Constructor for {@link BaseConverterImpl}.
     * @param generalClassesToMappers general classes to mappers map.
     * @param versionedClassesToMappers versioned classes to mappers map.
     * @param generalClassesBasePackage base package of general classes, needed for reflection
     *                                  operations in order to detect if field should be attempted
     *                                  to convert or not.
     */
    public BaseConverterImpl(final Map<Class, BaseMapper> generalClassesToMappers,
                             final Map<Class, BaseMapper> versionedClassesToMappers, final String generalClassesBasePackage) {
        this.generalClassesToMappers = generalClassesToMappers;
        this.versionedClassesToMappers = versionedClassesToMappers;
        this.generalClassesBasePackage = generalClassesBasePackage;
    }

    @Override
    public Object convertToVersionedClass(final Object general) {
        Object versionedClass = generalClassesToMappers.get(general.getClass()).mapGeneralToVersioned(general);
        return mapSubclassesToVersionedClasses(versionedClass);
    }

    @Override
    public Object convertToGeneralClass(final Object versioned) {
        Object generalClass = versionedClassesToMappers.get(versioned.getClass()).mapVersionedToGeneral(versioned);
        return mapSubclassesToGeneralClasses(generalClass);
    }

    private Object mapSubclassesToGeneralClasses(final Object generalClass) {
        if (generalClass == null) {
            return null;
        }
        try {
            Field[] fields = generalClass.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object obj = field.get(generalClass);
                if (obj == null) {
                    continue;
                }
                if (field.getType().isAssignableFrom(Object.class) && versionedClassesToMappers.containsKey(obj.getClass())) {
                    obj = versionedClassesToMappers.get(obj.getClass()).mapVersionedToGeneral(obj);
                    field.set(generalClass, mapSubclassesToGeneralClasses(obj));
                }
                else if (List.class.isAssignableFrom(field.getType())) {
                    List objects = (List) obj;
                    List<Object> newList = new ArrayList<>();
                    for (Object listObj : objects) {
                        if (listObj == null) {
                            continue;
                        }
                        if (versionedClassesToMappers.containsKey(listObj.getClass())) {
                            listObj = versionedClassesToMappers.get(listObj.getClass()).mapVersionedToGeneral(listObj);
                            newList.add(mapSubclassesToGeneralClasses(listObj));
                        }
                        else if (listObj.getClass().getCanonicalName().startsWith(generalClassesBasePackage)) {
                            newList.add(mapSubclassesToGeneralClasses(listObj));
                        }
                        else {
                            newList.add(listObj);
                        }
                    }
                    field.set(generalClass, newList);
                }
                else if (field.getType().getCanonicalName().startsWith(generalClassesBasePackage)) {
                    field.set(generalClass, mapSubclassesToGeneralClasses(obj));
                }
            }
        }
        catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return generalClass;
    }

    private Object mapSubclassesToVersionedClasses(final Object versionedClass) {
        if (versionedClass == null) {
            return null;
        }
        try {
            Field[] fields = versionedClass.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object obj = field.get(versionedClass);
                if (obj == null) {
                    continue;
                }
                if (field.getType().isAssignableFrom(Object.class) && generalClassesToMappers.containsKey(obj.getClass())) {
                    obj = generalClassesToMappers.get(obj.getClass()).mapGeneralToVersioned(obj);
                    field.set(versionedClass, mapSubclassesToVersionedClasses(obj));
                }
                else if (List.class.isAssignableFrom(field.getType())) {
                    List objects = (List) obj;
                    List<Object> newList = new ArrayList<>();
                    for (Object listObj : objects) {
                        if (listObj == null) {
                            continue;
                        }
                        if (generalClassesToMappers.containsKey(listObj.getClass())) {
                            listObj = generalClassesToMappers.get(listObj.getClass()).mapGeneralToVersioned(listObj);
                            newList.add(mapSubclassesToVersionedClasses(listObj));
                        }
                        else if (listObj.getClass().getCanonicalName().startsWith(generalClassesBasePackage)) {
                            newList.add(mapSubclassesToVersionedClasses(listObj));
                        }
                        else {
                            newList.add(listObj);
                        }
                    }
                    field.set(versionedClass, newList);
                }
                else {
                    if (field.getType().getCanonicalName().startsWith(generalClassesBasePackage)) {
                        field.set(versionedClass, mapSubclassesToVersionedClasses(obj));
                    }
                }
            }
        }
        catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return versionedClass;
    }
}
