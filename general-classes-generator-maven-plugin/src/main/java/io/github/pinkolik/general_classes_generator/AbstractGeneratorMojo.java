package io.github.pinkolik.general_classes_generator;

import io.github.pinkolik.general_classes_generator.generators.Generator;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.IOException;

/**
 * Abstract mojo for source generation using {@link Generator}.
 *
 * @see Generator
 */
public abstract class AbstractGeneratorMojo extends AbstractMojo {

    @Override
    public void execute() throws MojoExecutionException {
        Generator generator = createGenerator();

        try {
            generator.generate();
        }
        catch (IOException e) {
            throw new MojoExecutionException("Couldn't modify files", e);
        }
        catch (IllegalAccessException e) {
            throw new MojoExecutionException("Couldn't access field", e);
        }
    }

    /**
     * Provides an implementation of {@link Generator} which will be used
     * for source generation.
     *
     * @return an implementation of {@link Generator}
     *
     * @see Generator
     */
    protected abstract Generator createGenerator();
}
