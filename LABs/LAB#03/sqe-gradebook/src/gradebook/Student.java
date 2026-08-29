package gradebook;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private String name;
    private String idNumber;
    private List<Double> scores;

    public Student(String name, String idNumber) {
        this.name = name;
        this.idNumber = idNumber;
        this.scores = new ArrayList<>();
    }

    /**
     * Adds a score for the student.
     *
     * @param score the student's score
     * @throws IllegalArgumentException if the score is outside the range 0-100
     */
    public void add_score(double score) {
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Score must be between 0 and 100");
        }

        scores.add(score);
    }

    public String getStudentName() {
        return name;
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