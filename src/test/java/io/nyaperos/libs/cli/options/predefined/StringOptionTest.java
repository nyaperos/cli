package io.nyaperos.libs.cli.options.predefined;

import io.nyaperos.libs.cli.options.Option;
import lombok.val;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class StringOptionTest {

    private static final class ChangeableValueStringOption extends StringOption {

        ChangeableValueStringOption(List<String> alias, String description, List<Option<?>> requiredIfPresent) {
            super(alias, description, requiredIfPresent);
        }

        void changeValue(String value) {
            this.setValue(value);
        }
    }

    @Test
    public void givenValue_whenGetValue_ShouldBeReturned() {
        String value = "dumb-value";
        StringOption so = create(value);
        assertEquals(Optional.of(value), so.getValue());
    }

    private StringOption create(String value) {
        val stringOption = new ChangeableValueStringOption(Collections.emptyList(), "Empty description", Collections.emptyList());
        stringOption.changeValue(value);
        return stringOption;
    }
}
