package com.github.pinkolik.general_classes_generator.util;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class ClassInfo {

    private final String name;

    private final boolean isEnum;

    private final boolean isMember;

    private final boolean isStatic;

}