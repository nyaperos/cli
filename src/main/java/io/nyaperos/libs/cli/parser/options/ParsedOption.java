package io.nyaperos.libs.cli.parser.options;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class ParsedOption {
    private final String key;
    private final String value;

    boolean valueIsEmpty() {
        return value.isEmpty();
    }
}
