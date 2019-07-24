package io.nyaperos.libs.cli.utils;

import io.nyaperos.libs.cli.command.CommandDefinition;
import io.nyaperos.libs.cli.tests.packages.duplicated.DuplicatedCommandDefinition;
import io.nyaperos.libs.cli.tests.packages.duplicated.DuplicatedCommandDefinition2;
import io.nyaperos.libs.cli.tests.packages.duplicated.subpackage.DuplicatedCommandDefinition3;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static io.nyaperos.libs.cli.TestUtils.asSet;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AnnotationsUtilsTest {

    private static final Class<?> DUPLICATED = DuplicatedCommandDefinition.class;
    private static final Class<?> DUPLICATED2 = DuplicatedCommandDefinition2.class;
    private static final Class<?> DUPLICATED3 = DuplicatedCommandDefinition3.class;

    @Test
    void return_all_command_definitions_including_which_are_in_subpackages() {
        Set<Class<?>> expectedClasses = asSet(DUPLICATED, DUPLICATED2, DUPLICATED3);
        Set<Class<?>> foundClasses = AnnotationsUtils.find(DUPLICATED.getPackage(), CommandDefinition.class);
        assertEquals(expectedClasses, foundClasses);
    }
}
