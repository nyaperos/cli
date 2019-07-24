package io.nyaperos.libs.cli.options.predefined.adapters;


import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class IntegerOptionAdapterTest {

    private static final IntegerOptionAdapter ADAPTER = new IntegerOptionAdapter();

    @Test
    void given_a_string_when_adapt_should_return_it_as_integer() {
        assertThat(ADAPTER.adapt("100"), is(Optional.of(100)));
    }

}
