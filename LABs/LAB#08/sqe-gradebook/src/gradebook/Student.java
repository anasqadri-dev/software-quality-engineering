package gradebook;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Student {

    private String name;
    private String idNumber;
    private List<Double> scores;
    private static Set<String> registeredIds = new HashSet<>();

    public Student(String name, String idNumber) {
        if (registeredIds.contains(idNumber)) {
            throw new IllegalArgumentException("Student ID already exists");
        }
        this.name = name;
        this.idNumber = idNumber;
        this.scores = new ArrayList<>();
        registeredIds.add(idNumber);
    }

    /**
     * Adds a score for the student.
     *
     * @param score the student's score
     * @throws IllegalArgumentException if the score is outside the range 0-100
     */
    public void add_score(double score) {
        if (score < 0 || score >= 100) {
            throw new IllegalArgumentException("Score must be between 0 and 100");
        }

        scores.add(score);
    }

    public String getStudentName() {
        return name;
    }

    /**
     * Validates a student name.
     *
     * A valid name must contain between 1 and 50 characters.
     *
     * @param name the student name
     * @return true if the name is valid
     * @throws IllegalArgumentException if the name is null,
     *                                  empty, or longer than 50 characters
     */
    public static boolean validateName(String name) {

        if (name == null || name.length() < 1 || name.length() > 50) {
            throw new IllegalArgumentException(
                    "Name must contain between 1 and 50 characters");
        }

        return true;
    }

    /**
     * Returns the number of scores recorded for the student.
     */
    public int getScoreCount() {
        return scores.size();
    }

    /**
     * Calculates the average score for the student.
     *
     * @return the average score, or 0.0 if no scores are recorded
     */
    public double calculateAverage() {
        if (scores.isEmpty()) {
            return 0.0;
        }
        double total = 0.0;
        for (double score : scores) {
            total += score;
        }
        return total / scores.size();
    }
}