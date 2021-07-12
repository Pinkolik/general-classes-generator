package io.github.pinkolik.general_classes_generator;

import io.github.pinkolik.general_classes_generator.generators.Generator;
import org.apache.maven.plugin.AbstractMojo;

/**
 * Abstract mojo for source generation using {@link Generator}.
 *
 * @see Generator
 */
public abstract class AbstractGeneratorMojo extends AbstractMojo {

    @Override
    public void execute() {
        Generator generator = createGenerator();

        try {
            generator.generate();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
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
