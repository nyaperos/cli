package io.nyaperos.libs.cli.options;

import io.nyaperos.libs.cli.fakes.options.Fake123StringOptionAdapter;
import io.nyaperos.libs.cli.fakes.options.FakeOption;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static io.nyaperos.libs.cli.fakes.options.Fake123StringOptionAdapter.append123Suffix;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OptionTest {

    private static final List<String> ALIASES = Arrays.asList("fake-alias1", "fake-alias2");
    private static final String DESCRIPTION = "fake-description";
    private static final Fake123StringOptionAdapter ADAPTER = new Fake123StringOptionAdapter();

    @Test
    void givenNullAliases_ShouldThrowException() {
        assertThrows(NullPointerException.class, () -> new FakeOption(null, DESCRIPTION, ADAPTER));
    }

    @Test
    void givenNullDescription_ShouldThrowException() {
        assertThrows(NullPointerException.class, () -> new FakeOption(ALIASES, null, ADAPTER));
    }

    @Test
    void givenNullAdapter_ShouldThrowException() {
        assertThrows(NullPointerException.class, () -> new FakeOption(ALIASES, DESCRIPTION, null));
    }

    @Test
    void givenNotNullArguments_ShouldReturnIt() {
        val option = new FakeOption(ALIASES, DESCRIPTION, ADAPTER);
        assertEquals(ALIASES, option.getAliases());
        assertEquals(DESCRIPTION, option.getDescription());
    }

    @Test
    void whenValueAsStringIsSet_ThenRetrieveOptionalWithValue() {
        val value = "fake-value";
        val expectedValue = Optional.of(append123Suffix(value));

        val option = new FakeOption(ALIASES, DESCRIPTION, ADAPTER);
        option.setValue(value);
        assertEquals(expectedValue, option.value());
    }

}
