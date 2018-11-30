package io.nyaperos.libs.cli.options.predefined.adapters;


import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntegerOptionAdapterTest {

    private static final IntegerOptionAdapter ADAPTER = new IntegerOptionAdapter();

    @Test
    void given100AsString_whenAdapt_ShouldReturnOptionalWith100AsInteger() {
        assertEquals(Optional.of(100), ADAPTER.adapt("100"));
    }

}
