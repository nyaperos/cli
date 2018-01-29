package io.nyaperos.libs.cli.options.predefined;

import io.nyaperos.libs.cli.options.Option;
import io.nyaperos.libs.cli.options.OptionAdapter;

import java.util.List;
import java.util.Optional;

public class StringOption extends Option<String> {

    private static final class StringOptionAdapter extends OptionAdapter<String> {

        @Override
        public Optional<String> adapt(String value) {
            return Optional.ofNullable(value);
        }
    }

    public StringOption(List<String> alias, String description, List<Option<?>> requiredIfPresent) {
        super(alias, description, requiredIfPresent, new StringOptionAdapter());
    }

}
