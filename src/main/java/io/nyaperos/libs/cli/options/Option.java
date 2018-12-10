package io.nyaperos.libs.cli.options;

import io.nyaperos.libs.cli.parser.options.ParsedOption;
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

    private String value;


    protected boolean hasSameAlias(ParsedOption parsedOption) {
        return aliases.contains(parsedOption.getKey());
    }

    protected void setValue(ParsedOption parsedOption) {
        this.value = parsedOption.getValue();
    }

    public Optional<T> value() {
        return adapter.adapt(this.value);
    }



}
