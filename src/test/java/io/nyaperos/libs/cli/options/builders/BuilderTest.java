package io.nyaperos.libs.cli.options.builders;

import io.nyaperos.libs.cli.options.FakeOption;
import io.nyaperos.libs.cli.options.OptionAdapter;
import io.nyaperos.libs.cli.options.predefined.adapters.StringOptionAdapter;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;

import static io.nyaperos.libs.cli.options.builders.InvalidBuildStateException.MESSAGE;
import static java.text.MessageFormat.format;
import static org.junit.Assert.assertEquals;

public class BuilderTest {

    private static final String[] ALIASES = new String[]{"alias1", "alias2"};
    private static final String DESCRIPTION = "This is a fake description";

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void givenAllRequiredFieldsProvided_ReturnFakeOption() {
        OptionAdapter<String> adapter = new StringOptionAdapter();
        List<String> aliasesList = Arrays.asList(ALIASES);
        FakeOption option = new FakeBuilder(adapter)
                .commandNames(ALIASES)
                .description(DESCRIPTION)
                .build();

        assertEquals(aliasesList, option.getCommandNames());
        assertEquals(DESCRIPTION, option.getDescription());
    }

    @Test
    public void givenEmptyAliasesDefined_ThrowException() {
        String expectedMessage = format(MESSAGE, "commandNames");
        exception.expect(InvalidBuildStateException.class);
        exception.expectMessage(expectedMessage);

        new FakeBuilder(null)
                .description(DESCRIPTION)
                .build();
    }

    @Test
    public void givenNoAliasesDefined_ThrowException() {
        String expectedMessage = format(MESSAGE, "commandNames");
        exception.expect(InvalidBuildStateException.class);
        exception.expectMessage(expectedMessage);

        new FakeBuilder(null)
                .description(DESCRIPTION)
                .build();
    }


    @Test(expected = NullPointerException.class)
    public void givenNulAliasesDefined_ThrowException() {
        new FakeBuilder(null)
                .commandNames(null)
                .description(DESCRIPTION)
                .build();
    }

    @Test
    public void givenNoDescriptionDefined_ThrowException() {
        String expectedMessage = format(MESSAGE, "description");
        exception.expect(InvalidBuildStateException.class);
        exception.expectMessage(expectedMessage);

        new FakeBuilder(null)
                .commandNames(ALIASES)
                .build();
    }

    @Test(expected = NullPointerException.class)
    public void givenNullDescriptionDefined_ThrowException() {
        new FakeBuilder(null)
                .commandNames(ALIASES)
                .description(null)
                .build();
    }

}
