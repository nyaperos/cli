package io.nyaperos.libs.cli.options.builders;

import lombok.NonNull;

import java.util.Arrays;
import java.util.List;

public abstract class Builder<T> {
    private List<String> commandNames;
    private String description;

    public Builder<T> commandNames(@NonNull String... commandNames) {
        this.commandNames = Arrays.asList(commandNames);
        return this;
    }

    public Builder<T> description(@NonNull String description) {
        this.description = description;
        return this;
    }

    List<String> getCommandNames() {
        if (commandNames == null || commandNames.isEmpty()) throw new InvalidBuildStateException("commandNames");
        return commandNames;
    }

    String getDescription() {
        if (description == null) throw new InvalidBuildStateException("description");
        return description;
    }

    public abstract T build();
}
