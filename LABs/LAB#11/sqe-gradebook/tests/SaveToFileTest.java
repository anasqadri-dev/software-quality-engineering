
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.Test;

import gradebook.GradeBookIOError;
import gradebook.Roster;
import gradebook.Student;

public class SaveToFileTest extends GradeBookTestFixtures {

    @Test
    void saveToFileWritesExpectedContentWithoutCreatingARealFile() {
        AtomicReference<String> capturedPath = new AtomicReference<>();
        AtomicReference<String> capturedContent = new AtomicReference<>();

        // Mock file I/O: capture the arguments instead of writing a real file.
        Roster mockedRoster = new Roster((path, content) -> {
            capturedPath.set(path);
            capturedContent.set(content);
        });

        Student student = createStudent("Ali", 80, 90);
        mockedRoster.add_student(student);

        mockedRoster.saveToFile("fake-roster.txt");

        assertEquals("fake-roster.txt", capturedPath.get());
        assertEquals(
                "Ali,85.0" + System.lineSeparator(),
                capturedContent.get());
    }

    @Test
    void saveToFileWrapsAnIOExceptionInGradeBookIOError() {
        // Simulate a file-writing failure without accessing the file system.
        Roster failingRoster = new Roster((path, content) -> {
            throw new IOException("Simulated file-writing failure");
        });

        Student student = createStudent("Sara", 70, 90);
        failingRoster.add_student(student);

        GradeBookIOError exception = assertThrows(
                GradeBookIOError.class,
                () -> failingRoster.saveToFile("fake-roster.txt"));

        assertEquals(
                "Unable to save roster to file: fake-roster.txt",
                exception.getMessage());

        assertEquals(
                "Simulated file-writing failure",
                exception.getCause().getMessage());
    }
}
