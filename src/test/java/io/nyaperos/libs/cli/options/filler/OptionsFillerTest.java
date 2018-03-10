package io.nyaperos.libs.cli.options.filler;

import io.nyaperos.libs.cli.options.Option;
import io.nyaperos.libs.cli.utils.AnnotationsUtils;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static io.nyaperos.libs.cli.options.filler.IllegalCommandDefinitionException.MESSAGE;
import static java.text.MessageFormat.format;

public class OptionsFillerTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private static OptionsFiller optionsFiller;

    @BeforeClass
    public static void setUpClass() {
        optionsFiller = new OptionsFiller();
    }


    @Test
    public void givenClassWithoutPublicConstructor_ShouldThrowException() {
        String expectedMessage = format(MESSAGE, AnnotationsUtils.class.getCanonicalName());
        exception.expect(IllegalCommandDefinitionException.class);
        exception.expectMessage(expectedMessage);

        optionsFiller.fill(AnnotationsUtils.class);
    }

    @Test
    public void givenPrimitiveTypeWithoutPublicConstructor_ShouldThrowException() {
        String expectedMessage = format(MESSAGE, Option.class.getCanonicalName());
        exception.expect(IllegalCommandDefinitionException.class);
        exception.expectMessage(expectedMessage);

        optionsFiller.fill(Option.class);
    }

}
