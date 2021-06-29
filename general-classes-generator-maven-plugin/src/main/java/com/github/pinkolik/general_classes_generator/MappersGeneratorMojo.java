package com.github.pinkolik.general_classes_generator;

import com.github.pinkolik.general_classes_generator.generators.Generator;
import com.github.pinkolik.general_classes_generator.generators.impl.MappersGeneratorImpl;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;

/**
 * Goal which generates mappers which can be used to convert generalized classes to versioned and vice versa.
 * @see com.github.pinkolik.general_classes_generator.conversion.BaseMapper
 */
@Mojo(name = "generate-mappers", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class MappersGeneratorMojo extends AbstractGeneratorMojo {

    /**
     * Base path to the directory where versioned classes are stored.
     * <p>
     * Example: your project have multiple versions of class <code>Example</code> located at
     * <p>
     * "${project.basedir}/src/main/java/your_package/ver1/Example.java",
     * <p>
     * "${project.basedir}/src/main/java/your_package/ver2/Example.java",
     * <p>
     * "${project.basedir}/src/main/java/your_package/ver3/Example.java".
     * <p>
     * In that case the base path would be "${project.basedir}/src/main/java/your_package".
     */
    @Parameter(name = "versionClassesBasePath", required = true)
    private File versionClassesBasePath;

    /**
     * Regular Expression to extract version part from the path.
     * <p>
     * Example: your project have multiple versions of class <code>Example</code> located at
     * <p>
     * "${project.basedir}/src/main/java/your_package/ver1/Example.java",
     * <p>
     * "${project.basedir}/src/main/java/your_package/ver2/Example.java",
     * <p>
     * "${project.basedir}/src/main/java/your_package/ver3/Example.java".
     * <p>
     * In that case version RegEx would be "ver\d+".
     */
    @Parameter(name = "versionRegexPattern", required = true)
    private String versionRegexPattern;

    /**
     * Base path to the directory where mapper interfaces will be generated.
     * <p>
     * Example: your project have multiple versions of class <code>Example</code> located at
     * <p>
     * "${project.basedir}/src/main/java/your_package/ver1/Example.java",
     * <p>
     * "${project.basedir}/src/main/java/your_package/ver2/Example.java",
     * <p>
     * "${project.basedir}/src/main/java/your_package/ver3/Example.java".
     * <p>
     * And outputBasePath is "${project.basedir}/src/main/java/another_package".
     * <p>
     * Then mappers for <code>Example</code> will be put in
     * <p>
     * "${project.basedir}/src/main/java/another_package/ver1/ExampleMapper.java",
     * <p>
     * "${project.basedir}/src/main/java/another_package/ver2/ExampleMapper.java",
     * <p>
     * "${project.basedir}/src/main/java/another_package/ver3/ExampleMapper.java".
     */
    @Parameter(name = "outputBasePath", required = true)
    private File outputBasePath;

    @Override
    protected Generator createGenerator() {
        return new MappersGeneratorImpl(versionClassesBasePath.getAbsolutePath(), versionRegexPattern,
                                        outputBasePath.getAbsolutePath());
    }
}
