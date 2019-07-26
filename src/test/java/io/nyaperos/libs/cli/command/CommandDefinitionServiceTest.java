package io.nyaperos.libs.cli.command;

import io.nyaperos.libs.cli.command.exceptions.CommandNotFoundException;
import io.nyaperos.libs.cli.command.exceptions.IllegalCommandDefinitionException;
import io.nyaperos.libs.cli.command.exceptions.MultipleAliasesAssignedToCommandException;
import io.nyaperos.libs.cli.options.Option;
import io.nyaperos.libs.cli.tests.packages.FakeCommandDefinitionWithoutOptions;
import io.nyaperos.libs.cli.tests.packages.duplicated.DuplicatedCommandDefinition;
import io.nyaperos.libs.cli.tests.packages.duplicated.DuplicatedCommandDefinition2;
import io.nyaperos.libs.cli.utils.AnnotationsUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.text.MessageFormat.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandDefinitionServiceTest {

    private static final Package PACKAGE = FakeCommandDefinitionWithoutOptions.class.getPackage();

    @Test
    void alias_does_not_match_with_any_command_should_throw_exception() {
        String commandAlias = "fake-non-existent-alias";
        assertThrows(
                CommandNotFoundException.class,
                () -> CommandDefinitionService.find(PACKAGE, commandAlias),
                format(CommandNotFoundException.MESSAGE, commandAlias)
        );
    }

    @Test
    void when_alias_is_assigned_to_multiple_commands_should_throw_exception() {
        String commandAlias = "duplicated-fake";
        List<Class<?>> classes = Arrays.asList(DuplicatedCommandDefinition.class, DuplicatedCommandDefinition2.class);

        assertThrows(
                MultipleAliasesAssignedToCommandException.class,
                () -> CommandDefinitionService.find(PACKAGE, commandAlias),
                format(MultipleAliasesAssignedToCommandException.MESSAGE, commandAlias, classes)
        );
    }

    @Test
    void command_definition_with_2_alias_can_be_found_by_both() throws CommandNotFoundException {
        String commandAlias = "fake3";
        String commandAlias2 = "fake-3";

        Class<?> commandDefinitionClass = CommandDefinitionService.find(PACKAGE, commandAlias);
        Class<?> commandDefinitionClass2 = CommandDefinitionService.find(PACKAGE, commandAlias2);

        assertThat(commandDefinitionClass, is(FakeCommandDefinitionWithoutOptions.class));
        assertThat(commandDefinitionClass2, is(FakeCommandDefinitionWithoutOptions.class));
    }

    /*
     *
     */

    @Test
    void class_without_empty_constructor_should_throw_exception() {
        assertThrows(
                IllegalCommandDefinitionException.class,
                () -> CommandDefinitionService.instantiate(AnnotationsUtils.class),
                format(IllegalCommandDefinitionException.MESSAGE, AnnotationsUtils.class.getCanonicalName())
        );
    }

    @Test
    void abstract_class_should_throw_exception() {
        assertThrows(
                IllegalCommandDefinitionException.class,
                () -> CommandDefinitionService.instantiate(Option.class),
                format(IllegalCommandDefinitionException.MESSAGE, Option.class.getCanonicalName())
        );
    }

    @Test
        //TODO: this test has no sense when instantiate is pushed down
    void primitive_type_should_throw_exception() {
        assertThrows(
                IllegalCommandDefinitionException.class,
                () -> CommandDefinitionService.instantiate(int.class),
                format(IllegalCommandDefinitionException.MESSAGE, int.class.getCanonicalName())
        );
    }

    @Test
    void valid_command_should_return_an_instance_of_a_class() {
        DuplicatedCommandDefinition instance = CommandDefinitionService.instantiate(DuplicatedCommandDefinition.class);

        assertThat(instance.getClass(), is(DuplicatedCommandDefinition.class));
    }


}

