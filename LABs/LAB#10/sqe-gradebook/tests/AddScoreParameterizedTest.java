
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import gradebook.Student;

public class AddScoreParameterizedTest {

    @ParameterizedTest(name = "{0}: score {1}, accepted = {2}")
    @CsvSource({
        "negative_score, -0.01, false",
        "zero_boundary, 0.0, true",
        "normal_score, 50.0, true",
        "upper_valid_boundary, 99.99, true",
        "exactly_100, 100.0, false",
        "above_100, 101.0, false",
        "large_negative_score, -50.0, false",
        "another_valid_score, 75.5, true"
    })
    void addScoreHandlesEdgeCases(
            String caseId,
            double score,
            boolean shouldBeAccepted) {

        // A unique ID prevents conflicts with other tests.
        Student student = new Student(
                "Test Student",
                "SCORE-" + UUID.randomUUID());

        if (shouldBeAccepted) {
            assertDoesNotThrow(
                    () -> student.add_score(score),
                    caseId + " should accept the score");

            assertEquals(
                    1,
                    student.getScoreCount(),
                    "An accepted score should be stored");
        } else {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> student.add_score(score),
                    caseId + " should reject the score");

            assertEquals(
                    0,
                    student.getScoreCount(),
                    "A rejected score should not be stored");
        }
    }
}
