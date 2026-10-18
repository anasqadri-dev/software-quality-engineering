
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import gradebook.Student;

public class RosterTest extends GradeBookTestFixtures {

    @ParameterizedTest(name = "Score count {0}: accepted = {1}")
    @CsvSource({
        "0, false",
        "3, true",
        "8, false"
    })
    void testScoreCountEquivalenceClasses(
            int scoreCount, boolean shouldBeAccepted) {

        double[] scores = new double[scoreCount];

        for (int i = 0; i < scoreCount; i++) {
            scores[i] = 50 + i;
        }

        Student student = createStudent(
            "Test Student",
            scores
        );

        if (shouldBeAccepted) {
            assertDoesNotThrow(() -> roster.add_student(student));
        } else {
            assertThrows(
                IllegalArgumentException.class,
                () -> roster.add_student(student)
            );
        }
    }
}
