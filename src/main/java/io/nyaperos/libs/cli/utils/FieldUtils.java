package io.nyaperos.libs.cli.utils;

import io.nyaperos.libs.cli.commons.InvalidClassInstantiationException;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FieldUtils {

    private FieldUtils() {
        throw new InvalidClassInstantiationException(FieldUtils.class);
    }

    public static <F, T> List<F> findFields(T instance, Class<F> clazz) {
        return Stream.of(instance.getClass().getFields())
                .filter(field -> clazz.isAssignableFrom(field.getType()))
                .map(field -> FieldUtils.<F,T>getFieldValue(field, instance))
                .collect(Collectors.toList());
    }

    @SneakyThrows
    @SuppressWarnings("unchecked")
    private static <F, T> F getFieldValue(Field field, T instance) {
        return (F) field.get(instance);
    }
}
