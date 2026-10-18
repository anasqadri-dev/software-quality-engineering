
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import gradebook.Roster;
import gradebook.RankStudents;

import java.util.List;

public class RankStudentsTest {

    @Test
    void testRankStudentsEmptyRosterReturnsEmptyList() {
        Roster roster = new Roster();

        assertEquals(List.of(), RankStudents.rank_students(roster));
    }
}
