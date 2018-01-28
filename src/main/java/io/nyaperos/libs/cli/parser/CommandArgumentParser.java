package io.nyaperos.libs.cli.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static java.util.Arrays.copyOfRange;

public class CommandArgumentParser {

    public static Optional<String> getCommand(String... args) {
        String command = null;
        int firstArgumentPosition = findFirstArgumentPosition(args);

        if (firstArgumentPosition != 0)
            command = String.join(" ", copyOfRange(args, 0, firstArgumentPosition));

        return Optional.ofNullable(command);
    }

    private static int findFirstArgumentPosition(String... args) {
        for (int i=0; i < args.length; i++)
            if (args[i].startsWith("-")) return i;
        return args.length;
    }


    public static List<String> getOptions(String... args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-"))
                return asList(copyOfRange(args, i, args.length));
        }
        return new ArrayList<>();
    }

}
