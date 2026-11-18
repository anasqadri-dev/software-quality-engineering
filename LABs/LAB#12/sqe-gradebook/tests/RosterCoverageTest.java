import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import gradebook.GradeBookIOError;
import gradebook.Roster;
import gradebook.Student;

class RosterCoverageTest {

    @Test
    void getStudentsReturnsDefensiveCopy() {
        Roster roster = new Roster();
        Student student = new Student("Ali", "LAB11-ROSTER-001");
        student.add_score(80.0);
        roster.add_student(student);

        List<Student> returned = roster.getStudents();
        assertEquals(1, returned.size());
        assertEquals(student, returned.get(0));
        assertNotSame(roster.getStudents(), returned);

        returned.clear();
        assertEquals(1, roster.getStudentCount());
        assertEquals(1, roster.getStudents().size());
    }

    @Test
    void saveToFileWrapsSecurityException() {
        Roster roster = new Roster((path, content) -> {
            throw new SecurityException("write denied");
        });

        assertThrows(
            GradeBookIOError.class,
            () -> roster.saveToFile("blocked.txt")
        );
    }

    @Test
    void saveToFileWritesEmptyContentForEmptyRoster() {
        final String[] captured = {null};
        Roster roster = new Roster((path, content) -> captured[0] = content);

        roster.saveToFile("empty.txt");

        assertEquals("", captured[0]);
    }

    @Test
    void saveToFileWritesMultipleStudentsInInsertionOrder() {
        final String[] captured = {null};
        Roster roster = new Roster((path, content) -> captured[0] = content);
        Student first = new Student("Ali", "LAB11-ROSTER-002");
        first.add_score(80.0);
        Student second = new Student("Sara", "LAB11-ROSTER-003");
        second.add_score(90.0);
        roster.add_student(first);
        roster.add_student(second);

        roster.saveToFile("roster.txt");

        assertEquals(
            "Ali,80.0" + System.lineSeparator()
                + "Sara,90.0" + System.lineSeparator(),
            captured[0]
        );
    }
}
