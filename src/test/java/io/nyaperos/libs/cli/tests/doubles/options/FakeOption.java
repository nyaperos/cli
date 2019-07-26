package io.nyaperos.libs.cli.tests.doubles.options;

import io.nyaperos.libs.cli.options.Option;
import io.nyaperos.libs.cli.options.OptionAdapter;
import io.nyaperos.libs.cli.options.predefined.adapters.StringOptionAdapter;

import java.util.List;

public class FakeOption extends Option<String> {

    public FakeOption(List<String> aliases, String description) {
        super(aliases, description, new StringOptionAdapter());
    }

    public FakeOption(List<String> aliases, String description, OptionAdapter<String> adapter) {
        super(aliases, description, adapter);
    }
}
