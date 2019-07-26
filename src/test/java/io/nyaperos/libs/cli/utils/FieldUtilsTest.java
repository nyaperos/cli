package io.nyaperos.libs.cli.utils;

import io.nyaperos.libs.cli.options.Option;
import io.nyaperos.libs.cli.tests.doubles.options.FakeOption;
import io.nyaperos.libs.cli.tests.packages.FakeCommandDefinitionWithOptions;
import io.nyaperos.libs.cli.tests.packages.duplicated.FakeNonCommandDefinition;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.in;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.Is.is;

class FieldUtilsTest {

    @Test
    void class_without_option_fields_should_return_empty_list() {
        val emptyOptionClass = new FakeNonCommandDefinition();
        Set<FakeOption> options = FieldUtils.findFields(emptyOptionClass, FakeOption.class);

        assertThat(options, is(empty()));
    }

    @Test
    void class_with_two_option_fields_should_return_list_with_these_two_equal_field_instances() {
        val emptyOptionClass = new FakeCommandDefinitionWithOptions();

        Set<FakeOption> options = FieldUtils.findFields(emptyOptionClass, FakeOption.class);

        assertThat(emptyOptionClass.name, is(in(options)));
        assertThat(emptyOptionClass.history, is(in(options)));
    }

    @Test
    void find_fields_by_parent_class_should_return_list_with_the_two_options_in_child_instance() {
        val emptyOptionClass = new FakeCommandDefinitionWithOptions();

        Set<Option> options = FieldUtils.findFields(emptyOptionClass, Option.class);

        assertThat(emptyOptionClass.name, is(in(options)));
        assertThat(emptyOptionClass.history, is(in(options)));
    }
}
