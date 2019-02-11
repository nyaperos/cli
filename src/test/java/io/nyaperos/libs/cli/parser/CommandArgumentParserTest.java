package io.nyaperos.libs.cli.parser;

import io.nyaperos.libs.cli.parser.options.ParsedOption;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.omg.PortableServer.POA;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandArgumentParserTest {

    private static final String[] ARGUMENTS = {"command", "-arg1", "value1", "-arg2", "value2"};
    private static final String[] ARGUMENTS_WITHOUT_OPTIONS = {"command1", "command2", "command3"};
    private static final String[] EMPTY_ARGUMENTS = {};

    /*Command*/

    @Test
    void givenStringArray_ShouldReturnItsCommand() {
        Optional<String> command = CommandArgumentParser.getCommand(ARGUMENTS);

        val expectedCommand = Optional.of("command");
        assertEquals(expectedCommand, command);
    }

    @Test
    void givenStringArrayWithThreeWordsCommand_ShouldReturnTheseTheThreeWords() {
        String[] threeWordsCommandArguments = {"command1", "command2", "command3", "-arg1", "value1", "-arg2", "value2"};

        Optional<String> command = CommandArgumentParser.getCommand(threeWordsCommandArguments);

        val expectedCommand = Optional.of("command1 command2 command3");
        assertEquals(expectedCommand, command);
    }

    @Test
    void givenStringArrayWithoutOptions_ShouldReturnItsCommand() {
        Optional<String> command = CommandArgumentParser.getCommand(ARGUMENTS_WITHOUT_OPTIONS);

        val expectedCommand = Optional.of("command1 command2 command3");
        assertEquals(expectedCommand, command);
    }


    @Test
    void givenStringArrayWithoutCommand_ShouldReturnEmptyOptionalAsCommand() {
        String[] argumentsWithoutCommand = {"-arg1", "value1", "-arg2", "value2"};

        Optional<String> command = CommandArgumentParser.getCommand(argumentsWithoutCommand);

        assertEquals(Optional.empty(), command);
    }

    @Test
    void givenEmptyStringArray_ShouldReturnEmptyOptionalAsCommand() {
        Optional<String> command = CommandArgumentParser.getCommand(EMPTY_ARGUMENTS);

        assertEquals(Optional.empty(), command);
    }

    /*Options*/

    @Test
    void givenStringArray_ShouldReturnItsOptions() {
        List<ParsedOption> options = CommandArgumentParser.getOptions(ARGUMENTS);

        List<ParsedOption> expectedOptions = asList(
                new ParsedOption("-arg1", "value1"),
                new ParsedOption("-arg2", "value2"));
        assertEquals(expectedOptions, options);
    }

    @Test
    void givenStringArrayWithoutOptions_ShouldReturnEmptyListAsOptions() {
        List<ParsedOption> options = CommandArgumentParser.getOptions(ARGUMENTS_WITHOUT_OPTIONS);

        assertEquals(Collections.emptyList(), options);
    }

    @Test
    void givenEmptyStringArray_ShouldReturnEmptyListAsOptions() {
        List<ParsedOption> options = CommandArgumentParser.getOptions(EMPTY_ARGUMENTS);

        assertEquals(Collections.emptyList(), options);
    }
}
