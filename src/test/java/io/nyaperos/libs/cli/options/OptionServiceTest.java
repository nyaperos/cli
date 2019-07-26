package io.nyaperos.libs.cli.options;

import io.nyaperos.libs.cli.parser.options.ParsedOption;
import io.nyaperos.libs.cli.tests.doubles.options.FakeOption;
import io.nyaperos.libs.cli.tests.packages.FakeCommandDefinitionWithOptions;
import lombok.val;
import org.junit.jupiter.api.Test;

import static io.nyaperos.libs.cli.OptionalMatchers.hasValue;
import static io.nyaperos.libs.cli.OptionalMatchers.isEmpty;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.collection.IsIn.in;
import static org.hamcrest.core.Is.is;

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

        assertThat(commandDefinition.name, is(in(optionsDefinition)));
        assertThat(commandDefinition.history, is(in(optionsDefinition)));
    }

    @Test
    void extracted_options_from_different_command_definition_instances_should_be_different_option_instances() {
        val commandDefinition = new FakeCommandDefinitionWithOptions();
        val commandDefinition2 = new FakeCommandDefinitionWithOptions();

        val optionsDefinition = OptionService.extract(commandDefinition);

        assertThat(commandDefinition2.name, is(not(in(optionsDefinition))));
        assertThat(commandDefinition2.history, is(not(in(optionsDefinition))));
    }

    @Test
    void should_not_fill_any_option_because_parsed_option_has_different_alias() {
        val command = new FakeCommandClass();
        val parsedOptions = singletonList(new ParsedOption("fake-alias", ""));

        OptionService.fill(command, parsedOptions);

        assertThat(command.history.value(), isEmpty());
        assertThat(command.name.value(), isEmpty());
    }

    @Test
    void should_fill_only_the_option_that_matches_with_alias_parsed_option() {
        val command = new FakeCommandClass();
        val parsedOptions = singletonList(new ParsedOption("name", "some-fake-value"));

        OptionService.fill(command, parsedOptions);

        assertThat(command.history.value(), isEmpty());
        assertThat(command.name.value(), hasValue("some-fake-value"));
    }

    @Test
    void should_be_able_to_fill_option_referenced_by_two_aliases() {
        val longOptionsCommand = new FakeCommandClass();
        val shortOptionsCommand = new FakeCommandClass();
        val longParsedOptions = singletonList(new ParsedOption("history", "some-fake-value"));
        val shortParsedOptions = singletonList(new ParsedOption("h", "some-fake-value"));

        OptionService.fill(longOptionsCommand, longParsedOptions);
        OptionService.fill(shortOptionsCommand, shortParsedOptions);

        assertThat(longOptionsCommand.history.value(), hasValue("some-fake-value"));
        assertThat(shortOptionsCommand.history.value(), hasValue("some-fake-value"));
    }


}
