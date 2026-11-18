package edu.sqe.gradebook;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class GradebookService {

    public static final int MAX_SCORES = 5;

    private final Map<String, Student> students = new LinkedHashMap<>();

    public synchronized Student addStudent(Student student) {

        if (students.containsKey(student.getStudentId())) {
            throw new ApiException(
                    HttpStatus.CONFLICT,
                    "studentId already exists");
        }

        students.put(student.getStudentId(), student);
        return student;
    }

    public synchronized List<Student> allStudents() {
        return new ArrayList<>(students.values());
    }

    public synchronized Student getStudent(String studentId) {

        Student student = students.get(studentId);

        if (student == null) {
            throw new ApiException(
                    HttpStatus.NOT_FOUND,
                    "Unknown student: " + studentId);
        }

        return student;
    }

    public synchronized Student recordScore(String studentId, int score) {

        Student student = getStudent(studentId);

        if (score < 0 || score > 100) {
            throw new ApiException(
                    HttpStatus.BAD_REQUEST,
                    "score must be between 0 and 100");
        }

        if (student.getScores().size() >= MAX_SCORES) {
            throw new ApiException(
                    HttpStatus.BAD_REQUEST,
                    "limit of 5 scores reached");
        }

        student.addScore(score);
        return student;
    }

    public static String letterGrade(int score) {

        if (score < 0 || score > 100) {
            throw new IllegalArgumentException(
                    "score must be between 0 and 100");
        }

        if (score >= 90) {
            return "A";
        } else if (score >= 80) {
            return "B";
        } else if (score >= 70) {
            return "C";
        } else if (score >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    synchronized void reset() {
        students.clear();
    }
}
