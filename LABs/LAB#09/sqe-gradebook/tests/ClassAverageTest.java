
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import gradebook.Student;

public class ClassAverageTest extends GradeBookTestFixtures {

    @Test
    void emptyRosterReturnsZero() {
        // The shared fixture creates a fresh roster before each test.
        assertEquals(0.0, roster.class_average(), 0.001);
    }

    @Test
    void singleStudentReturnsIndividualAverage() {
        Student student = createStudent("Ali", 80, 90);

        roster.add_student(student);

        assertEquals(85.0, roster.class_average(), 0.001);
    }

    @Test
    void multipleStudentsReturnAverageOfIndividualAverages() {
        Student first = createStudent("Ali", 80, 90);
        Student second = createStudent("Sara", 60, 80);

        roster.add_student(first);
        roster.add_student(second);

        // Ali's average = 85; Sara's average = 70.
        // Class average = (85 + 70) / 2 = 77.5.
        assertEquals(77.5, roster.class_average(), 0.001);
    }
}
