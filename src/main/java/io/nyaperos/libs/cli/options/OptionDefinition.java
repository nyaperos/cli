package io.nyaperos.libs.cli.options;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public abstract class OptionDefinition {

    @NonNull
    private final List<String> commandNames;
    @NonNull
    private final String description;

    protected abstract void setValue(String value);

}
