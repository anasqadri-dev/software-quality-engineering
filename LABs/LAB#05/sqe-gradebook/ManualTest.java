import gradebook.Student;

public class ManualTest {
    public static void main(String[] args) {

        // TC-001: add_score valid input
        System.out.println("=== TC-001: Valid Score ===");
        Student s1 = new Student("Ali", "TC001");
        s1.add_score(85);

        if (s1.getScoreCount() == 1) {
            System.out.println("PASS: Valid score accepted and stored.");
        } else {
            System.out.println("FAIL: Valid score was not stored.");
        }


        // TC-002: add_score negative input
        System.out.println("\n=== TC-002: Negative Score ===");
        Student s2 = new Student("Ahmed", "TC002");

        try {
            s2.add_score(-5);
            System.out.println("FAIL: Negative score was accepted.");
        } catch (IllegalArgumentException e) {
            System.out.println("PASS: Negative score rejected.");
        }


        // TC-003: add_score non-numeric input
        System.out.println("\n=== TC-003: Non-Numeric Score ===");
        System.out.println("BLOCKED: Current Student.java only accepts double input; no string/non-numeric score API exists.");


        // TC-004: average with scores
        System.out.println("\n=== TC-004: Average With Multiple Scores ===");
        Student s4 = new Student("Sara", "TC004");
        s4.add_score(80);
        s4.add_score(90);

        double average4 = s4.calculateAverage();

        if (average4 == 85.0) {
            System.out.println("PASS: Average calculated as 85.0.");
        } else {
            System.out.println("FAIL: Expected 85.0 but got " + average4);
        }


        // TC-005: average with empty list
        System.out.println("\n=== TC-005: Empty Average ===");
        Student s5 = new Student("Hina", "TC005");

        double average5 = s5.calculateAverage();

        if (average5 == 0.0) {
            System.out.println("PASS: Empty score list returns 0.0.");
        } else {
            System.out.println("FAIL: Expected 0.0 but got " + average5);
        }


        // TC-006: average with single score
        System.out.println("\n=== TC-006: Single Score Average ===");
        Student s6 = new Student("Usman", "TC006");
        s6.add_score(75);

        double average6 = s6.calculateAverage();

        if (average6 == 75.0) {
            System.out.println("PASS: Single score average is 75.0.");
        } else {
            System.out.println("FAIL: Expected 75.0 but got " + average6);
        }


        // TC-007: duplicate student ID rejection
        System.out.println("\n=== TC-007: Duplicate Student ID ===");

        try {
            Student s7a = new Student("Student A", "DUP001");
            Student s7b = new Student("Student B", "DUP001");

            System.out.println("FAIL: Duplicate ID was accepted.");
        } catch (IllegalArgumentException e) {
            System.out.println("PASS: Duplicate ID rejected.");
        }


        // TC-008: name case-insensitivity
        System.out.println("\n=== TC-008: Case-Insensitive Name Comparison ===");
        System.out.println("BLOCKED: Student.java has no name comparison method to execute.");


        // TC-009: maximum score boundary
        System.out.println("\n=== TC-009: Maximum Score 100 ===");
        Student s9 = new Student("Student 9", "TC009");

        try {
            s9.add_score(100);
            System.out.println("PASS: Score 100 accepted.");
        } catch (IllegalArgumentException e) {
            System.out.println("FAIL: Score 100 was rejected.");
        }


        // TC-010: minimum score boundary
        System.out.println("\n=== TC-010: Minimum Score 0 ===");
        Student s10 = new Student("Student 10", "TC010");

        try {
            s10.add_score(0);
            System.out.println("PASS: Score 0 accepted.");
        } catch (IllegalArgumentException e) {
            System.out.println("FAIL: Score 0 was rejected.");
        }


        // TC-011: grade-letter conversion mid-range
        System.out.println("\n=== TC-011: Grade-Letter Conversion Mid-Range ===");
        System.out.println("BLOCKED: Student.java has no grade-letter conversion method.");


        // TC-012: grade-letter conversion boundary
        System.out.println("\n=== TC-012: Grade-Letter Conversion Boundary ===");
        System.out.println("BLOCKED: Student.java has no grade-letter conversion method.");

        System.out.println("\n=== MANUAL EXECUTION COMPLETE ===");
    }
}