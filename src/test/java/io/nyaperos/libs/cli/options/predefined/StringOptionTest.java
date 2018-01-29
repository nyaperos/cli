package io.nyaperos.libs.cli.options.predefined;

import lombok.val;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class StringOptionTest {

    private static final class ChangeableValueStringOption extends StringOption {

        ChangeableValueStringOption(List<String> alias, String description) {
            super(alias, description);
        }

        void changeValue(String value) {
            this.setValue(value);
        }
    }

    @Test
    public void givenValue_whenGetValue_ShouldBeReturned() {
        List<String> aliases = Arrays.asList("alias1", "alias2");
        String description = "This is a fake-test description";
        String value = "dumb-value";

        StringOption so = create(aliases, description, value);

        assertEquals(aliases, so.getAliases());
        assertEquals(description, so.getDescription());
        assertEquals(Optional.of(value), so.getValue());
    }

    private StringOption create(List<String> alias, String description, String value) {
        val stringOption = new ChangeableValueStringOption(alias, description);
        stringOption.changeValue(value);
        return stringOption;
    }
}
