package io.nyaperos.libs.cli.options;

import java.util.Optional;

public class Fake123StringOptionAdapter implements OptionAdapter<String> {

    static String append123Suffix(String value) {
        return value + "123";
    }

    @Override
    public Optional<String> adapt(String value) {
        return Optional.of(append123Suffix(value));
    }
}

