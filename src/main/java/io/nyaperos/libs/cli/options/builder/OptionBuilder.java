package io.nyaperos.libs.cli.options.builder;

import lombok.NonNull;

import java.util.Arrays;
import java.util.List;

public abstract class OptionBuilder<T> {
    private List<String> aliases;
    private String description;

    public OptionBuilder<T> aliases(@NonNull String... aliases) {
        this.aliases = Arrays.asList(aliases);
        return this;
    }

    public OptionBuilder<T> description(@NonNull String description) {
        this.description = description;
        return this;
    }

    protected List<String> getAliases() {
        if (aliases == null || aliases.isEmpty()) throw new InvalidBuildStateException("aliases");
        return aliases;
    }

    protected String getDescription() {
        if (description == null) throw new InvalidBuildStateException("description");
        return description;
    }

    public abstract T build();
}
