
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import gradebook.RankStudents;
import gradebook.Roster;
import gradebook.Student;

public class RankStudentsTest {

    private Student createStudent(String name, String id, double... scores) {
        Student student = new Student(name, id);

        for (double score : scores) {
            student.add_score(score);
        }

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

        assertEquals(List.of(student), RankStudents.rank_students(roster));
    }

    @Test
    void testRankStudentsReturnsStudentsInDescendingAverageOrder() {
        Roster roster = new Roster();

        Student low = createStudent("Ali", "RANK-TASK3-001", 60.0, 70.0);
        Student high = createStudent("Sara", "RANK-TASK3-002", 90.0, 100.0 - 1.0);
        Student middle = createStudent("Usman", "RANK-TASK3-003", 75.0, 85.0);

        roster.add_student(low);
        roster.add_student(high);
        roster.add_student(middle);

        assertEquals(
            List.of(high, middle, low),
            RankStudents.rank_students(roster)
        );
    }

    @Test
    void testRankStudentsBreaksEqualAverageTiesAlphabetically() {
        Roster roster = new Roster();

        Student zara = createStudent("Zara", "RANK-TASK4-001", 80.0, 90.0);
        Student ali = createStudent("Ali", "RANK-TASK4-002", 85.0, 85.0);

        roster.add_student(zara);
        roster.add_student(ali);

        assertEquals(
            List.of(ali, zara),
            RankStudents.rank_students(roster)
        );
    }

    @Test
    void testRankStudentsDoesNotModifyOriginalRosterOrder() {
        Roster roster = new Roster();

        Student lower = createStudent("Ali", "RANK-ORDER-001", 60.0);
        Student higher = createStudent("Sara", "RANK-ORDER-002", 90.0);

        roster.add_student(lower);
        roster.add_student(higher);

        RankStudents.rank_students(roster);

        assertEquals(List.of(lower, higher), roster.getStudents());
    }

    @Test
    void testRankStudentsRejectsNullRoster() {
        assertThrows(
            IllegalArgumentException.class,
            () -> RankStudents.rank_students(null)
        );
    }
}
