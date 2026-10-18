
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import gradebook.RankStudents;
import gradebook.Roster;
import gradebook.Student;

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

    @Test
    void testRankStudentsReturnsStudentsInDescendingAverageOrder() {
        Roster roster = new Roster();

        Student low = new Student("Ali", "RANK-TASK3-001");
        low.add_score(60.0);
        roster.add_student(low);

        Student high = new Student("Sara", "RANK-TASK3-002");
        high.add_score(95.0);
        roster.add_student(high);

        Student middle = new Student("Ahmed", "RANK-TASK3-003");
        middle.add_score(80.0);
        roster.add_student(middle);

        assertEquals(
                List.of(high, middle, low),
                RankStudents.rank_students(roster)
        );
    }
}
