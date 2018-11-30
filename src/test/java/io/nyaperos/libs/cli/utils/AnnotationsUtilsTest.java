package io.nyaperos.libs.cli.utils;

import io.nyaperos.libs.cli.command.CommandDefinition;
import io.nyaperos.libs.cli.fakes.packages.duplicated.DuplicatedCommandDefinition;
import io.nyaperos.libs.cli.fakes.packages.duplicated.DuplicatedCommandDefinition2;
import io.nyaperos.libs.cli.fakes.packages.duplicated.subpackage.DuplicatedCommandDefinition3;
import org.junit.Test;

import java.util.Set;

import static io.nyaperos.libs.cli.TestUtils.asSet;
import static org.junit.Assert.assertEquals;

public class AnnotationsUtilsTest {

    private static final Class<?> DUPLICATED = DuplicatedCommandDefinition.class;
    private static final Class<?> DUPLICATED2 = DuplicatedCommandDefinition2.class;
    private static final Class<?> DUPLICATED3 = DuplicatedCommandDefinition3.class;

    @Test
    public void givenSubPackage_ThenOnlyReturnFakeCommandDefinition() {
        Set<Class<?>> expectedClasses = asSet(DUPLICATED3);
        Set<Class<?>> foundClasses = AnnotationsUtils.find(DUPLICATED3.getPackage(), CommandDefinition.class);
        assertEquals(expectedClasses, foundClasses);
    }

    @Test
    public void givenFakesPackage_ThenReturnAllFakeCommandDefinitions() {
        Set<Class<?>> expectedClasses = asSet(DUPLICATED, DUPLICATED2, DUPLICATED3);
        Set<Class<?>> foundClasses = AnnotationsUtils.find(DUPLICATED.getPackage(), CommandDefinition.class);
        assertEquals(expectedClasses, foundClasses);
    }
}
