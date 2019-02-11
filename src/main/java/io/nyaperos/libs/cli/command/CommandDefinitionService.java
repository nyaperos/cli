package io.nyaperos.libs.cli.command;

import io.nyaperos.libs.cli.command.exceptions.CommandNotFoundException;
import io.nyaperos.libs.cli.command.exceptions.IllegalCommandDefinitionException;
import io.nyaperos.libs.cli.command.exceptions.MultipleAliasesAssignedToCommandException;
import io.nyaperos.libs.cli.commons.InvalidClassInstantiationException;
import io.nyaperos.libs.cli.utils.AnnotationsUtils;
import lombok.val;

import java.util.Arrays;
import java.util.stream.Collectors;

class CommandDefinitionService {

    private static final Class<CommandDefinition> COMMAND_DEFINITION_ANNOTATION = CommandDefinition.class;

    private CommandDefinitionService() {
        throw new InvalidClassInstantiationException(CommandDefinitionService.class);
    }

    static Class<?> find(Package pkg, String commandAlias) throws CommandNotFoundException {
        val matchedCommandDefinitions =
                AnnotationsUtils.find(pkg, COMMAND_DEFINITION_ANNOTATION).stream()
                        .filter(c -> containsAlias(c, commandAlias))
                        .collect(Collectors.toList());

        if (matchedCommandDefinitions.size() > 1)
            throw new MultipleAliasesAssignedToCommandException(commandAlias, matchedCommandDefinitions);

        if (matchedCommandDefinitions.isEmpty())
            throw new CommandNotFoundException(commandAlias);

        return matchedCommandDefinitions.get(0);
    }

    static <T> T instantiate(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (IllegalAccessException | InstantiationException exception) {
            throw new IllegalCommandDefinitionException(clazz);
        }
    }

    private static boolean containsAlias(Class<?> clazz, String commandAlias) {
        val commandDefinition = clazz.getAnnotation(COMMAND_DEFINITION_ANNOTATION);
        return Arrays.asList(commandDefinition.aliases()).contains(commandAlias);
    }
}
