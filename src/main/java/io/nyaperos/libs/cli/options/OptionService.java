package io.nyaperos.libs.cli.options;

import io.nyaperos.libs.cli.commons.InvalidClassInstantiationException;
import io.nyaperos.libs.cli.parser.options.ParsedOption;
import io.nyaperos.libs.cli.utils.FieldUtils;
import lombok.val;

import java.util.List;

class OptionService {

    private OptionService() {
        throw new InvalidClassInstantiationException(OptionService.class);
    }

    static List<Option> extract(Object instance) {
        return FieldUtils.findFields(instance, Option.class);
    }

    static void fill(Object instance, List<ParsedOption> parsedOptions) {
        val options = extract(instance);
        options.forEach(option -> {
            val optionParsed = parsedOptions.stream().filter(option::hasSameAlias).findAny();
            optionParsed.ifPresent(option::setValue);
        });
    }

}
