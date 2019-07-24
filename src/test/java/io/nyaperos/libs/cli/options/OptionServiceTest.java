package io.nyaperos.libs.cli.options;

import io.nyaperos.libs.cli.parser.options.ParsedOption;
import io.nyaperos.libs.cli.tests.doubles.options.FakeOption;
import io.nyaperos.libs.cli.tests.packages.FakeCommandDefinitionWithOptions;
import lombok.val;
import org.junit.jupiter.api.Test;

import static io.nyaperos.libs.cli.TestUtils.assertContainsSameObject;
import static io.nyaperos.libs.cli.TestUtils.assertNotContainsSameObject;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

class OptionServiceTest {

    public class FakeCommandClass {
        public FakeOption name = new FakeOption(singletonList("name"), "fake name description");
        public FakeOption history = new FakeOption(asList("h", "history"), "fake history description");
        public String potato = "this is a potato";
    }

    @Test
    void should_be_able_to_get_option_fields_of_a_command() {
        val commandDefinition = new FakeCommandDefinitionWithOptions();

        val optionsDefinition = OptionService.extract(commandDefinition);

        assertContainsSameObject(optionsDefinition, commandDefinition.name);
        assertContainsSameObject(optionsDefinition, commandDefinition.history);
    }

    @Test
    void extracted_options_from_different_command_definition_instances_should_be_different_option_instances() {
        val commandDefinition = new FakeCommandDefinitionWithOptions();
        val commandDefinition2 = new FakeCommandDefinitionWithOptions();

        val optionsDefinition = OptionService.extract(commandDefinition);

        assertNotContainsSameObject(optionsDefinition, commandDefinition2.name);
        assertNotContainsSameObject(optionsDefinition, commandDefinition2.history);
    }

    @Test
    void should_not_fill_any_option_because_parsed_option_has_different_alias() {
        val command = new FakeCommandClass();
        val parsedOptions = singletonList(new ParsedOption("fake-alias", ""));

        OptionService.fill(command, parsedOptions);

        assertFalse(command.history.value().isPresent());
        assertFalse(command.name.value().isPresent());
    }

    @Test
    void should_fill_only_the_option_that_matches_with_alias_parsed_option() {
        val command = new FakeCommandClass();
        val parsedOptions = singletonList(new ParsedOption("name", "some-fake-value"));

        OptionService.fill(command, parsedOptions);

        assertFalse(command.history.value().isPresent());
        assertTrue(command.name.value().isPresent());
    }

    @Test
    void should_be_able_to_fill_option_referenced_by_two_aliases() {
        val longOptionsCommand = new FakeCommandClass();
        val shortOptionsCommand = new FakeCommandClass();
        val longParsedOptions = singletonList(new ParsedOption("history", "some-fake-value"));
        val shortParsedOptions = singletonList(new ParsedOption("h", "some-fake-value"));

        OptionService.fill(longOptionsCommand, longParsedOptions);
        OptionService.fill(shortOptionsCommand, shortParsedOptions);

        assertTrue(longOptionsCommand.history.value().isPresent());
        assertTrue(shortOptionsCommand.history.value().isPresent());
    }

}
