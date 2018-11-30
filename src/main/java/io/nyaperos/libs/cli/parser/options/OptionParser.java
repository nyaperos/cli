package io.nyaperos.libs.cli.parser.options;

import lombok.val;

import java.util.LinkedList;
import java.util.List;

class OptionParser {

    private OptionParser() {
    }

    static List<OptionDTO> parse(List<String> args) {
        val result = new LinkedList<OptionDTO>();
        val linkedArguments = new LinkedList<String>(args);
        
        while (!linkedArguments.isEmpty()) {
            String key = linkedArguments.poll();
            List<String> value = new LinkedList<>();
            while (!linkedArguments.isEmpty() && !isAnOption(linkedArguments.peek())) {
                value.add(linkedArguments.poll());
            }
            result.add(new OptionDTO(key, String.join(" ", value)));
        }
        return result;
    }

    private static boolean isAnOption(String arg) {
        return arg != null && (arg.startsWith("-") || arg.startsWith("--"));
    }
}
