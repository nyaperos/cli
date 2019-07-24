package io.nyaperos.libs.cli.options.predefined.adapters;


import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntegerOptionAdapterTest {

    private static final IntegerOptionAdapter ADAPTER = new IntegerOptionAdapter();

    @Test
    void given_a_string_when_adapt_should_return_it_as_integer() {
        assertEquals(Optional.of(100), ADAPTER.adapt("100"));
    }

}
