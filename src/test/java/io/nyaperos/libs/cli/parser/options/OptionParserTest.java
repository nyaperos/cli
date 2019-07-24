package io.nyaperos.libs.cli.parser.options;

import lombok.val;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OptionParserTest {

    @Test
    void empty_string_list_should_return_empty_list() {
        List<ParsedOption> options = OptionParser.parse(emptyList());
        assertEquals(emptyList(), options);
    }

    @Test
    void flag_option_should_return_empty_string_value() {
        List<ParsedOption> options = OptionParser.parse(singletonList("-o"));

        val expected = singletonList(new ParsedOption("-o", ""));
        assertTrue(options.get(0).valueIsEmpty());
        assertEquals(expected, options);
    }

    @Test
    void two_flag_options_should_return_two_options_with_empty_string_values() {
        List<ParsedOption> options = OptionParser.parse(asList("-o", "-f"));

        ParsedOption optionO = new ParsedOption("-o", "");
        ParsedOption optionF = new ParsedOption("-f", "");

        val expected = asList(optionO, optionF);
        assertEquals(expected, options);
        assertTrue(options.get(0).valueIsEmpty());
        assertTrue(options.get(1).valueIsEmpty());

    }

    @Test
    void short_option_alias_should_parse_it_and_fill_value() {
        List<ParsedOption> options = OptionParser.parse(asList("-o", "option"));

        val expected = singletonList(new ParsedOption("-o", "option"));
        assertEquals(expected, options);
    }

    @Test
    void long_option_alias_should_parse_it_and_fill_value() {
        List<ParsedOption> options = OptionParser.parse(asList("--option", "option"));

        val expected = singletonList(new ParsedOption("--option", "option"));
        assertEquals(expected, options);
    }

    @Test
    void one_option_with_multiple_value_should_parse_join_it_as_one_string() {
        List<ParsedOption> options = OptionParser.parse(asList("--option", "option1", "option2", "option3"));

        val expected = singletonList(new ParsedOption("--option", "option1 option2 option3"));
        assertEquals(expected, options);
    }

    @Test
    void one_option_with_a_value_with_slashes_should_parse_it_as_only_one_option() {
        List<ParsedOption> options = OptionParser.parse(asList("--option", "option-1 option-2 option-3"));

        val expected = singletonList(new ParsedOption("--option", "option-1 option-2 option-3"));
        assertEquals(expected, options);
    }

    @Disabled("sure this is the desired behaviour? It should provoke problems with subcommands or parameters")
    void given_multiple_options_with_values_should_parse_it() {
        List<ParsedOption> options = OptionParser.parse(asList(
                "--option", "option1",
                "--argument", "argument1", "argument2"));

        val expected = asList(
                new ParsedOption("--option", "option1"),
                new ParsedOption("--argument", "argument1 argument2"));
        assertEquals(expected, options);
    }
}

