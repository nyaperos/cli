package io.nyaperos.libs.cli.options.builder;

import io.nyaperos.libs.cli.options.OptionAdapter;
import io.nyaperos.libs.cli.options.predefined.adapters.StringOptionAdapter;
import io.nyaperos.libs.cli.tests.doubles.options.FakeOption;
import io.nyaperos.libs.cli.tests.doubles.options.builder.FakeBuilder;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.nyaperos.libs.cli.options.builder.InvalidBuildStateException.MESSAGE;
import static java.text.MessageFormat.format;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BuilderTest {

    private static final String[] ALIASES = new String[]{"alias1", "alias2"};
    private static final String DESCRIPTION = "This is a fake description";


    @Test
    void when_all_required_fields_are_provided_should_build_option() {
        OptionAdapter<String> adapter = new StringOptionAdapter();

        FakeOption option = new FakeBuilder(adapter)
                .aliases(ALIASES)
                .description(DESCRIPTION)
                .build();

        assertThat(option.getAliases(), is(asList(ALIASES)));
        assertThat(option.getDescription(), is(DESCRIPTION));
    }

    @Test
    void when_aliases_are_empty_throw_exception() {
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
    void when_aliases_are_not_defined_throw_exception() {
        assertThrows(
                InvalidBuildStateException.class,
                () -> new FakeBuilder(null)
                        .description(DESCRIPTION)
                        .build(),
                format(MESSAGE, "aliases")
        );
    }


    @Test
    void when_aliases_are_defined_as_null_throw_exception() {
        assertThrows(
                NullPointerException.class,
                () -> new FakeBuilder(null)
                        .aliases((String[]) null)
                        .description(DESCRIPTION)
                        .build()
        );
    }

    @Test
    void when_description_is_not_defined_throw_exception() {
        assertThrows(
                InvalidBuildStateException.class,
                () -> new FakeBuilder(null)
                        .aliases(ALIASES)
                        .build(),
                format(MESSAGE, "description")
        );
    }

    @Test
    void when_description_are_defined_as_null_throw_exception() {
        assertThrows(
                NullPointerException.class,
                () -> new FakeBuilder(null)
                        .aliases(ALIASES)
                        .description(null)
                        .build()
        );
    }

    @Test
    void when_no_option_is_defined_throw_first_aliases_error() {
        assertThrows(
                InvalidBuildStateException.class,
                () -> new FakeBuilder(null).build(),
                format(MESSAGE, "aliases")
        );
    }

}
