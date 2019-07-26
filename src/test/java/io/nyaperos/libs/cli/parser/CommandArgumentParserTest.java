package io.nyaperos.libs.cli.parser;

import io.nyaperos.libs.cli.parser.options.ParsedOption;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static io.nyaperos.libs.cli.OptionalMatchers.hasValue;
import static io.nyaperos.libs.cli.OptionalMatchers.isEmpty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class CommandArgumentParserTest {

    private static final String[] ARGUMENTS = {"command", "-arg1", "value1", "-arg2", "value2"};
    private static final String[] ARGUMENTS_WITHOUT_OPTIONS = {"command1", "command2", "command3"};
    private static final String[] EMPTY_ARGUMENTS = {};

    /*Command*/

    @Test
    void string_array_with_one_element_should_return_the_command() {
        Optional<String> command = CommandArgumentParser.getCommand(ARGUMENTS);

        assertThat(command, hasValue(("command")));
    }

    @Test
    void should_return_the_the_command_and_subcommands_joined_as_string() {
        String[] threeWordsCommandArguments = {"command1", "command2", "command3", "-arg1", "value1", "-arg2", "value2"};

        Optional<String> command = CommandArgumentParser.getCommand(threeWordsCommandArguments);

        assertThat(command, hasValue("command1 command2 command3"));
    }

    @Test
    void array_without_options_should_return_commands_joined_as_string() {
        Optional<String> command = CommandArgumentParser.getCommand(ARGUMENTS_WITHOUT_OPTIONS);

        assertThat(command, hasValue("command1 command2 command3"));
    }


    @Test
    void string_array_without_command_should_return_empty_optional() {
        String[] argumentsWithoutCommand = {"-arg1", "value1", "-arg2", "value2"};

        Optional<String> command = CommandArgumentParser.getCommand(argumentsWithoutCommand);

        assertThat(command, isEmpty());
    }

    @Test
    void empty_string_array_should_return_empty_optional() {
        Optional<String> command = CommandArgumentParser.getCommand(EMPTY_ARGUMENTS);

        assertThat(command, isEmpty());
    }

    /*Options*/

    @Test
    void string_array_should_return_its_options() {
        List<ParsedOption> options = CommandArgumentParser.getOptions(ARGUMENTS);

        assertThat(new ParsedOption("-arg1", "value1"), is(in(options)));
        assertThat(new ParsedOption("-arg2", "value2"), is(in(options)));
    }

    @Test
    void string_array_without_options_should_return_empty_list() {
        List<ParsedOption> options = CommandArgumentParser.getOptions(ARGUMENTS_WITHOUT_OPTIONS);

        assertThat(options, is(empty()));
    }

    @Test
    void empty_string_array_should_return_empty_list() {
        List<ParsedOption> options = CommandArgumentParser.getOptions(EMPTY_ARGUMENTS);

        assertThat(options, is(empty()));
    }
}
