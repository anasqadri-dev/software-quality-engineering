package gradebook;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class RankStudents {

    private RankStudents() {
        // Utility class; do not instantiate.
    }

    /**
     * Ranks students by average score, highest first.
     * Students with equal averages are ordered alphabetically by name.
     *
     * The original roster is not modified.
     */
    public static List<Student> rank_students(Roster roster) {
        if (roster == null) {
            throw new IllegalArgumentException("Roster cannot be null");
        }

        List<Student> rankedStudents = new ArrayList<>(roster.getStudents());

        rankedStudents.sort(
            Comparator.comparingDouble(Student::calculateAverage)
                      .reversed()
                      .thenComparing(Student::getStudentName)
        );

        return rankedStudents;
    }
    /**
     * Calculates the percentile of a student within the roster.
     */
    public static double percentile(Student student, Roster roster) {
        List<Student> rankedStudents = rank_students(roster);

        int rankIndex = rankedStudents.indexOf(student);

        int totalStudents = rankedStudents.size();
        if (totalStudents == 0) {
            throw new IllegalArgumentException("Roster cannot be empty");
        }
        if (totalStudents == 1) {
            return 100.0;
        }
        return (double) (totalStudents - 1 - rankIndex)
                / (totalStudents - 1) * 100.0;
    }
}
