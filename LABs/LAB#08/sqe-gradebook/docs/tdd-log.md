
# Test-Driven Development (TDD) Log

## Feature
Rank students by average score in descending order, breaking ties alphabetically by student name.

## Cycle 1 — Empty Roster
- **RED:** Added a test expecting an empty list for an empty roster.
- **GREEN:** Implemented the minimum behavior to return an empty list.
- **REFACTOR:** No additional refactoring was needed for this simple case.
- **Commits:** `test(rank): red - empty roster returns empty list`; `feat(rank): green - stub rank_students returning []`.

## Cycle 2 — Single Student
- **RED:** Added a test expecting a roster with one student to return a list containing that student. The test failed before the implementation was updated.
- **GREEN:** Updated the implementation to return the roster's student when there is one student.
- **REFACTOR:** Reviewed the implementation before extending it to multiple students.
- **Commits:** `test(rank): red - single student is returned`; `feat(rank): green - return single roster student`.

## Cycle 3 — Descending Average
- **RED:** Added a test with three students having distinct averages and expected highest-to-lowest ordering.
- **GREEN:** Implemented sorting by average score in descending order.
- **REFACTOR:** Extracted repeated student construction into a helper method in `RankStudentsTest.java`.

## Cycle 4 — Alphabetical Tie-Breaking
- **RED:** Added a test with two students having equal averages but different names; the expected order was alphabetical.
- **GREEN:** Added student name as the secondary sorting criterion.
- **REFACTOR:** Used a comparator to express both sorting rules clearly, copied the roster's students before sorting, and added checks for preserving the original roster order and rejecting a null roster.

## Regression Testing
- The Java Test Runner screenshot showed **66/66 tests passing** after the ranking and tie-breaking changes.
- No regression was visible in that test run.
- Re-run the complete Java test suite after the final edits and update this section if any failures occur.

## Final Sorting Rules
1. Students with higher averages appear first.
2. Students with equal averages appear in alphabetical order by name.
3. The original roster order is not modified by ranking.
4. A null roster is rejected with `IllegalArgumentException`.
