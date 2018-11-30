package io.nyaperos.libs.cli;

import com.sun.javafx.binding.StringFormatter;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestUtils {


    public static <T> void assertContainsSameObject(Collection<T> collection, T object) {
        assertTrue(StringFormatter.format("Object %s not contained on collection %s", object, collection).getValue(), containsSameObject(collection, object));
    }

    public static <T> void assertNotContainsSameObject(Collection<T> collection, T object) {
        assertFalse(StringFormatter.format("Object %s is contained on collection %s", object, collection).getValue(), containsSameObject(collection, object));
    }

    private static <T> boolean containsSameObject(Collection<T> collection, T object) {
        return collection.stream().anyMatch(x -> x == object);
    }

    @SafeVarargs
    public static <T> Set<T> asSet(T... items) {
        return Stream.of(items).collect(Collectors.toSet());
    }
}
