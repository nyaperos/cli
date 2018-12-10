package io.nyaperos.libs.cli.options;

import io.nyaperos.libs.cli.parser.options.ParsedOption;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public abstract class Option<T> extends OptionDefinition {

    private final OptionAdapter<T> adapter;

    private String value;

    public Option(List<String> aliases, String description, @NonNull OptionAdapter<T> adapter) {
        super(aliases, description);
        this.adapter = adapter;
    }

    @Override
    protected void setValue(ParsedOption parsedOption) {
        this.value = parsedOption.getValue();
    }

    public Optional<T> value() {
        return adapter.adapt(this.value);
    }

}
