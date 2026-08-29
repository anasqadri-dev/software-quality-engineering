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
     * @throws IllegalArgumentException if the score is negative
    */
    public void addScore(double score) {
        if (score < 0) {
            throw new IllegalArgumentException("Score cannot be negative");
        }
        scores.add(score);
    }
}