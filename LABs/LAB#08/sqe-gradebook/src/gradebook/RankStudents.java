
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
}
