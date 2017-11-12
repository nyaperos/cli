package io.nyaperos.libs.cli.utils;

import java.io.File;
import java.io.FileNotFoundException;

import lombok.SneakyThrows;

public class OptionUtils {
    
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public static Boolean bool(String argument) {
        return argument != null;
    }

    public static String notNullString(String argument) {
        return argument == null ? "" : argument;
    }

    public static Integer integer(String argument) {
        return Integer.parseInt(argument);
    }

    @SneakyThrows
    public static File file(String argument) {
        return file(argument, false);
    }

    @SneakyThrows
    public static File file(String argument, Boolean forceFileExists) {
        File f = ResourceUtils.getFile(argument);
        if (forceFileExists && !f.exists()) throw new FileNotFoundException();
        return f;
    }

//    public static DateTime date(String argument) {
//        return date(argument, DATE_FORMAT);
//    }
//
//    public static DateTime date(String argument, String pattern) {
//        return date(argument, pattern, true);
//    }
//
//    public static DateTime date(String argument, String pattern, Boolean nullable) {
//        return argument == null ? (nullable == true ? null : new DateTime(0))
//                : DateTime.parse(argument, DateTimeFormat.forPattern(pattern));
//    }

}
