package io.nyaperos.libs.cli.utils;

import io.nyaperos.libs.cli.tests.doubles.options.FakeOption;
import io.nyaperos.libs.cli.tests.packages.FakeCommandDefinitionWithOptions;
import io.nyaperos.libs.cli.tests.packages.duplicated.FakeNonCommandDefinition;
import io.nyaperos.libs.cli.options.Option;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.nyaperos.libs.cli.TestUtils.asSet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FieldUtilsTest {

    @Test
    void class_without_fake_option_fields_should_return_empty_list() {
        val emptyOptionClass = new FakeNonCommandDefinition();
        List<FakeOption> options = FieldUtils.findFields(emptyOptionClass, FakeOption.class);

        assertTrue(options.isEmpty());
    }

    @Test
    void class_with_two_fake_option_fields_should_return_list_with_these_two_equal_field_instances() {
        val emptyOptionClass = new FakeCommandDefinitionWithOptions();
        val expectedOptions = asSet(emptyOptionClass.name, emptyOptionClass.history);

        List<FakeOption> options = FieldUtils.findFields(emptyOptionClass, FakeOption.class);

        assertEquals(expectedOptions, asSet(options.toArray()));
    }

    @Test
    void find_fields_searching_by_parent_class_should_return_list_with_the_two_options_in_child_instance() {
        val emptyOptionClass = new FakeCommandDefinitionWithOptions();
        val expectedOptions = asSet(emptyOptionClass.name, emptyOptionClass.history);

        List<Option> options = FieldUtils.findFields(emptyOptionClass, Option.class);

        assertEquals(expectedOptions, asSet(options.toArray()));
    }
}
