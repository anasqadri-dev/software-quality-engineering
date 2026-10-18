
package gradebook;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Roster {

    private List<Student> students;

    public Roster() {
        students = new ArrayList<>();
    }

    /**
     * Adds a student to the roster.
     *
     * A student must have between 1 and 6 scores.
     *
     * @param student the student to add
     * @throws IllegalArgumentException if the student has fewer than 1
     *                                  or more than 6 scores
     */
    public void add_student(Student student) {
        int scoreCount = student.getScoreCount();

        if (scoreCount < 1 || scoreCount > 6) {
            throw new IllegalArgumentException(
                "Student must have between 1 and 6 scores"
            );
        }

        students.add(student);
    }

    /**
     * Returns the number of students in the roster.
     */
    public int getStudentCount() {
        return students.size();
    }

    /**
     * Calculates the average of all students' individual averages.
     *
     * @return the class average, or 0.0 if the roster is empty
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
     * Saves student names and averages to a text file.
     *
     * @param filePath destination file path
     * @throws GradeBookIOError if writing the file fails
     */
    public void saveToFile(String filePath) {
        try (BufferedWriter writer =
                 Files.newBufferedWriter(Path.of(filePath))) {

            for (Student student : students) {
                writer.write(
                    student.getStudentName()
                    + ","
                    + student.calculateAverage()
                );
                writer.newLine();
            }

        } catch (IOException | SecurityException e) {
            throw new GradeBookIOError(
                "Unable to save roster to file: " + filePath, e
            );
        }
    }
}
