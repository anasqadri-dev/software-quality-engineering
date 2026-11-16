import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import gradebook.Student;

public class NameLengthBVATest {

    private String createName(int length) {
        return "A".repeat(length);
    }

    @Test
    void testNameLengthZero() {

        String name = createName(0);

        assertThrows(
                IllegalArgumentException.class,
                () -> Student.validateName(name));
    }

    @Test
    void testNameLengthOne() {

        String name = createName(1);

        assertDoesNotThrow(
                () -> Student.validateName(name));
    }

    @Test
    void testNameLength49() {

        String name = createName(49);

        assertDoesNotThrow(
                () -> Student.validateName(name));
    }

    @Test
    void testNameLength50() {

        String name = createName(50);

        assertDoesNotThrow(
                () -> Student.validateName(name));
    }

    @Test
    void testNameLength51() {

        String name = createName(51);

        assertThrows(
                IllegalArgumentException.class,
                () -> Student.validateName(name));
    }
}