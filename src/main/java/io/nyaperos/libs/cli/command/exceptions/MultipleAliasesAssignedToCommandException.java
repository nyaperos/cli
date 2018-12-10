package io.nyaperos.libs.cli.command.exceptions;

import java.util.List;
import java.util.stream.Collectors;

import static java.text.MessageFormat.format;

public class MultipleAliasesAssignedToCommandException extends RuntimeException {

    public static final String MESSAGE = "Invalid command alias {0}. This alias it's assigned to multiple commands: {1}";

    public MultipleAliasesAssignedToCommandException(String commandAlias, List<Class<?>> classes) {
        super(format(MESSAGE, commandAlias, joinToString(classes)));
    }

    private static String joinToString(List<Class<?>> classes) {
        return classes.stream().map(Class::getCanonicalName).sorted().collect(Collectors.joining(","));
    }
}
