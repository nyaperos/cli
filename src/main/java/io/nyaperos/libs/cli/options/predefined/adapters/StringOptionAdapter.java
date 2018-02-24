package io.nyaperos.libs.cli.options.predefined.adapters;

import io.nyaperos.libs.cli.options.OptionAdapter;

import java.util.Optional;

public class StringOptionAdapter implements OptionAdapter<String> {

    @Override
    public Optional<String> adapt(String value) {
        return Optional.ofNullable(value);
    }
}
