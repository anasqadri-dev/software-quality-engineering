import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import gradebook.RankStudents;
import gradebook.Roster;
import gradebook.Student;

class RankStudentsCoverageTest {

    @Test
    void rejectsNullRosterWhenRanking() {
        assertThrows(IllegalArgumentException.class, () -> RankStudents.rank_students(null));
    }

    @Test
    void rankingReturnsCopyAndDoesNotChangeRosterOrder() {
        Roster roster = new Roster();
        Student lower = student("Ali", "LAB11-RANK-001", 60);
        Student higher = student("Sara", "LAB11-RANK-002", 90);
        roster.add_student(lower);
        roster.add_student(higher);

        List<Student> ranked = RankStudents.rank_students(roster);

        assertEquals(List.of(higher, lower), ranked);
        assertNotSame(roster.getStudents(), ranked);
        assertEquals(List.of(lower, higher), roster.getStudents());
    }

    @Test
    void percentileRejectsEmptyRosterAndNonmember() {
        Roster empty = new Roster();
        Student student = student("Ali", "LAB11-RANK-003", 70);
        assertThrows(IllegalArgumentException.class,
            () -> RankStudents.percentile(student, empty));

        Roster roster = new Roster();
        roster.add_student(student);
        Student outsider = student("Sara", "LAB11-RANK-004", 80);
        assertThrows(IllegalArgumentException.class,
            () -> RankStudents.percentile(outsider, roster));
    }

    @Test
    void percentileRejectsNullInputsIndependently() {
        Student student = student("Ali", "LAB11-RANK-005", 70);
        Roster roster = new Roster();

        assertThrows(IllegalArgumentException.class,
            () -> RankStudents.percentile(null, roster));
        assertThrows(IllegalArgumentException.class,
            () -> RankStudents.percentile(student, null));
    }

    private Student student(String name, String id, double score) {
        Student student = new Student(name, id);
        student.add_score(score);
        return student;
    }
}
