
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import gradebook.Student;

public class StudentEmptyAverageTest extends GradeBookTestFixtures {

    @Test
    void emptyStudentAverageIsZero() {
        Student student = createStudent("Ali");

        assertEquals(0.0, student.calculateAverage(), 0.001);
    }

    @Test
    void sharedScoresProduceExpectedAverage() {
        Student student = createStudentWithSharedScores("Sara");

        assertEquals(80.0, student.calculateAverage(), 0.001);
    }

    @Test
    void studentAverageUpdatesAfterAddingScores() {
        Student student = createStudent("Ahmed");

        student.add_score(80);
        student.add_score(90);
        student.add_score(70);

        assertEquals(80.0, student.calculateAverage(), 0.001);
    }
}
