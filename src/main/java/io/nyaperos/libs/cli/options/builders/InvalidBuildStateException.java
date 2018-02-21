package io.nyaperos.libs.cli.options.builders;

import static java.text.MessageFormat.format;

public class InvalidBuildStateException extends RuntimeException {

    public static final String MESSAGE = "Invalid state building option. {0} field is null";

    public InvalidBuildStateException(String field) {
        super(format(MESSAGE, field));
    }
}
