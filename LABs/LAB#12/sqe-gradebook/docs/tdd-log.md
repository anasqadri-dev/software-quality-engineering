# Test-Driven Development (TDD) Log

## Feature

Implement `rank_students(roster)` to return students sorted by average score in descending order, breaking ties alphabetically by student name.

## Cycle 1 — Empty Roster

- **RED:** Added a test confirming that an empty roster returns an empty list.
- **GREEN:** Implemented the minimum behavior required to return an empty list.
- **REFACTOR:** Reviewed the implementation before continuing to the next cycle.
- **Result:** The empty-roster test passed.

**Commits**
- RED: `1b19983` — `test(rank): red - empty roster returns empty list`
- GREEN: `0bfc146` — `feat(rank): green - stub rank_students returning empty list`

## Cycle 2 — Single Student

- **RED:** Added a test confirming that a roster containing one student returns a list containing that student.
- **GREEN:** Implemented the minimum behavior needed to return the single student.
- **REFACTOR:** Reviewed and clarified the single-student implementation.
- **Result:** The single-student test passed.

**Commits**
- RED: `a7ac2c7` — `test(rank): red - single student is returned`
- GREEN: `3fa7ad5` — `feat(rank): green - return single roster student`
- REFACTOR: `6d6d088` — `refactor(rank): clarify single-student implementation`

## Cycle 3 — Descending Average Order

- **RED:** Added a test using three students with distinct averages and asserted that the highest average appears first.
- **GREEN:** Implemented sorting by average in descending order.
- **REFACTOR:** Extracted repeated student-creation logic into a test helper.
- **Result:** Students were returned in the expected highest-to-lowest order.

**Commits**
- RED: `c71cf52` — `test(rank): red - sort students by descending average`
- GREEN: `538e2ca` — `feat(rank): green - sort students by descending average`
- REFACTOR: `d8eef48` — `refactor(rank): extract student test helper`

## Cycle 4 — Alphabetical Tie-Breaking

- **RED:** Added a test for students with equal averages but different names.
- **GREEN:** Extended the comparator to sort equal-average students alphabetically.
- **REFACTOR:** Clarified the ranking implementation and documented the TDD cycles.
- **Result:** Students with equal averages were ranked alphabetically.

**Commits**
- RED: `f60ea72` — `test(rank): red - alphabetical tie breaking`
- GREEN: `d7e6d69` — `feat(rank): green - break ties alphabetically`
- REFACTOR: `4e9d514` — `refactor(rank): clarify ranking and document TDD cycles`

## Cycle 5 — Zero-Average Student

- **RED:** Added a test mixing a zero-average student with students who had higher averages.
- **GREEN:** Verified that sorting by descending average places the zero-average student after students with higher averages.
- **REFACTOR:** Reviewed the comparator and confirmed that ranking operates on a copy of the roster's student list.
- **Result:** The zero-average ranking test passed.

**Commits**
- GREEN: `31f593d` — `feat(rank): green - rank zero average students correctly`

## TDD Retrospective

Writing tests before implementing the feature helped define the expected behavior in small, manageable steps. The initial empty-roster test encouraged a minimal implementation instead of building the complete feature immediately. The single-student and descending-order tests then guided the implementation toward returning the correct students and sorting them by average. The tie-breaking test revealed the need for a secondary alphabetical sorting criterion, while the zero-average test checked another important case. Compared with writing the implementation first, TDD provided an incremental design process and made each change easier to verify through automated tests.

## Regression Testing

The Java Test Runner screenshot showed **65/65 tests passing** after the ranking changes. No test failures were visible in that run. Run the complete test suite again after the final edits to confirm the final result.

## Final Sorting Rules

1. Students with higher averages appear first.
2. Students with equal averages appear in alphabetical order by name.
3. Ranking does not modify the original roster order.
4. A null roster is rejected with `IllegalArgumentException`.