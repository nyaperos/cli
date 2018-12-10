package io.nyaperos.libs.cli.command;

import io.nyaperos.libs.cli.command.exceptions.CommandNotFoundException;
import io.nyaperos.libs.cli.command.exceptions.IllegalCommandDefinitionException;
import io.nyaperos.libs.cli.command.exceptions.MultipleAliasesAssignedToCommandException;
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
    void givenAliasWithoutAssignedCommand_ShouldThrowException() {
        String commandAlias = "fake-non-existent-alias";

        assertThrows(
                CommandNotFoundException.class,
                () -> commandDefinitionService.find(PACKAGE, commandAlias),
                format(CommandNotFoundException.MESSAGE, commandAlias)
        );
    }

    @Test
    void givenAliasAssignedToMultipleCommands_ShouldThrowException() {
        String commandAlias = "duplicated-fake";
        List<Class<?>> classes = Arrays.asList(DuplicatedCommandDefinition.class, DuplicatedCommandDefinition2.class);

        assertThrows(
                MultipleAliasesAssignedToCommandException.class,
                () -> commandDefinitionService.find(PACKAGE, commandAlias),
                format(MultipleAliasesAssignedToCommandException.MESSAGE, commandAlias, classes)
        );
    }

    @Test
    void givenCommandDefinitionWith2Alias_ThenCanBeFoundByBoth() throws CommandNotFoundException {
        Class<?> expectedClass = FakeCommandDefinitionWithoutOptions.class;
        String commandAlias = "fake3";
        String commandAlias2 = "fake-3";

        Class<?> commandDefinitionClass = commandDefinitionService.find(PACKAGE, commandAlias);
        Class<?> commandDefinitionClass2 = commandDefinitionService.find(PACKAGE, commandAlias2);

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

