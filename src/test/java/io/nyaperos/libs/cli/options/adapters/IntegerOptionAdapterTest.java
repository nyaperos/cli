package io.nyaperos.libs.cli.options.adapters;


import io.nyaperos.libs.cli.options.adapters.IntegerOptionAdapter;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class IntegerOptionAdapterTest {

    private static final IntegerOptionAdapter ADAPTER = new IntegerOptionAdapter();

    @Test
    public void given100AsString_whenAdapt_ShouldReturnOptionalWith100AsInteger() {
        assertEquals(Optional.of(100), ADAPTER.adapt("100"));
    }

}
