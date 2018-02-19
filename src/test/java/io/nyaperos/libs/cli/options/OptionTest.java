package io.nyaperos.libs.cli.options;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import io.nyaperos.libs.cli.options.adapters.StringOptionAdapter;
import lombok.val;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionTest {

    static class DummyOption extends Option<String> {
        DummyOption(List<String> alias, String description, StringOptionAdapter adapter) {
            super(alias, description, adapter);
        }
    }

    private static final List<String> ALIASES = Arrays.asList("fake-alias1", "fake-alias2");
    private static final String DESCRIPTION = "fake-description";
    private static final StringOptionAdapter ADAPTER = new StringOptionAdapter();

    @Test(expected = NullPointerException.class)
    public void givenNullAliases_ShouldThrowException() {
         new DummyOption(null, DESCRIPTION, ADAPTER);
    }

    @Test(expected = NullPointerException.class)
    public void givenNullDescription_ShouldThrowException() {
        new DummyOption(ALIASES, null, ADAPTER);
    }

    @Test(expected = NullPointerException.class)
    public void givenNullAdapter_ShouldThrowException() {
        new DummyOption(ALIASES, DESCRIPTION, null);
    }

    @Test
    public void givenNotNullArguments_ShouldReturnIt() {
        val option = new DummyOption(ALIASES, DESCRIPTION, ADAPTER);
        assertEquals(ALIASES, option.getAliases());
        assertEquals(DESCRIPTION, option.getDescription());
    }

}
