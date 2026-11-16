
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

        Student ali = createStudent("Ali", "RANK-TASK3-001", 70.0);
        Student sara = createStudent("Sara", "RANK-TASK3-002", 95.0);
        Student ahmed = createStudent("Ahmed", "RANK-TASK3-003", 80.0);

        roster.add_student(ali);
        roster.add_student(sara);
        roster.add_student(ahmed);

        assertEquals(
                List.of(sara, ahmed, ali),
                RankStudents.rank_students(roster)
        );
    }

    @Test
    void testRankStudentsBreaksEqualAverageTiesAlphabetically() {
        Roster roster = new Roster();

        Student zain = createStudent("Zain", "RANK-TASK4-001", 85.0);
        Student ali = createStudent("Ali", "RANK-TASK4-002", 85.0);
        Student bilal = createStudent("Bilal", "RANK-TASK4-003", 85.0);

        roster.add_student(zain);
        roster.add_student(ali);
        roster.add_student(bilal);

        assertEquals(
                List.of(ali, bilal, zain),
                RankStudents.rank_students(roster)
        );
    }

    @Test
    void testRankStudentsPlacesZeroAverageStudentLast() {
        Roster roster = new Roster();

        Student zeroAverage = createStudent(
                "Ali", "RANK-TASK5-001", 0.0
        );
        Student highAverage = createStudent(
                "Sara", "RANK-TASK5-002", 90.0
        );
        Student mediumAverage = createStudent(
                "Bilal", "RANK-TASK5-003", 60.0
        );

        roster.add_student(zeroAverage);
        roster.add_student(highAverage);
        roster.add_student(mediumAverage);

        assertEquals(
                List.of(highAverage, mediumAverage, zeroAverage),
                RankStudents.rank_students(roster)
        );
    }

    private Student createStudent(String name, String id, double... scores) {
        Student student = new Student(name, id);

        for (double score : scores) {
            student.add_score(score);
        }

        return student;
    }
}
