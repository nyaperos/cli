package io.nyaperos.libs.cli.options.predefined;

import io.nyaperos.libs.cli.options.Option;
import io.nyaperos.libs.cli.options.predefined.adapters.IntegerOptionAdapter;

import java.util.List;

public class IntegerOption extends Option<Integer> {

    public IntegerOption(List<String> commandNames, String description) {
        super(commandNames, description, new IntegerOptionAdapter());
    }

}
