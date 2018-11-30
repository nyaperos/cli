package io.nyaperos.libs.cli.parser.options;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
class OptionDTO {
    private final String key;
    private final String value;

    boolean valueIsEmpty() {
        return value.isEmpty();
    }
}
