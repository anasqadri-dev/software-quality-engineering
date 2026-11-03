
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import gradebook.GradeBook;

public class NameValidationTest extends GradeBookTestFixtures {

    @Test
    void testValidTypicalNameClass() {
        assertDoesNotThrow(
            () -> GradeBook.validateName(validStudentName())
        );
    }

    @Test
    void testEmptyNameClass() {
        assertThrows(
            IllegalArgumentException.class,
            () -> GradeBook.validateName("")
        );
    }

    @Test
    void testOverLengthNameClass() {
        assertThrows(
            IllegalArgumentException.class,
            () -> GradeBook.validateName(overlengthStudentName())
        );
    }

    @Test
    void testDigitsAndSymbolsNameClass() {
        assertThrows(
            IllegalArgumentException.class,
            () -> GradeBook.validateName("Ali123")
        );
    }
}
