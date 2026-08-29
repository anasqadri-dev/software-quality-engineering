package gradebook;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private String name;
    private String rollNo;
    private List<Double> scores;

    public Student(String name, String rollNo) {
        this.name = name;
        this.rollNo = rollNo;
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
}