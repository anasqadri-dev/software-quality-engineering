
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import gradebook.RankStudents;
import gradebook.Roster;
import gradebook.Student;

import java.util.List;

public class RankStudentsTest {

    @Test
    void testRankStudentsEmptyRosterReturnsEmptyList() {
        Roster roster = new Roster();

        assertEquals(List.of(), RankStudents.rank_students(roster));
    }

    @Test
    void testRankStudentsSingleStudentReturnsThatStudent() {
        Roster roster = new Roster();

        Student student = new Student("Ali", "RANK-TASK2-001");
        student.add_score(85.0);
        roster.add_student(student);

        assertEquals(
                List.of(student),
                RankStudents.rank_students(roster)
        );
    }
}
