
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import gradebook.Roster;
import gradebook.Student;

public abstract class GradeBookTestFixtures {

    protected Roster roster;

    private static List<Double> sharedReferenceScores;

    @BeforeAll
    protected static void setUpSharedFixture() {
        /*
         * Module-style shared fixture:
         * Cache immutable reference data for reuse across test classes.
         * JUnit's @BeforeAll is class-scoped, not literally module-scoped.
         * The static guard reuses this data within the same class loader.
         *
         * This pattern is appropriate for expensive, reusable setup that
         * is safe to share and does not change between tests.
         */
        if (sharedReferenceScores == null) {
            sharedReferenceScores = List.of(80.0, 90.0, 70.0);
        }
    }

    @BeforeEach
    protected void setUpFunctionFixture() {
        /*
         * Function-scoped fixture equivalent:
         * create a fresh Roster for every test so changes in one test
         * cannot affect another test's roster.
         */
        roster = new Roster();
    }

    protected Student createStudent(
            String name, double... scores) {

        Student student = new Student(
            name,
            "TEST-" + UUID.randomUUID()
        );

        for (double score : scores) {
            student.add_score(score);
        }

        return student;
    }

    protected Student createStudentWithSharedScores(String name) {
        Student student = new Student(
            name,
            "TEST-" + UUID.randomUUID()
        );

        for (double score : sharedReferenceScores) {
            student.add_score(score);
        }

        return student;
    }

    protected String validStudentName() {
        return "Ali Khan";
    }

    protected String overlengthStudentName() {
        return "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXY";
    }

    protected static double[] referenceScores() {
        return sharedReferenceScores.stream()
            .mapToDouble(Double::doubleValue)
            .toArray();
    }
}
