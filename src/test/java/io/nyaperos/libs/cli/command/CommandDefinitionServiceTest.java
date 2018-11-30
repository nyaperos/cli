package io.nyaperos.libs.cli.command;

import io.nyaperos.libs.cli.command.exceptions.CommandNotFoundException;
import io.nyaperos.libs.cli.command.exceptions.IllegalCommandDefinitionException;
import io.nyaperos.libs.cli.command.exceptions.MultipleNamesAssignedToCommandException;
import io.nyaperos.libs.cli.fakes.packages.FakeCommandDefinitionWithoutOptions;
import io.nyaperos.libs.cli.fakes.packages.duplicated.DuplicatedCommandDefinition;
import io.nyaperos.libs.cli.fakes.packages.duplicated.DuplicatedCommandDefinition2;
import io.nyaperos.libs.cli.options.Option;
import io.nyaperos.libs.cli.utils.AnnotationsUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.text.MessageFormat.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandDefinitionServiceTest {

    private static final Package PACKAGE = FakeCommandDefinitionWithoutOptions.class.getPackage();
    private static CommandDefinitionService commandDefinitionService;


    @BeforeAll
    static void setUpBeforeAll() {
        commandDefinitionService = new CommandDefinitionService();
    }

    @Test
    void givenNameWithoutAssignedCommand_ShouldThrowException() {
        String commandName = "fake-non-existent-alias";

        assertThrows(
                CommandNotFoundException.class,
                () -> commandDefinitionService.find(PACKAGE, commandName),
                format(CommandNotFoundException.MESSAGE, commandName)
        );
    }

    @Test
    void givenNameAssignedToMultipleCommands_ShouldThrowException() {
        String commandName = "duplicated-fake";
        List<Class<?>> classes = Arrays.asList(DuplicatedCommandDefinition.class, DuplicatedCommandDefinition2.class);

        assertThrows(
                MultipleNamesAssignedToCommandException.class,
                () -> commandDefinitionService.find(PACKAGE, commandName),
                format(MultipleNamesAssignedToCommandException.MESSAGE, commandName, classes)
        );
    }

    @Test
    void givenCommandDefinitionWith2Names_ThenCanBeFoundBy2Names() throws CommandNotFoundException {
        Class<?> expectedClass = FakeCommandDefinitionWithoutOptions.class;
        String commandName = "fake3";
        String commandName2 = "fake-3";

        Class<?> commandDefinitionClass = commandDefinitionService.find(PACKAGE, commandName);
        Class<?> commandDefinitionClass2 = commandDefinitionService.find(PACKAGE, commandName2);

        assertEquals(expectedClass, commandDefinitionClass);
        assertEquals(expectedClass, commandDefinitionClass2);
    }

    /*
     *
     */

    @Test
    void givenClassWithoutConstructor_ShouldThrowException() {
        assertThrows(
                IllegalCommandDefinitionException.class,
                () -> commandDefinitionService.instantiate(AnnotationsUtils.class),
                format(IllegalCommandDefinitionException.MESSAGE, AnnotationsUtils.class.getCanonicalName())
        );
    }

    @Test
    void givenAbstractClass_ShouldThrowException() {
        assertThrows(
                IllegalCommandDefinitionException.class,
                () -> commandDefinitionService.instantiate(Option.class),
                format(IllegalCommandDefinitionException.MESSAGE, Option.class.getCanonicalName())
        );
    }

    @Test
    void givenPrimitiveType_ShouldThrowException() {
        assertThrows(
                IllegalCommandDefinitionException.class,
                () -> commandDefinitionService.instantiate(int.class),
                format(IllegalCommandDefinitionException.MESSAGE, int.class.getCanonicalName())
        );
    }

    @Test
    void givenValidCommandDefinitionClass_ShouldReturnAnInstance() {
        DuplicatedCommandDefinition instance = commandDefinitionService.instantiate(DuplicatedCommandDefinition.class);

        assertEquals(DuplicatedCommandDefinition.class, instance.getClass());
    }


}

