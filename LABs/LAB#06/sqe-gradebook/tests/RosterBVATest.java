import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import gradebook.Roster;
import gradebook.Student;

public class RosterBVATest {

    @ParameterizedTest(name = "score count={0}, expected accepted={1}")
    @CsvSource({
            "0, false",
            "1, true",
            "2, true",
            "5, true",
            "6, true",
            "7, false"
    })
    void testRosterScoreCountBoundaries(
            int scoreCount,
            boolean shouldBeAccepted) {

        Student student = new Student(
                "BVA Student",
                "BVA" + scoreCount);

        for (int i = 0; i < scoreCount; i++) {
            student.add_score(50 + i);
        }

        Roster roster = new Roster();

        if (shouldBeAccepted) {

            assertDoesNotThrow(
                    () -> roster.add_student(student));

        } else {

            assertThrows(
                    IllegalArgumentException.class,
                    () -> roster.add_student(student));
        }
    }
}