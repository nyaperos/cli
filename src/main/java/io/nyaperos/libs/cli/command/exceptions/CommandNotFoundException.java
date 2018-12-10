package io.nyaperos.libs.cli.command.exceptions;

import static java.text.MessageFormat.format;

public class CommandNotFoundException extends Exception {

    public static final String MESSAGE = "Invalid command alias {0}. Does not exists any command with this alias";

    public CommandNotFoundException(String commandAlias) {
        super(format(MESSAGE, commandAlias));
    }
}
