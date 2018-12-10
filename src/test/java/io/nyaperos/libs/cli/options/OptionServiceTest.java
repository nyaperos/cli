package io.nyaperos.libs.cli.options;

import io.nyaperos.libs.cli.fakes.options.FakeOption;
import io.nyaperos.libs.cli.fakes.packages.FakeCommandDefinitionWithOptions;
import io.nyaperos.libs.cli.parser.options.ParsedOption;
import joptsimple.OptionParser;
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
    void givenCommandDefinitionWithOptionField_ShouldReturnIt() {
        val commandDefinition = new FakeCommandDefinitionWithOptions();

        val optionsDefinition = OptionService.extract(commandDefinition);

        assertContainsSameObject(optionsDefinition, commandDefinition.name);
        assertContainsSameObject(optionsDefinition, commandDefinition.history);
    }

    @Test
    void givenDifferentInstancesOfCommand_ShouldBeDifferentOptionDefinitionInstances() {
        val commandDefinition = new FakeCommandDefinitionWithOptions();
        val commandDefinition2 = new FakeCommandDefinitionWithOptions();

        assertNotSame(commandDefinition2.name, commandDefinition.name);
    }

    @Test
    void givenDifferentInstancesOfCommand_ShouldReturnDifferentOptionDefinitionInstances() {
        val commandDefinition = new FakeCommandDefinitionWithOptions();
        val commandDefinition2 = new FakeCommandDefinitionWithOptions();

        val optionsDefinition = OptionService.extract(commandDefinition);

        assertNotContainsSameObject(optionsDefinition, commandDefinition2.name);
        assertNotContainsSameObject(optionsDefinition, commandDefinition2.history);
    }

    @Test
    void givenParsedOptionWithDifferentAlias_ShouldNotFillAnyOption() {
        val command = new FakeCommandClass();
        val parsedOptions = singletonList(new ParsedOption("fake-alias", ""));

        OptionService.fill(command, parsedOptions);

        assertFalse(command.history.value().isPresent());
        assertFalse(command.name.value().isPresent());
    }

    @Test
    void givenParsedOptionWithSameAlias_ShouldFillOnlySameOption() {
        val command = new FakeCommandClass();
        val parsedOptions = singletonList(new ParsedOption("name", "some-fake-value"));

        OptionService.fill(command, parsedOptions);

        assertFalse(command.history.value().isPresent());
        assertTrue(command.name.value().isPresent());
    }

    @Test
    void givenParsedOptionAndOptionWith2Alias_ShouldBeAbleToFillItWithBothAliases() {
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
