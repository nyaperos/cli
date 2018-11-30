package io.nyaperos.libs.cli.options;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public abstract class OptionDefinition {

    @NonNull
    private final List<String> aliases;
    @NonNull
    private final String description;

    protected abstract void setValue(String value);

}
