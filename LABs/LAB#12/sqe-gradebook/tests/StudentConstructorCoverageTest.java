import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import gradebook.Student;

class StudentConstructorCoverageTest {

    @Test
    void rejectsDuplicateStudentIds() {
        String id = "LAB11-DUPLICATE-001";
        Student first = new Student("Ali", id);
        assertEquals("Ali", first.getStudentName());

        IllegalArgumentException error = assertThrows(
            IllegalArgumentException.class,
            () -> new Student("Sara", id)
        );
        assertEquals("Student ID already exists", error.getMessage());
    }
}
