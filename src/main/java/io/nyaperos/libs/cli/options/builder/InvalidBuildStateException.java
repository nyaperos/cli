package io.nyaperos.libs.cli.options.builder;

import static java.text.MessageFormat.format;

public class InvalidBuildStateException extends RuntimeException {

    static final String MESSAGE = "Invalid state building option. {0} field is null";

    public InvalidBuildStateException(String field) {
        super(format(MESSAGE, field));
    }
}
