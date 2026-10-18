
package gradebook;

import java.util.ArrayList;
import java.util.List;

public final class RankStudents {

    private RankStudents() {
    }

    public static List<Student> rank_students(Roster roster) {
        List<Student> rankedStudents = new ArrayList<>();

        for (Student student : roster.getStudents()) {
            rankedStudents.add(student);
        }

        return rankedStudents;
    }
}
