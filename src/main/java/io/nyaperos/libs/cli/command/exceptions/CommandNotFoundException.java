package io.nyaperos.libs.cli.command.exceptions;

import static java.text.MessageFormat.format;

public class CommandNotFoundException extends Exception {

    public static final String MESSAGE = "Invalid command name {0}. Does not exists any command with this name";

    public CommandNotFoundException(String commandName) {
        super(format(MESSAGE, commandName));
    }
}
