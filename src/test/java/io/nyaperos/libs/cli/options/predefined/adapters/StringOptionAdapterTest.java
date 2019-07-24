package io.nyaperos.libs.cli.options.predefined.adapters;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringOptionAdapterTest {

    private static final StringOptionAdapter ADAPTER = new StringOptionAdapter();

    @Test
    void given_string_when_adapt_should_return_the_same() {
        String value = "fake-value";
        assertEquals(Optional.of(value), ADAPTER.adapt(value));
    }

}
