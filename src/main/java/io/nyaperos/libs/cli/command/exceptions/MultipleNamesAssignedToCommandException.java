package io.nyaperos.libs.cli.command.exceptions;

import java.util.List;
import java.util.stream.Collectors;

import static java.text.MessageFormat.format;

public class MultipleNamesAssignedToCommandException extends RuntimeException {

    public static final String MESSAGE = "Invalid command name {0}. This name it's assigned to multiple commands: {1}";

    public MultipleNamesAssignedToCommandException(String commandName, List<Class<?>> classes) {
        super(format(MESSAGE, commandName, joinToString(classes)));
    }

    private static String joinToString(List<Class<?>> classes) {
        return classes.stream().map(Class::getCanonicalName).sorted().collect(Collectors.joining(","));
    }
}
