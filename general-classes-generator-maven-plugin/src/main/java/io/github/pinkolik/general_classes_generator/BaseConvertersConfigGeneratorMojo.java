package io.github.pinkolik.general_classes_generator;

import io.github.pinkolik.general_classes_generator.conversion.BaseConverter;
import io.github.pinkolik.general_classes_generator.generators.Generator;
import io.github.pinkolik.general_classes_generator.generators.impl.BaseConvertersConfigGeneratorImpl;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.util.Set;

/**
 * Goal which generates a spring-based converter named {@link BaseConverter}.
 *
 * @see BaseConverter
 */
@Mojo(name = "generate-base-converters-config", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class BaseConvertersConfigGeneratorMojo extends AbstractGeneratorMojo {

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
     * Base path to the directory where mapper interfaces are stored.
     * <p>
     * Example: your have generated mappers for <code>Example</code> located at
     * <p>
     * "${project.basedir}/src/main/java/your_package/ver1/ExampleMapper.java",
     * <p>
     * "${project.basedir}/src/main/java/your_package/ver2/ExampleMapper.java",
     * <p>
     * "${project.basedir}/src/main/java/your_package/ver3/ExampleMapper.java".
     * <p>
     * In that case the base path would be "${project.basedir}/src/main/java/your_package".
     */
    @Parameter(name = "mappersBasePath", required = true)
    private File mappersBasePath;

    /**
     * Path to the directory where BaseConvertersConfig.java will be put.
     */
    @Parameter(name = "outputPath", required = true)
    private File outputPath;

    /**
     * List of regex expressions of classes to include for generation.
     * If not set all classes are generated.
     */
    @Parameter(name = "includeClassesRegex")
    private Set<String> includeClassesRegex;

    @Override
    protected Generator createGenerator() {
        BaseConvertersConfigGeneratorImpl generator =
                new BaseConvertersConfigGeneratorImpl(versionClassesBasePath.getAbsolutePath(), versionRegexPattern,
                                                      mappersBasePath.getAbsolutePath(), outputPath.getAbsolutePath());
        generator.setIncludeClassesRegex(includeClassesRegex);
        return generator;
    }
}
