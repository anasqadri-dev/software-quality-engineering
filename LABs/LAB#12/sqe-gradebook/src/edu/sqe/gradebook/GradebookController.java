
package edu.sqe.gradebook;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GradebookController {

    private final GradebookService service;

    public GradebookController(GradebookService service) {
        this.service = service;
    }

    // Register a student
    @PostMapping("/students")
    public ResponseEntity<Student> addStudent(
            @RequestBody Map<String, Object> request) {

        Object idValue = request.get("studentId");
        Object nameValue = request.get("name");

        if (!(idValue instanceof String studentId)
                || !studentId.matches("S[0-9]{4}")) {
            throw new ApiException(
                    HttpStatus.BAD_REQUEST,
                    "studentId must follow the format S followed by 4 digits");
        }

        if (!(nameValue instanceof String name) || name.isBlank()) {
            throw new ApiException(
                    HttpStatus.BAD_REQUEST,
                    "name is required");
        }

        Student student = new Student(studentId, name);
        service.addStudent(student);

        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    // Record a score
    @PostMapping("/students/{studentId}/scores")
    public ResponseEntity<Map<String, Object>> recordScore(
            @PathVariable String studentId,
            @RequestBody Map<String, Object> request) {

        Object scoreValue = request.get("score");

        if (!(scoreValue instanceof Integer score)) {
            throw new ApiException(
                    HttpStatus.BAD_REQUEST,
                    "score must be an integer");
        }

        Student student = service.recordScore(studentId, score);

        Map<String, Object> response = Map.of(
                "studentId", student.getStudentId(),
                "score", score,
                "average", student.getAverage()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Get all students
    @GetMapping("/students")
    public ResponseEntity<?> getAllStudents() {
        return ResponseEntity.ok(service.allStudents());
    }

    // Get one student
    @GetMapping("/students/{studentId}")
    public ResponseEntity<Student> getStudent(
            @PathVariable String studentId) {

        return ResponseEntity.ok(service.getStudent(studentId));
    }

    // Get student's average and letter grade
    @GetMapping("/students/{studentId}/grade")
    public ResponseEntity<Map<String, Object>> getGrade(
            @PathVariable String studentId) {

        Student student = service.getStudent(studentId);

        if (student.getScores().isEmpty()) {
            throw new ApiException(
                    HttpStatus.BAD_REQUEST,
                    "No scores recorded for this student");
        }

        Map<String, Object> response = Map.of(
                "studentId", student.getStudentId(),
                "average", student.getAverage(),
                "letterGrade",
                GradebookService.letterGrade(student.getAverage())
        );

        return ResponseEntity.ok(response);
    }

    // Get letter grade for a supplied score
    @GetMapping("/grade-scale")
    public ResponseEntity<Map<String, Object>> getGradeScale(
            @RequestParam int score) {

        if (score < 0 || score > 100) {
            throw new ApiException(
                    HttpStatus.BAD_REQUEST,
                    "score must be between 0 and 100");
        }

        Map<String, Object> response = Map.of(
                "score", score,
                "letterGrade", GradebookService.letterGrade(score)
        );

        return ResponseEntity.ok(response);
    }
}