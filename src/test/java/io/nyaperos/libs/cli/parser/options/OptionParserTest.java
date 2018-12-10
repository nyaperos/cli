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
    void givenNonOptions_ShouldReturnEmptyList() {
        List<ParsedOption> options = OptionParser.parse(emptyList());
        assertEquals(emptyList(), options);
    }

    @Test
    void givenFlagOption_ShouldParseItAndReturnEmptyString() {
        List<ParsedOption> options = OptionParser.parse(singletonList("-o"));

        val expected = singletonList(new ParsedOption("-o", ""));
        assertTrue(options.get(0).valueIsEmpty());
        assertEquals(expected, options);
    }

    @Test
    void givenTwoFlagOptions_ShouldParseItAndReturnTwoOptionsWithEmptyString() {
        List<ParsedOption> options = OptionParser.parse(asList("-o", "-f"));

        ParsedOption optionO = new ParsedOption("-o", "");
        ParsedOption optionF = new ParsedOption("-f", "");

        val expected = asList(optionO, optionF);
        assertEquals(expected, options);
        assertTrue(options.get(0).valueIsEmpty());
        assertTrue(options.get(1).valueIsEmpty());

    }

    @Test
    void givenOneShortOptionValue_ShouldParseIt() {
        List<ParsedOption> options = OptionParser.parse(asList("-o", "option"));

        val expected = singletonList(new ParsedOption("-o", "option"));
        assertEquals(expected, options);
    }

    @Test
    void givenOneLongOptionValue_ShouldParseIt() {
        List<ParsedOption> options = OptionParser.parse(asList("--option", "option"));

        val expected = singletonList(new ParsedOption("--option", "option"));
        assertEquals(expected, options);
    }

    @Test
    void givenOneOptionAndMultipleValue_ShouldParseIt() {
        List<ParsedOption> options = OptionParser.parse(asList("--option", "option1", "option2", "option3"));

        val expected = singletonList(new ParsedOption("--option", "option1 option2 option3"));
        assertEquals(expected, options);
    }

    @Test
    void givenOneOptionAndOneValueWithSlashes_ShouldParseItAsOnlyOneOption() {
        List<ParsedOption> options = OptionParser.parse(asList("--option", "option-1, option-2, option-3"));

        val expected = singletonList(new ParsedOption("--option", "option-1, option-2, option-3"));
        assertEquals(expected, options);
    }

    @Disabled("sure this is the desired behaviour? It should provoke problems with subcommands or parameters")
    void givenMultipleOptionsWithValues_ShouldParseIt() {
        List<ParsedOption> options = OptionParser.parse(asList(
                "--option", "option1",
                "--argument", "argument1", "argument2"));

        val expected = asList(
                new ParsedOption("--option", "option1"),
                new ParsedOption("--argument", "argument1 argument2"));
        assertEquals(expected, options);
    }
}

