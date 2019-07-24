package io.nyaperos.libs.cli.tests.doubles.options.builder;


import io.nyaperos.libs.cli.tests.doubles.options.FakeOption;
import io.nyaperos.libs.cli.options.OptionAdapter;
import io.nyaperos.libs.cli.options.builder.OptionBuilder;

public class FakeBuilder extends OptionBuilder<FakeOption> {

    private final OptionAdapter<String> adapter;

    public FakeBuilder(OptionAdapter<String> adapter) {
        this.adapter = adapter;
    }

    @Override
    public FakeOption build() {
        return new FakeOption(getAliases(), getDescription(), adapter);
    }
}

