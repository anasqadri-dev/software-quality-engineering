package gradebook;

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
}