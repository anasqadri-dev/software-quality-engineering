import gradebook.Student;

public class DuplicateStudentIdTest {

    public static void main(String[] args) {

        Student student1 = new Student("Ali", "S001");

        try {
            Student student2 = new Student("Ahmed", "S001");

            System.out.println("TEST FAILED: Duplicate student ID was accepted.");

        } catch (IllegalArgumentException e) {

            System.out.println("TEST PASSED: Duplicate student ID was rejected.");
        }
    }
}