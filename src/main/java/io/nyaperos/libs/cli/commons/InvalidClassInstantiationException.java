package io.nyaperos.libs.cli.commons;

import static java.text.MessageFormat.format;

public class InvalidClassInstantiationException extends RuntimeException{

    private static final String MESSAGE = "This class [{0}] can't be instantiated";

    public InvalidClassInstantiationException(Class<?> clazz) {
        super(format(MESSAGE, clazz.getCanonicalName()));
    }
}
