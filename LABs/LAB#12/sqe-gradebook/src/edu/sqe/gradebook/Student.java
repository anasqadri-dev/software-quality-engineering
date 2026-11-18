package edu.sqe.gradebook;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Student {

    private final String studentId;
    private final String name;
    private final List<Integer> scores = new ArrayList<>();

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    @JsonIgnore
    public List<Integer> getScores() {
        return scores;
    }

    public void addScore(int score) {
        scores.add(score);
    }

    public int getAverage() {
        if (scores.isEmpty()) {
            return 0;
        }

        double mean = scores.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0);

        return (int) Math.round(mean);
    }
}
