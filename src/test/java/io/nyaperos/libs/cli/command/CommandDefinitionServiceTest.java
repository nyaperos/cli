package io.nyaperos.libs.cli.command;

import io.nyaperos.libs.cli.command.exceptions.CommandNotFoundException;
import io.nyaperos.libs.cli.command.exceptions.MultipleNamesAssignedToCommandException;
import io.nyaperos.libs.cli.fakes.DuplicatedFakeClass;
import io.nyaperos.libs.cli.fakes.DuplicatedFakeClass2;
import io.nyaperos.libs.cli.fakes.subpackage.FakeClass3;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;

import static java.text.MessageFormat.format;
import static org.junit.Assert.assertEquals;

public class CommandDefinitionServiceTest {

    private static final Package PACKAGE = DuplicatedFakeClass2.class.getPackage();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private static CommandDefinitionService service;

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
        List<Class<?>> classes = Arrays.asList(DuplicatedFakeClass.class, DuplicatedFakeClass2.class);
        String expectedMessage = format(MultipleNamesAssignedToCommandException.MESSAGE, commandName, classes);

        exception.expect(MultipleNamesAssignedToCommandException.class);
        exception.expectMessage(expectedMessage);

        service.find(PACKAGE, commandName);
    }

    @Test
    public void givenCommandDefinitionWith2Names_ThenCanBeFoundBy2Names() throws CommandNotFoundException {
        Class<?> expectedClass = FakeClass3.class;
        String commandName = "fake3";
        String commandName2 = "fake-3";

        Class<?> commandDefinitionClass = service.find(PACKAGE, commandName);
        Class<?> commandDefinitionClass2 = service.find(PACKAGE, commandName2);

        assertEquals(expectedClass, commandDefinitionClass);
        assertEquals(expectedClass, commandDefinitionClass2);
    }


}

