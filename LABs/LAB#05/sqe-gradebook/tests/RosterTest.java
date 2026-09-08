import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import gradebook.Roster;
import gradebook.Student;

public class RosterTest {

    @ParameterizedTest
    @CsvSource({
        "0, false",
        "3, true",
        "8, false"
    })
    void testScoreCountEquivalenceClasses(int scoreCount, boolean shouldBeAccepted) {

        Student student = new Student(
            "Test Student",
            "ROSTER" + scoreCount
        );

        for (int i = 0; i < scoreCount; i++) {
            student.add_score(50 + i);
        }

        Roster roster = new Roster();

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