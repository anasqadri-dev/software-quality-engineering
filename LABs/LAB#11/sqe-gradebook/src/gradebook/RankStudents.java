
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
     *
     * @param roster the roster to rank
     * @return a new list of students ranked by average score
     * @throws IllegalArgumentException if the roster is null
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
     * Calculates a student's percentile within the roster.
     *
     * The highest-ranked student receives 100.0, and the lowest-ranked
     * student receives 0.0. A roster with one student returns 100.0.
     *
     * @param student the student whose percentile is calculated
     * @param roster the roster containing the student
     * @return the student's percentile from 0.0 to 100.0
     * @throws IllegalArgumentException if either argument is null,
     *         the roster is empty, or the student is not in the roster
     */
    public static double percentile(Student student, Roster roster) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }

        if (roster == null) {
            throw new IllegalArgumentException("Roster cannot be null");
        }

        List<Student> rankedStudents = rank_students(roster);
        int totalStudents = rankedStudents.size();

        if (totalStudents == 0) {
            throw new IllegalArgumentException("Roster cannot be empty");
        }

        int rankIndex = rankedStudents.indexOf(student);

        if (rankIndex == -1) {
            throw new IllegalArgumentException(
                    "Student is not part of the roster");
        }

        if (totalStudents == 1) {
            return 100.0;
        }

        return (double) (totalStudents - 1 - rankIndex)
                / (totalStudents - 1) * 100.0;
    }
}
