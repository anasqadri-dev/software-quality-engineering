
package gradebook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Roster {

    /**
     * Abstraction for writing content to a file.
     * Tests can replace the real file writer with a mock.
     */
    @FunctionalInterface
    public interface FileWriterAction {
        void write(String filePath, String content) throws IOException;
    }

    private final List<Student> students;
    private final FileWriterAction fileWriter;

    /**
     * Normal constructor: uses real file I/O.
     */
    public Roster() {
        this((filePath, content) ->
                Files.writeString(Path.of(filePath), content));
    }

    /**
     * Constructor for injecting a real or mocked file writer.
     */
    public Roster(FileWriterAction fileWriter) {
        this.students = new ArrayList<>();
        this.fileWriter = fileWriter;
    }

    public void add_student(Student student) {
        int scoreCount = student.getScoreCount();

        if (scoreCount < 1 || scoreCount > 6) {
            throw new IllegalArgumentException(
                    "Student must have between 1 and 6 scores");
        }

        students.add(student);
    }

    public int getStudentCount() {
        return students.size();
    }

    /**
     * Returns the average of the individual student averages.
     * An empty roster has an average of zero.
     */
    public double class_average() {
        if (students.isEmpty()) {
            return 0.0;
        }

        double total = 0.0;

        for (Student student : students) {
            total += student.calculateAverage();
        }

        return total / students.size();
    }

    /**
     * Saves each student's name and average.
     * Each student occupies one line: name,average
     */
    public void saveToFile(String filePath) {
        StringBuilder content = new StringBuilder();

        for (Student student : students) {
            content.append(student.getStudentName())
                   .append(",")
                   .append(student.calculateAverage())
                   .append(System.lineSeparator());
        }

        try {
            fileWriter.write(filePath, content.toString());
        } catch (IOException | SecurityException e) {
            throw new GradeBookIOError(
                    "Unable to save roster to file: " + filePath, e);
        }
    }
}
