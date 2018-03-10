package io.nyaperos.libs.cli.options.filler;

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
