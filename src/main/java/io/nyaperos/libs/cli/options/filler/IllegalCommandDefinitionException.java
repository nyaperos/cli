package io.nyaperos.libs.cli.options.filler;

import static java.text.MessageFormat.format;

public class IllegalCommandDefinitionException extends RuntimeException {

    static final String MESSAGE = "Illegal command definition. {0} class has not an empty public constructor";

    public IllegalCommandDefinitionException(Class<?> clazz) {
        super(format(MESSAGE, clazz.getCanonicalName()));
    }
}
