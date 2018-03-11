package io.nyaperos.libs.cli.options.filler;

import io.nyaperos.libs.cli.command.exceptions.IllegalCommandDefinitionException;

public class OptionsFiller {

    public <T> T fill(Class<T> clazz, String... arguments) {
        try {
            clazz.newInstance();
        } catch (IllegalAccessException | InstantiationException exception) {
            throw new IllegalCommandDefinitionException(clazz);
        }
        return null;
    }
}
