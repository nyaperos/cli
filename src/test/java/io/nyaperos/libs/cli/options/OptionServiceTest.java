package io.nyaperos.libs.cli.options;

import io.nyaperos.libs.cli.fakes.packages.FakeCommandDefinitionWithOptions;
import lombok.val;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.nyaperos.libs.cli.TestUtils.assertContainsSameObject;
import static io.nyaperos.libs.cli.TestUtils.assertNotContainsSameObject;
import static org.junit.Assert.assertNotSame;

public class OptionServiceTest {

    private static OptionService service;

    @BeforeClass
    public static void setUpBeforeClass() {
        service = new OptionService();
    }

    @Test
    public void givenCommandDefinitionWithOptionField_ShouldReturnIt() {
        val commandDefinition = new FakeCommandDefinitionWithOptions();

        val optionsDefinition = service.get(commandDefinition);

        assertContainsSameObject(optionsDefinition, commandDefinition.name);
        assertContainsSameObject(optionsDefinition, commandDefinition.history);
    }

    @Test
    public void givenDifferentInstancesOfCommand_ShouldBeDifferentOptionDefinitionInstances() {
        val commandDefinition = new FakeCommandDefinitionWithOptions();
        val commandDefinition2 = new FakeCommandDefinitionWithOptions();

        assertNotSame(commandDefinition2.name, commandDefinition.name);
    }

    @Test
    public void givenDifferentInstancesOfCommand_ShouldReturnDifferentOptionDefinitionInstances() {
        val commandDefinition = new FakeCommandDefinitionWithOptions();
        val commandDefinition2 = new FakeCommandDefinitionWithOptions();

        val optionsDefinition = service.get(commandDefinition);

        assertNotContainsSameObject(optionsDefinition, commandDefinition2.name);
        assertNotContainsSameObject(optionsDefinition, commandDefinition2.history);
    }
}
