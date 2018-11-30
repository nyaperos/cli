package io.nyaperos.libs.cli.utils;

import io.nyaperos.libs.cli.fakes.packages.FakeCommandDefinitionWithOptions;
import io.nyaperos.libs.cli.fakes.packages.duplicated.FakeNonCommandDefinition;
import io.nyaperos.libs.cli.fakes.options.FakeOption;
import io.nyaperos.libs.cli.options.OptionDefinition;
import lombok.val;
import org.junit.Test;

import java.util.List;

import static io.nyaperos.libs.cli.TestUtils.asSet;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FieldUtilsTest {

    @Test
    public void givenClassWithoutFakeOptionFields_ShouldReturnEmptyList() {
        val emptyOptionClass = new FakeNonCommandDefinition();
        List<FakeOption> options = FieldUtils.findFields(emptyOptionClass, FakeOption.class);

        assertTrue(options.isEmpty());
    }

    @Test
    public void givenClassWithTwoFakeOptionFields_ShouldReturnListWithTheseTwoEqualFieldInstances() {
        val emptyOptionClass = new FakeCommandDefinitionWithOptions();
        val expectedOptions = asSet(emptyOptionClass.name, emptyOptionClass.history);

        List<FakeOption> options = FieldUtils.findFields(emptyOptionClass, FakeOption.class);

        assertEquals(expectedOptions, asSet(options.toArray()));
    }

    @Test
    public void givenClassWithTwoFields_WhenParentClassOfTheseFieldsIsSearched_ShouldReturnListWithTheseTwoEqualFieldInstances() {
        val emptyOptionClass = new FakeCommandDefinitionWithOptions();
        val expectedOptions = asSet(emptyOptionClass.name, emptyOptionClass.history);

        List<OptionDefinition> options = FieldUtils.findFields(emptyOptionClass, OptionDefinition.class);

        assertEquals(expectedOptions, asSet(options.toArray()));
    }
}
