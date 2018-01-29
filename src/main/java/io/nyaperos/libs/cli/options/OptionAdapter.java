package io.nyaperos.libs.cli.options;

import java.util.Optional;

public interface OptionAdapter<T> {
    Optional<T> adapt(String value);
}
