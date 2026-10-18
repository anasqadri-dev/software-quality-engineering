import gradebook.Student;

public class StudentNegativeScoreTest {
    public static void main(String[] args) {
        Student student = new Student("Ali", "S001");
        try {
            student.add_score(-10);
            throw new AssertionError("Negative score was accepted");
        } catch (IllegalArgumentException e) {
            System.out.println("PASS: Negative score was rejected.");
        }
    }
}