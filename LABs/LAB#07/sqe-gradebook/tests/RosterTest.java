
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import gradebook.Roster;
import gradebook.Student;

public class RosterTest {

    private Roster roster;

    @BeforeEach
    void setUp() {
        // Function-scoped fixture equivalent:
        // create a fresh roster before every test.
        roster = new Roster();
    }

    @ParameterizedTest(name = "Score count {0}: accepted = {1}")
    @CsvSource({
        "0, false",
        "3, true",
        "8, false"
    })
    void testScoreCountEquivalenceClasses(
            int scoreCount, boolean shouldBeAccepted) {

        Student student = createStudentWithScores(scoreCount);

        if (shouldBeAccepted) {
            assertDoesNotThrow(() -> roster.add_student(student));
        } else {
            assertThrows(
                IllegalArgumentException.class,
                () -> roster.add_student(student)
            );
        }
    }

    private Student createStudentWithScores(int scoreCount) {
        Student student = new Student(
            "Test Student",
            "ROSTER-" + java.util.UUID.randomUUID()
        );

        for (int i = 0; i < scoreCount; i++) {
            student.add_score(50 + i);
        }

        return student;
    }
}
