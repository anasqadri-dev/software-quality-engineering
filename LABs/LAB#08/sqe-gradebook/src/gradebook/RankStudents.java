
package gradebook;

import java.util.ArrayList;
import java.util.List;

public final class RankStudents {

    private RankStudents() {
        // Utility class; do not instantiate.
    }

    /**
     * Returns the roster's students as a new list.
     * Ranking and tie-breaking will be implemented in a later TDD cycle.
     */
    public static List<Student> rank_students(Roster roster) {
        if (roster == null) {
            throw new IllegalArgumentException("Roster cannot be null");
        }

        return new ArrayList<>(roster.getStudents());
    }
}
