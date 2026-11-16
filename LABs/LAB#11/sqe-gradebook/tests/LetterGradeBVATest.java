import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import gradebook.GradeBook;

public class LetterGradeBVATest {

    @ParameterizedTest(name = "score={0}, expected={1}")
    @CsvSource({
            "-1, INVALID",
            "0, F",
            "1, F",

            "59, F",
            "60, D",
            "61, D",

            "69, D",
            "70, C",
            "71, C",

            "79, C",
            "80, B",
            "81, B",

            "89, B",
            "90, A",
            "91, A",

            "99, A",
            "100, A",
            "101, INVALID"
    })
    void testLetterGradeBoundaries(double score, String expected) {

        if (expected.equals("INVALID")) {

            assertThrows(
                    IllegalArgumentException.class,
                    () -> GradeBook.letterGrade(score));

        } else {

            assertEquals(
                    expected.charAt(0),
                    GradeBook.letterGrade(score));
        }
    }
}