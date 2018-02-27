package io.nyaperos.libs.cli.options;

import java.util.List;

public class FakeOption extends Option<String> {
    public FakeOption(List<String> commandNames, String description, OptionAdapter<String> adapter) {
        super(commandNames, description, adapter);
    }
}
