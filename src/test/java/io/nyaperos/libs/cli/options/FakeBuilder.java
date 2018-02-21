package io.nyaperos.libs.cli.options;


import io.nyaperos.libs.cli.options.builders.Builder;

public class FakeBuilder extends Builder<FakeOption> {

    private final OptionAdapter adapter;

    public FakeBuilder(OptionAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public FakeOption build() {
        return new FakeOption(getAliases(), getDescription(), adapter);
    }
}

