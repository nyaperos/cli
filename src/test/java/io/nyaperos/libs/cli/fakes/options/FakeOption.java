package io.nyaperos.libs.cli.fakes.options;

import io.nyaperos.libs.cli.options.Option;
import io.nyaperos.libs.cli.options.OptionAdapter;

import java.util.List;

public class FakeOption extends Option<String> {

    public FakeOption(List<String> aliases, String description) {
        super(aliases, description, new Fake123StringOptionAdapter());
    }

    public FakeOption(List<String> aliases, String description, OptionAdapter<String> adapter) {
        super(aliases, description, adapter);
    }
}
