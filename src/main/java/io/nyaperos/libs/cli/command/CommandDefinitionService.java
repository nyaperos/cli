package io.nyaperos.libs.cli.command;

import io.nyaperos.libs.cli.command.exceptions.CommandNotFoundException;
import io.nyaperos.libs.cli.command.exceptions.IllegalCommandDefinitionException;
import io.nyaperos.libs.cli.command.exceptions.MultipleNamesAssignedToCommandException;
import io.nyaperos.libs.cli.utils.AnnotationsUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommandDefinitionService {

    private static final Class<CommandDefinition> COMMAND_DEFINITION_ANNOTATION = CommandDefinition.class;

    public Class<?> find(Package pkg, String commandName) throws CommandNotFoundException {
        Set<Class<?>> classes = AnnotationsUtils.find(pkg, COMMAND_DEFINITION_ANNOTATION);
        List<Class<?>> matchedCommandDefinitions = classes.stream().filter(c -> containsAlias(c, commandName)).collect(Collectors.toList());
        if (matchedCommandDefinitions.size() > 1)
            throw new MultipleNamesAssignedToCommandException(commandName, matchedCommandDefinitions);

        if (matchedCommandDefinitions.isEmpty())
            throw new CommandNotFoundException(commandName);

        return matchedCommandDefinitions.get(0);
    }

    public <T> T instantiate(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (IllegalAccessException | InstantiationException exception) {
            throw new IllegalCommandDefinitionException(clazz);
        }
    }


    private static boolean containsAlias(Class<?> clazz, String commandName) {
        CommandDefinition commandDefinition = clazz.getAnnotation(COMMAND_DEFINITION_ANNOTATION);
        return Stream.of(commandDefinition.names()).anyMatch(name -> name.equals(commandName));
    }
}
