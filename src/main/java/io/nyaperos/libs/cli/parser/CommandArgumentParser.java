package io.nyaperos.libs.cli.parser;

import io.nyaperos.libs.cli.commons.InvalidClassInstantiationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static java.util.Arrays.copyOfRange;

final class CommandArgumentParser {

    private CommandArgumentParser() {
        throw new InvalidClassInstantiationException(CommandArgumentParser.class);
    }

    static Optional<String> getCommand(String... args) {
        int firstArgumentPosition = findFirstArgumentPosition(args);

        if (firstArgumentPosition == 0) return Optional.empty();
        return Optional.of(String.join(" ", copyOfRange(args, 0, firstArgumentPosition)));
    }

    private static int findFirstArgumentPosition(String... args) {
        for (int i = 0; i < args.length; i++)
            if (args[i].startsWith("-")) return i;
        return args.length;
    }


    static List<String> getOptions(String... args) {
        int firstArgumentPosition = findFirstArgumentPosition(args);

        if (firstArgumentPosition == args.length) return new ArrayList<>();
        else return asList(copyOfRange(args, firstArgumentPosition, args.length));
    }

}
