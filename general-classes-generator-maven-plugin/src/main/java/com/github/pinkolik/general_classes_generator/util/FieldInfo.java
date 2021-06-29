package com.github.pinkolik.general_classes_generator.util;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class FieldInfo {

    private final String name;

    private final String type;

    private final boolean isStatic;

    private final boolean isFinal;

    private final boolean isEnum;

    private final Object staticFinalValue;
}