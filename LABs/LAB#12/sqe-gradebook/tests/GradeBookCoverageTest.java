import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import gradebook.GradeBook;
import gradebook.Student;

class GradeBookCoverageTest {

    @Test
    void validatesNullAndEmptyNames() {
        assertThrows(IllegalArgumentException.class, () -> GradeBook.validateName(null));
        assertThrows(IllegalArgumentException.class, () -> GradeBook.validateName(""));
    }

    @Test
    void validatesMaximumLengthAndAllowedCharacters() {
        assertDoesNotThrow(() -> GradeBook.validateName("A".repeat(50)));
        assertThrows(IllegalArgumentException.class, () -> GradeBook.validateName("A".repeat(51)));
        assertThrows(IllegalArgumentException.class, () -> GradeBook.validateName("Anas_1"));
        assertThrows(IllegalArgumentException.class, () -> GradeBook.validateName("Anas@"));
    }

    @Test
    void studentNameValidationCoversNullAndValidNameBranches() {
        assertThrows(IllegalArgumentException.class, () -> Student.validateName(null));
        assertThrows(IllegalArgumentException.class, () -> Student.validateName(""));
        assertDoesNotThrow(() -> Student.validateName("A"));
        assertDoesNotThrow(() -> Student.validateName("A".repeat(50)));
        assertThrows(IllegalArgumentException.class, () -> Student.validateName("A".repeat(51)));
    }
}
