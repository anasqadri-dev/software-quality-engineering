import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import gradebook.RankStudents;
import gradebook.Roster;
import gradebook.Student;

public class PercentileTest {

    @Test
    void testHighestRankedStudentReceives100Percent() {
        Roster roster = new Roster();

        Student ali = createStudent("Ali", "PERCENTILE-001", 70.0);
        Student sara = createStudent("Sara", "PERCENTILE-002", 90.0);
        Student ahmed = createStudent("Ahmed", "PERCENTILE-003", 80.0);

        roster.add_student(ali);
        roster.add_student(sara);
        roster.add_student(ahmed);

        assertEquals(100.0, RankStudents.percentile(sara, roster), 0.001);
    }

    @Test
    void testLowestRankedStudentReceivesZeroPercent() {
        Roster roster = new Roster();

        Student ali = createStudent("Ali", "PERCENTILE-004", 70.0);
        Student sara = createStudent("Sara", "PERCENTILE-005", 90.0);
        Student ahmed = createStudent("Ahmed", "PERCENTILE-006", 80.0);

        roster.add_student(ali);
        roster.add_student(sara);
        roster.add_student(ahmed);

        assertEquals(0.0, RankStudents.percentile(ali, roster), 0.001);
    }

    @Test
    void testSingleStudentReceives100Percent() {
        Roster roster = new Roster();
        Student ali = createStudent("Ali", "PERCENTILE-007", 85.0);
        roster.add_student(ali);

        assertEquals(100.0, RankStudents.percentile(ali, roster), 0.001);
    }

    @Test
    void testEmptyRosterThrowsException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> RankStudents.percentile(
                        createStudent("Ali", "PERCENTILE-008", 85.0),
                        new Roster()
                )
        );
    }

    @Test
    void testStudentNotInRosterThrowsException() {
        Roster roster = new Roster();
        Student ali = createStudent("Ali", "PERCENTILE-009", 85.0);
        Student sara = createStudent("Sara", "PERCENTILE-010", 90.0);
        roster.add_student(ali);

        assertThrows(
                IllegalArgumentException.class,
                () -> RankStudents.percentile(sara, roster)
        );
    }

    @Test
    void testNullStudentThrowsException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> RankStudents.percentile(null, new Roster())
        );
    }

    @Test
    void testNullRosterThrowsException() {
        Student ali = createStudent("Ali", "PERCENTILE-011", 85.0);

        assertThrows(
                IllegalArgumentException.class,
                () -> RankStudents.percentile(ali, null)
        );
    }

    private Student createStudent(String name, String id, double score) {
        Student student = new Student(name, id);
        student.add_score(score);
        return student;
    }
}
