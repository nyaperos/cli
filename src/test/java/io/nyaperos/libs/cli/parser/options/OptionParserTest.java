package io.nyaperos.libs.cli.parser.options;

import lombok.val;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OptionParserTest {

    @Test
    public void givenNonOptions_ShouldReturnEmptyList() {
        List<OptionDTO> options = OptionParser.parse(emptyList());
        assertEquals(emptyList(), options);
    }

    @Test
    public void givenFlagOption_ShouldParseItAndReturnEmptyString() {
        List<OptionDTO> options = OptionParser.parse(singletonList("-o"));

        val expected = singletonList(new OptionDTO("-o", ""));
        assertTrue(options.get(0).valueIsEmpty());
        assertEquals(expected, options);
    }

    @Test
    public void givenTwoFlagOptions_ShouldParseItAndReturnTwoOptionsWithEmptyString() {
        List<OptionDTO> options = OptionParser.parse(asList("-o", "-f"));

        OptionDTO optionO = new OptionDTO("-o", "");
        OptionDTO optionF = new OptionDTO("-f", "");

        val expected = asList(optionO, optionF);
        assertEquals(expected, options);
        assertTrue(options.get(0).valueIsEmpty());
        assertTrue(options.get(1).valueIsEmpty());

    }

    @Test
    public void givenOneShortOptionValue_ShouldParseIt() {
        List<OptionDTO> options = OptionParser.parse(asList("-o", "option"));

        val expected = singletonList(new OptionDTO("-o", "option"));
        assertEquals(expected, options);
    }

    @Test
    public void givenOneLongOptionValue_ShouldParseIt() {
        List<OptionDTO> options = OptionParser.parse(asList("--option", "option"));

        val expected = singletonList(new OptionDTO("--option", "option"));
        assertEquals(expected, options);
    }

    @Test
    public void givenOneOptionAndMultipleValue_ShouldParseIt() {
        List<OptionDTO> options = OptionParser.parse(asList("--option", "option1", "option2", "option3"));

        val expected = singletonList(new OptionDTO("--option", "option1 option2 option3"));
        assertEquals(expected, options);
    }

    @Test
    public void givenOneOptionAndOneValueWithSlashes_ShouldParseItAsOnlyOneOption() {
        List<OptionDTO> options = OptionParser.parse(asList("--option", "option-1, option-2, option-3"));

        val expected = singletonList(new OptionDTO("--option", "option-1, option-2, option-3"));
        assertEquals(expected, options);
    }

    @Test
    public void givenMultipleOptionsWithValues_ShouldParseIt() {
        List<OptionDTO> options = OptionParser.parse(asList(
                "--option", "option1",
                "--argument", "argument1", "argument2"));

        val expected = asList(
                new OptionDTO("--option", "option1"),
                new OptionDTO("--argument", "argument1 argument2"));
        assertEquals(expected, options);
    }
}

