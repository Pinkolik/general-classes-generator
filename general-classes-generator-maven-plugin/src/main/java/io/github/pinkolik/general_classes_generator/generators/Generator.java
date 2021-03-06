package io.github.pinkolik.general_classes_generator.generators;

import java.io.IOException;
import java.util.Set;

/**
 * Generator interface which is used to generate source files.
 */
public interface Generator {

    /**
     * Generate source files.
     *
     * @throws IOException            when file system can't be accessed for some reason.
     * @throws IllegalAccessException when class field can't be accessed.
     */
    void generate() throws IOException, IllegalAccessException;

    /**
     * @param includeClassesRegex List of regex expressions of classes to include for generation.
     *                            If not set all classes are generated.
     */
    void setIncludeClassesRegex(Set<String> includeClassesRegex);
}
