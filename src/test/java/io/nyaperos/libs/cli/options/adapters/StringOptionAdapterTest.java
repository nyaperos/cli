package io.nyaperos.libs.cli.options.adapters;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class StringOptionAdapterTest {

    private static final StringOptionAdapter ADAPTER = new StringOptionAdapter();

    @Test
    public void givenString_whenAdapt_ShouldReturnOptionalWithSameString() {
        String value = "fake-value";
        assertEquals(Optional.of(value), ADAPTER.adapt(value));
    }

}
