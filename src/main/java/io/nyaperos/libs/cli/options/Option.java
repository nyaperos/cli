package io.nyaperos.libs.cli.options;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Getter
@RequiredArgsConstructor
public abstract class Option<T> {
    @NonNull
    private final List<String> aliases;
    @NonNull
    private final String description;
    @NonNull
    private final OptionAdapter<T> adapter;

    private Optional<T> value;

    protected void setValue(String value) {
        this.value = adapter.adapt(value);
    }
}
