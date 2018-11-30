package io.nyaperos.libs.cli.options.builder;

import io.nyaperos.libs.cli.fakes.options.FakeOption;
import io.nyaperos.libs.cli.fakes.options.builder.FakeBuilder;
import io.nyaperos.libs.cli.options.OptionAdapter;
import io.nyaperos.libs.cli.options.predefined.adapters.StringOptionAdapter;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.nyaperos.libs.cli.options.builder.InvalidBuildStateException.MESSAGE;
import static java.text.MessageFormat.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BuilderTest {

    private static final String[] ALIASES = new String[]{"alias1", "alias2"};
    private static final String DESCRIPTION = "This is a fake description";


    @Test
    void givenAllRequiredFieldsProvided_ReturnFakeOption() {
        OptionAdapter<String> adapter = new StringOptionAdapter();
        List<String> aliasesList = Arrays.asList(ALIASES);
        FakeOption option = new FakeBuilder(adapter)
                .aliases(ALIASES)
                .description(DESCRIPTION)
                .build();

        assertEquals(aliasesList, option.getAliases());
        assertEquals(DESCRIPTION, option.getDescription());
    }

    @Test
    void givenEmptyAliasesDefined_ThrowException() {
        assertThrows(
                InvalidBuildStateException.class,
                () -> new FakeBuilder(null)
                        .aliases()
                        .description(DESCRIPTION)
                        .build(),
                format(MESSAGE, "aliases")
        );
    }

    @Test
    void givenNoAliasesDefined_ThrowException() {
        assertThrows(
                InvalidBuildStateException.class,
                () -> new FakeBuilder(null)
                        .description(DESCRIPTION)
                        .build(),
                format(MESSAGE, "aliases")
        );
    }


    @Test
    void givenNullAliasesDefined_ThrowException() {
        assertThrows(
                NullPointerException.class,
                () -> new FakeBuilder(null)
                        .aliases((String[]) null)
                        .description(DESCRIPTION)
                        .build()
        );
    }

    @Test
    void givenNoDescriptionDefined_ThrowException() {
        assertThrows(
                InvalidBuildStateException.class,
                () -> new FakeBuilder(null)
                        .aliases(ALIASES)
                        .build(),
                format(MESSAGE, "description")
        );
    }

    @Test
    void givenNullDescriptionDefined_ThrowException() {
        assertThrows(
                NullPointerException.class,
                () -> new FakeBuilder(null)
                        .aliases(ALIASES)
                        .description(null)
                        .build()
        );
    }

}
