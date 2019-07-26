package io.nyaperos.libs.cli.utils;

import io.nyaperos.libs.cli.command.CommandDefinition;
import io.nyaperos.libs.cli.tests.packages.duplicated.DuplicatedCommandDefinition;
import io.nyaperos.libs.cli.tests.packages.duplicated.DuplicatedCommandDefinition2;
import io.nyaperos.libs.cli.tests.packages.duplicated.subpackage.DuplicatedCommandDefinition3;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIn.in;
import static org.hamcrest.core.Is.is;

class AnnotationsUtilsTest {

    private static final Class<?> DUPLICATED = DuplicatedCommandDefinition.class;
    private static final Class<?> DUPLICATED2 = DuplicatedCommandDefinition2.class;
    private static final Class<?> DUPLICATED3 = DuplicatedCommandDefinition3.class;

    @Test
    void return_all_command_definitions_including_which_are_in_subpackages() {
        Set<Class<?>> foundClasses = AnnotationsUtils.find(DUPLICATED.getPackage(), CommandDefinition.class);

        assertThat(DUPLICATED, is(in(foundClasses)));
        assertThat(DUPLICATED2, is(in(foundClasses)));
        assertThat(DUPLICATED3, is(in(foundClasses)));

    }

}
