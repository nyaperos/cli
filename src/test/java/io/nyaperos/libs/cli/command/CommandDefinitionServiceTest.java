package io.nyaperos.libs.cli.command;

import io.nyaperos.libs.cli.command.exceptions.CommandNotFoundException;
import io.nyaperos.libs.cli.command.exceptions.IllegalCommandDefinitionException;
import io.nyaperos.libs.cli.command.exceptions.MultipleAliasesAssignedToCommandException;
import io.nyaperos.libs.cli.fakes.packages.FakeCommandDefinitionWithoutOptions;
import io.nyaperos.libs.cli.fakes.packages.duplicated.DuplicatedCommandDefinition;
import io.nyaperos.libs.cli.fakes.packages.duplicated.DuplicatedCommandDefinition2;
import io.nyaperos.libs.cli.options.Option;
import io.nyaperos.libs.cli.utils.AnnotationsUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.text.MessageFormat.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandDefinitionServiceTest {

    private static final Package PACKAGE = FakeCommandDefinitionWithoutOptions.class.getPackage();

    @Test
    void givenAliasWithoutAssignedCommand_ShouldThrowException() {
        String commandAlias = "fake-non-existent-alias";

        assertThrows(
                CommandNotFoundException.class,
                () -> CommandDefinitionService.find(PACKAGE, commandAlias),
                format(CommandNotFoundException.MESSAGE, commandAlias)
        );
    }

    @Test
    void givenAliasAssignedToMultipleCommands_ShouldThrowException() {
        String commandAlias = "duplicated-fake";
        List<Class<?>> classes = Arrays.asList(DuplicatedCommandDefinition.class, DuplicatedCommandDefinition2.class);

        assertThrows(
                MultipleAliasesAssignedToCommandException.class,
                () -> CommandDefinitionService.find(PACKAGE, commandAlias),
                format(MultipleAliasesAssignedToCommandException.MESSAGE, commandAlias, classes)
        );
    }

    @Test
    void givenCommandDefinitionWith2Alias_ThenCanBeFoundByBoth() throws CommandNotFoundException {
        Class<?> expectedClass = FakeCommandDefinitionWithoutOptions.class;
        String commandAlias = "fake3";
        String commandAlias2 = "fake-3";

        Class<?> commandDefinitionClass = CommandDefinitionService.find(PACKAGE, commandAlias);
        Class<?> commandDefinitionClass2 = CommandDefinitionService.find(PACKAGE, commandAlias2);

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
                () -> CommandDefinitionService.instantiate(AnnotationsUtils.class),
                format(IllegalCommandDefinitionException.MESSAGE, AnnotationsUtils.class.getCanonicalName())
        );
    }

    @Test
    void givenAbstractClass_ShouldThrowException() {
        assertThrows(
                IllegalCommandDefinitionException.class,
                () -> CommandDefinitionService.instantiate(Option.class),
                format(IllegalCommandDefinitionException.MESSAGE, Option.class.getCanonicalName())
        );
    }

    @Test
    void givenPrimitiveType_ShouldThrowException() {
        assertThrows(
                IllegalCommandDefinitionException.class,
                () -> CommandDefinitionService.instantiate(int.class),
                format(IllegalCommandDefinitionException.MESSAGE, int.class.getCanonicalName())
        );
    }

    @Test
    void givenValidCommandDefinitionClass_ShouldReturnAnInstance() {
        DuplicatedCommandDefinition instance = CommandDefinitionService.instantiate(DuplicatedCommandDefinition.class);

        assertEquals(DuplicatedCommandDefinition.class, instance.getClass());
    }


}

