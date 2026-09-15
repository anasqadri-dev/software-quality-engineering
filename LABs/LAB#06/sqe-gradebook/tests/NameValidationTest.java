import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import gradebook.GradeBook;

public class NameValidationTest {

    // Valid equivalence class

    @Test
    void testValidTypicalNameClass() {
        assertDoesNotThrow(
            () -> GradeBook.validateName("Ali Khan")
        );
    }

    // Invalid equivalence classes

    @Test
    void testEmptyNameClass() {
        assertThrows(
            IllegalArgumentException.class,
            () -> GradeBook.validateName("")
        );
    }

    @Test
    void testOverLengthNameClass() {

        String longName =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXY";

        assertThrows(
            IllegalArgumentException.class,
            () -> GradeBook.validateName(longName)
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