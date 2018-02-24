package io.nyaperos.libs.cli.options.builders;


import io.nyaperos.libs.cli.options.FakeOption;
import io.nyaperos.libs.cli.options.OptionAdapter;
import io.nyaperos.libs.cli.options.builders.Builder;

public class FakeBuilder extends Builder<FakeOption> {

    private final OptionAdapter<String> adapter;

    FakeBuilder(OptionAdapter<String> adapter) {
        this.adapter = adapter;
    }

    @Override
    public FakeOption build() {
        return new FakeOption(getAliases(), getDescription(), adapter);
    }
}

