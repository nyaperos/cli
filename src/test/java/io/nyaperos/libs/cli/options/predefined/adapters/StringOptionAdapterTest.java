package io.nyaperos.libs.cli.options.predefined.adapters;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringOptionAdapterTest {

    private static final StringOptionAdapter ADAPTER = new StringOptionAdapter();

    @Test
    void givenString_whenAdapt_ShouldReturnOptionalWithSameString() {
        String value = "fake-value";
        assertEquals(Optional.of(value), ADAPTER.adapt(value));
    }

}
