package io.nyaperos.libs.cli.options.predefined;

import io.nyaperos.libs.cli.options.Option;
import io.nyaperos.libs.cli.options.predefined.adapters.StringOptionAdapter;

import java.util.List;

public class StringOption extends Option<String> {

    public StringOption(List<String> alias, String description) {
        super(alias, description, new StringOptionAdapter());
    }

}
