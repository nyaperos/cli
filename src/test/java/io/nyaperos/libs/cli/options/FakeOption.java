package io.nyaperos.libs.cli.options;

import java.util.List;

public class FakeOption extends Option<String> {
    public FakeOption(List<String> alias, String description, OptionAdapter<String> adapter) {
        super(alias, description, adapter);
    }
}
