import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import gradebook.RankStudents;
import gradebook.Roster;
import gradebook.Student;

public class PercentileTest {

    @Test
    void testPercentileForHighestScoringStudent() {
        Roster roster = new Roster();

        Student ali = createStudent("Ali", "PERCENTILE-001", 70.0);
        Student sara = createStudent("Sara", "PERCENTILE-002", 90.0);
        Student ahmed = createStudent("Ahmed", "PERCENTILE-003", 80.0);

        roster.add_student(ali);
        roster.add_student(sara);
        roster.add_student(ahmed);

        assertEquals(0.0, RankStudents.percentile(sara, roster));
    }

    private Student createStudent(String name, String id, double score) {
        Student student = new Student(name, id);
        student.add_score(score);
        return student;
    }
}