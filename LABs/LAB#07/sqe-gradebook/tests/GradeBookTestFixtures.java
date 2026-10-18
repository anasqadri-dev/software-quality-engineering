
import java.util.UUID;

import gradebook.Student;

public final class GradeBookTestFixtures {

    private GradeBookTestFixtures() {
    }

    public static Student student(
            String name, String id, double... scores) {

        Student student = new Student(name, id);

        for (double score : scores) {
            student.add_score(score);
        }

        return student;
    }

    public static Student studentWithUniqueId(
            String name, double... scores) {

        return student(
            name,
            "TEST-" + UUID.randomUUID(),
            scores
        );
    }
}
