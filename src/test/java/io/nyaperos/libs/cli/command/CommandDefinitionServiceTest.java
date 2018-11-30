package io.nyaperos.libs.cli.command;

import io.nyaperos.libs.cli.command.exceptions.CommandNotFoundException;
import io.nyaperos.libs.cli.command.exceptions.IllegalCommandDefinitionException;
import io.nyaperos.libs.cli.command.exceptions.MultipleNamesAssignedToCommandException;
import io.nyaperos.libs.cli.fakes.packages.FakeCommandDefinitionWithoutOptions;
import io.nyaperos.libs.cli.fakes.packages.duplicated.DuplicatedCommandDefinition;
import io.nyaperos.libs.cli.fakes.packages.duplicated.DuplicatedCommandDefinition2;
import io.nyaperos.libs.cli.options.Option;
import io.nyaperos.libs.cli.utils.AnnotationsUtils;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;

import static java.text.MessageFormat.format;
import static org.junit.Assert.assertEquals;

public class CommandDefinitionServiceTest {

    private static final Package PACKAGE = FakeCommandDefinitionWithoutOptions.class.getPackage();
    private static CommandDefinitionService service;
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @BeforeClass
    public static void setUpBeforeClass() {
        service = new CommandDefinitionService();
    }

    @Test
    public void givenNameWithoutAssignedCommand_ShouldThrowException() throws CommandNotFoundException {
        String commandName = "fake-non-existent-alias";

        String expectedMessage = format(CommandNotFoundException.MESSAGE, commandName);
        exception.expect(CommandNotFoundException.class);
        exception.expectMessage(expectedMessage);
        service.find(PACKAGE, commandName);
    }

    @Test
    public void givenNameAssignedToMultipleCommands_ShouldThrowException() throws CommandNotFoundException {
        String commandName = "duplicated-fake";
        List<Class<?>> classes = Arrays.asList(DuplicatedCommandDefinition.class, DuplicatedCommandDefinition2.class);
        String expectedMessage = format(MultipleNamesAssignedToCommandException.MESSAGE, commandName, classes);

        exception.expect(MultipleNamesAssignedToCommandException.class);
        exception.expectMessage(expectedMessage);

        service.find(PACKAGE, commandName);
    }

    @Test
    public void givenCommandDefinitionWith2Names_ThenCanBeFoundBy2Names() throws CommandNotFoundException {
        Class<?> expectedClass = FakeCommandDefinitionWithoutOptions.class;
        String commandName = "fake3";
        String commandName2 = "fake-3";

        Class<?> commandDefinitionClass = service.find(PACKAGE, commandName);
        Class<?> commandDefinitionClass2 = service.find(PACKAGE, commandName2);

        assertEquals(expectedClass, commandDefinitionClass);
        assertEquals(expectedClass, commandDefinitionClass2);
    }

    /*
     *
     */

    @Test
    public void givenClassWithoutPublicConstructor_ShouldThrowException() {
        String expectedMessage = format(IllegalCommandDefinitionException.MESSAGE, AnnotationsUtils.class.getCanonicalName());
        exception.expect(IllegalCommandDefinitionException.class);
        exception.expectMessage(expectedMessage);

        service.instantiate(AnnotationsUtils.class);
    }

    @Test
    public void givenAbstractClass_ShouldThrowException() {
        String expectedMessage = format(IllegalCommandDefinitionException.MESSAGE, Option.class.getCanonicalName());
        exception.expect(IllegalCommandDefinitionException.class);
        exception.expectMessage(expectedMessage);

        service.instantiate(Option.class);
    }

    @Test
    public void givenPrimitiveType_ShouldThrowException() {
        String expectedMessage = format(IllegalCommandDefinitionException.MESSAGE, int.class.getCanonicalName());
        exception.expect(IllegalCommandDefinitionException.class);
        exception.expectMessage(expectedMessage);

        service.instantiate(int.class);
    }

    @Test
    public void givenValidCommandDefinitionClass_ShouldReturnAnInstance() {
        DuplicatedCommandDefinition instance = service.instantiate(DuplicatedCommandDefinition.class);

        assertEquals(DuplicatedCommandDefinition.class, instance.getClass());
    }


}

