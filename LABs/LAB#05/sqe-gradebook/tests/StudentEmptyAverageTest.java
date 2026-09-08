import gradebook.Student;

public class StudentEmptyAverageTest {
    public static void main(String[] args) {
        // Regression test for Issue #23
        Student student = new Student("Ali", "S001");
        double emptyAverage = student.calculateAverage();
        if (emptyAverage != 0.0) {
            throw new AssertionError("Expected average of 0.0 for no scores, but got " + emptyAverage);
        }
        // Verify normal average calculation
        student.add_score(80);
        student.add_score(90);
        student.add_score(70);
        double average = student.calculateAverage();
        if (average != 80.0) {
            throw new AssertionError("Expected average of 80.0, but got " + average);
        }
        System.out.println("PASS: Empty and normal average calculations work correctly.");
    }
}