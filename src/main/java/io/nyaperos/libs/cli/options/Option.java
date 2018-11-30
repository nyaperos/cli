package io.nyaperos.libs.cli.options;

import lombok.NonNull;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PROTECTED;

public abstract class Option<T> extends OptionDefinition {

    private final OptionAdapter<T> adapter;

    @Setter(PROTECTED)
    private String value;

    public Option(List<String> aliases, String description, @NonNull OptionAdapter<T> adapter) {
        super(aliases, description);
        this.adapter = adapter;
    }

    public Optional<T> value() {
        return adapter.adapt(this.value);
    }

}
