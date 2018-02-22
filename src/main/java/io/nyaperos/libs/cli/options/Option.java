package io.nyaperos.libs.cli.options;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

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

}
