package io.nyaperos.libs.cli.options;

import io.nyaperos.libs.cli.tests.doubles.options.Fake123StringOptionAdapter;
import io.nyaperos.libs.cli.tests.doubles.options.FakeOption;
import io.nyaperos.libs.cli.parser.options.ParsedOption;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static io.nyaperos.libs.cli.tests.doubles.options.Fake123StringOptionAdapter.append123Suffix;
import static java.util.Arrays.asList;
import static java.util.Collections.*;
import static org.junit.jupiter.api.Assertions.*;

class OptionTest {

    private static final List<String> ALIASES = Arrays.asList("fake-alias1", "fake-alias2");
    private static final String DESCRIPTION = "fake-description";
    private static final Fake123StringOptionAdapter ADAPTER = new Fake123StringOptionAdapter();

    @Test
    void option_with_null_aliases_should_throw_exception() {
        assertThrows(NullPointerException.class, () -> new FakeOption(null, DESCRIPTION, ADAPTER));
    }

    @Test
    void option_with_null_description_should_throw_exception() {
        assertThrows(NullPointerException.class, () -> new FakeOption(ALIASES, null, ADAPTER));
    }

    @Test
    void option_with_null_adapter_should_throw_exception() {
        assertThrows(NullPointerException.class, () -> new FakeOption(ALIASES, DESCRIPTION, null));
    }

    @Test
    void given_not_null_arguments_should_return_it() {
        val option = new FakeOption(ALIASES, DESCRIPTION, ADAPTER);
        assertEquals(ALIASES, option.getAliases());
        assertEquals(DESCRIPTION, option.getDescription());
    }

    @Test
    void when_value_is_set_can_retrieve_value() {
        val value = "fake-value";
        val parsedOption = new ParsedOption("fake-option", value);
        val expectedValue = Optional.of(append123Suffix(value));

        val option = new FakeOption(ALIASES, DESCRIPTION, ADAPTER);
        option.setValue(parsedOption);
        assertEquals(expectedValue, option.value());
    }

    @Test
    void when_has_not_the_same_alias() {
        val optionDefinition = new FakeOption(singletonList("fake-alias"), "");
        val parsedOption = new ParsedOption("different-fake-alias", "fake-value");

        assertFalse(optionDefinition.hasSameAlias(parsedOption));

    }

    @Test
    void when_option_has_the_same_alias() {
        val optionDefinition = new FakeOption(singletonList("fake-alias"), "");
        val parsedOption = new ParsedOption("fake-alias", "fake-value");

        assertTrue(optionDefinition.hasSameAlias(parsedOption));
    }

    @Test
    void when_option_has_multiple_alias_and_one_of_them_are_the_same() {
        val optionDefinition = new FakeOption(asList("fake-alias", "other-fake-alias"), "");
        val parsedOption = new ParsedOption("other-fake-alias", "fake-value");

        assertTrue(optionDefinition.hasSameAlias(parsedOption));
    }

}
