package io.nyaperos.libs.cli.options;

import io.nyaperos.libs.cli.utils.FieldUtils;

import java.util.List;

class OptionService {

    List<OptionDefinition> extract(Object instance) {
        return FieldUtils.findFields(instance, OptionDefinition.class);
    }

}
