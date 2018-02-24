package io.nyaperos.libs.cli.options.predefined.adapters;

import io.nyaperos.libs.cli.options.OptionAdapter;

import java.util.Optional;

public class IntegerOptionAdapter implements OptionAdapter<Integer> {

    @Override
    public Optional<Integer> adapt(String value) {
        return Optional.ofNullable(value).map(Integer::valueOf);
    }
}
