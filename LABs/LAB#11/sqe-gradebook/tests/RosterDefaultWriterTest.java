import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import gradebook.Roster;
import gradebook.Student;

class RosterDefaultWriterTest {

    @TempDir
    Path temporaryDirectory;

    @Test
    void defaultConstructorWritesRosterToDisk() throws Exception {
        Path output = temporaryDirectory.resolve("roster.txt");
        Roster roster = new Roster();

        Student student = new Student("Anas", "LAB11-FILE-001");
        student.add_score(75.0);
        student.add_score(85.0);
        roster.add_student(student);

        roster.saveToFile(output.toString());

        assertTrue(Files.exists(output));
        assertEquals(
            "Anas,80.0" + System.lineSeparator(),
            Files.readString(output)
        );
    }
}
