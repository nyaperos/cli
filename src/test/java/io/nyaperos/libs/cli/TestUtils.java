package io.nyaperos.libs.cli;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestUtils {


    public static <T> void assertContainsSameObject(Collection<T> collection, T object) {
        assertTrue(containsSameObject(collection, object), String.format("Object %s not contained on collection %s", object, collection));
    }

    public static <T> void assertNotContainsSameObject(Collection<T> collection, T object) {
        assertFalse(containsSameObject(collection, object), String.format("Object %s is contained on collection %s", object, collection));
    }

    private static <T> boolean containsSameObject(Collection<T> collection, T object) {
        return collection.stream().anyMatch(x -> x == object);
    }

    @SafeVarargs
    public static <T> Set<T> asSet(T... items) {
        return Stream.of(items).collect(Collectors.toSet());
    }
}
