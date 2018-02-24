package io.nyaperos.libs.cli.options;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public abstract class Option<T> {

    @NonNull
    private final List<String> aliases;
    @NonNull
    private final String description;
    @NonNull
    private final OptionAdapter<T> adapter;
    @Getter(NONE)
    @Setter(PROTECTED)
    private String value;

    public Optional<T> value() {
        return adapter.adapt(this.value);
    }

}
