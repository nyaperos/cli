package io.nyaperos.libs.cli.fakes.options;

import io.nyaperos.libs.cli.options.OptionAdapter;

import java.util.Optional;

public class Fake123StringOptionAdapter implements OptionAdapter<String> {

    public static String append123Suffix(String value) {
        return value + "123";
    }

    @Override
    public Optional<String> adapt(String value) {
        return Optional.of(append123Suffix(value));
    }
}

