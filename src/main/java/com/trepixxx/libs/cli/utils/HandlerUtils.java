package com.trepixxx.libs.cli.utils;

import java.util.Arrays;

public class HandlerUtils {
    
    public static final String ERROR_MESSAGE = "ERROR";
    public static final String INVALID_ARGUMENTS = "INVALID_ARGUMENTS";

    public static String getCommand(String... args) {
        String command = null;
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-")) {
                command = String.join(" ", Arrays.copyOfRange(args, 0, i));
                break;
            }
        }
        command = (command == null && args.length > 0) ? String.join(" ", Arrays.copyOfRange(args, 0, args.length)) : command; 
        return command;
    }
    
    public static String[] getCommandArgs(String... args) {
        String[] commandArgs = new String[0];
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-")) {
                commandArgs = Arrays.copyOfRange(args, i, args.length);
                break;
            }
        }
        return commandArgs;
    }
    
    @SafeVarargs
    public static <T> T[] asArray(T... elements) {
        T[] e = elements;
        return e;
    }
}
