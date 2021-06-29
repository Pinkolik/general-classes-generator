package com.github.pinkolik.general_classes_generator.util;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class ConverterInfo {

    private final String generalClassName;

    private final String versionedClassName;

    private final String mapperClassName;
}
