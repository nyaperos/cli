package io.nyaperos.libs.cli.options;

import java.util.Optional;

public abstract class OptionAdapter<T> {
    public abstract Optional<T> adapt(String value);
}
