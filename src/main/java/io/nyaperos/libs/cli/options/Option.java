package io.nyaperos.libs.cli.options;

import lombok.*;

import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PROTECTED;

@EqualsAndHashCode(callSuper = true)
public abstract class Option<T> extends OptionDefinition {

    private final OptionAdapter<T> adapter;
    
    @Setter(PROTECTED)
    private String value;

    public Option(List<String> commandNames, String description, @NonNull OptionAdapter<T> adapter) {
        super(commandNames, description);
        this.adapter = adapter;
    }

    public Optional<T> value() {
        return adapter.adapt(this.value);
    }

}
