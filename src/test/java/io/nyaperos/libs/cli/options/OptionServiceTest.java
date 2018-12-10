package io.nyaperos.libs.cli.options;

import io.nyaperos.libs.cli.fakes.packages.FakeCommandDefinitionWithOptions;
import lombok.val;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.nyaperos.libs.cli.TestUtils.assertContainsSameObject;
import static io.nyaperos.libs.cli.TestUtils.assertNotContainsSameObject;
import static org.junit.jupiter.api.Assertions.assertNotSame;

class OptionServiceTest {

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
}
