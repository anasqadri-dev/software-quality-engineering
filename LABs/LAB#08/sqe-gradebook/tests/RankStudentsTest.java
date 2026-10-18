
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import gradebook.RankStudents;
import gradebook.Roster;
import gradebook.Student;

public class RankStudentsTest {

    private Student createStudent(String name, String id, double score) {
        Student student = new Student(name, id);
        student.add_score(score);
        return student;
    }

    @Test
    void testRankStudentsEmptyRosterReturnsEmptyList() {
        Roster roster = new Roster();

        assertEquals(List.of(), RankStudents.rank_students(roster));
    }

    @Test
    void testRankStudentsSingleStudentReturnsThatStudent() {
        Roster roster = new Roster();
        Student student = createStudent("Ali", "RANK-TASK2-001", 85.0);
        roster.add_student(student);

        assertEquals(
                List.of(student),
                RankStudents.rank_students(roster)
        );
    }

    @Test
    void testRankStudentsReturnsStudentsInDescendingAverageOrder() {
        Roster roster = new Roster();

        Student low = createStudent("Ali", "RANK-TASK3-001", 60.0);
        Student high = createStudent("Sara", "RANK-TASK3-002", 95.0);
        Student middle = createStudent("Ahmed", "RANK-TASK3-003", 80.0);

        roster.add_student(low);
        roster.add_student(high);
        roster.add_student(middle);

        assertEquals(
                List.of(high, middle, low),
                RankStudents.rank_students(roster)
        );
    }
}
