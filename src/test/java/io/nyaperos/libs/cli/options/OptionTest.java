package io.nyaperos.libs.cli.options;

import lombok.val;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static io.nyaperos.libs.cli.options.Fake123StringOptionAdapter.append123Suffix;
import static org.junit.Assert.assertEquals;

public class OptionTest {

    private static final List<String> NAMES = Arrays.asList("fake-alias1", "fake-alias2");
    private static final String DESCRIPTION = "fake-description";
    private static final Fake123StringOptionAdapter ADAPTER = new Fake123StringOptionAdapter();

    @Test(expected = NullPointerException.class)
    public void givenNullNames_ShouldThrowException() {
        new FakeOption(null, DESCRIPTION, ADAPTER);
    }

    @Test(expected = NullPointerException.class)
    public void givenNullDescription_ShouldThrowException() {
        new FakeOption(NAMES, null, ADAPTER);
    }

    @Test(expected = NullPointerException.class)
    public void givenNullAdapter_ShouldThrowException() {
        new FakeOption(NAMES, DESCRIPTION, null);
    }

    @Test
    public void givenNotNullArguments_ShouldReturnIt() {
        val option = new FakeOption(NAMES, DESCRIPTION, ADAPTER);
        assertEquals(NAMES, option.getCommandNames());
        assertEquals(DESCRIPTION, option.getDescription());
    }

    @Test
    public void whenValueAsStringIsSet_ThenRetrieveOptionalWithValue() {
        val value = "fake-value";
        val expectedValue = Optional.of(append123Suffix(value));

        val option = new FakeOption(NAMES, DESCRIPTION, ADAPTER);
        option.setValue(value);
        assertEquals(expectedValue, option.value());
    }

}
