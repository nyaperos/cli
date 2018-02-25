package io.nyaperos.libs.cli.utils;

import io.nyaperos.libs.cli.utils.fakes.annotations.FakeAnnotation;
import io.nyaperos.libs.cli.utils.fakes.annotations.FakeClass;
import io.nyaperos.libs.cli.utils.fakes.annotations.FakeClass2;
import io.nyaperos.libs.cli.utils.fakes.annotations.subpackage.FakeClass3;
import org.junit.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class AnnotationsUtilsTest {

    private static final Class<?> FAKE = FakeClass.class;
    private static final Class<?> FAKE2 = FakeClass2.class;
    private static final Class<?> FAKE3 = FakeClass3.class;

    @Test
    public void givenSubPackage_ThenOnlyReturnFake3Class() {
        Set<Class<?>> expectedClasses = asSet(FAKE3);
        Set<Class<?>> foundClasses = AnnotationsUtils.find(FAKE3.getPackage(), FakeAnnotation.class);
        assertEquals(expectedClasses, foundClasses);
    }

    @Test
    public void givenFakesPackage_ThenReturnAllFakeClasses() {
        Set<Class<?>> expectedClasses = asSet(FAKE, FAKE2, FAKE3);
        Set<Class<?>> foundClasses = AnnotationsUtils.find(FAKE.getPackage(), FakeAnnotation.class);
        assertEquals(expectedClasses, foundClasses);
    }

    @SafeVarargs
    private final <T> Set<T> asSet(T... items) {
        return Stream.of(items).collect(Collectors.toSet());
    }
}
