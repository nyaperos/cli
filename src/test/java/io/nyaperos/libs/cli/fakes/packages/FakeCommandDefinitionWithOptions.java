package io.nyaperos.libs.cli.fakes.packages;


import io.nyaperos.libs.cli.command.CommandDefinition;
import io.nyaperos.libs.cli.fakes.options.FakeOption;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

@CommandDefinition(aliases = {"fake-command"})
public class FakeCommandDefinitionWithOptions {

    public FakeOption name = new FakeOption(singletonList("name"), "fake name description");
    public FakeOption history = new FakeOption(asList("h", "history"), "fake history description");
    public String potato = "this is a potato";
}
