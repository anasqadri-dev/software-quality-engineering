import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import gradebook.GradeBook;

public class LetterGradeTest {

    // Valid equivalence classes

    @Test
    void testFGradeClass() {
        assertEquals('F', GradeBook.letterGrade(45));
    }

    @Test
    void testDGradeClass() {
        assertEquals('D', GradeBook.letterGrade(65));
    }

    @Test
    void testCGradeClass() {
        assertEquals('C', GradeBook.letterGrade(75));
    }

    @Test
    void testBGradeClass() {
        assertEquals('B', GradeBook.letterGrade(85));
    }

    @Test
    void testAGradeClass() {
        assertEquals('A', GradeBook.letterGrade(95));
    }

    // Invalid equivalence classes

    @Test
    void testInvalidLowScoreClass() {
        assertThrows(
                IllegalArgumentException.class,
                () -> GradeBook.letterGrade(-10));
    }

    @Test
    void testInvalidHighScoreClass() {
        assertThrows(
                IllegalArgumentException.class,
                () -> GradeBook.letterGrade(150));
    }
}