package io.nyaperos.libs.cli.options.builders;


import io.nyaperos.libs.cli.options.FakeOption;
import io.nyaperos.libs.cli.options.OptionAdapter;

public class FakeBuilder extends Builder<FakeOption> {

    private final OptionAdapter<String> adapter;

    FakeBuilder(OptionAdapter<String> adapter) {
        this.adapter = adapter;
    }

    @Override
    public FakeOption build() {
        return new FakeOption(getCommandNames(), getDescription(), adapter);
    }
}

