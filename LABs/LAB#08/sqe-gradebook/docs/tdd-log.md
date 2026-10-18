# Test-Driven Development (TDD) Log

## Feature

Rank students by average score in descending order, breaking ties alphabetically by student name.

## Cycle 1 — Empty Roster

- **RED:** Added a test confirming that an empty roster returns an empty list.
- **GREEN:** Implemented the minimum behavior to return an empty list.
- **REFACTOR:** Reviewed the implementation.
- **Result:** Test passed.

## Cycle 2 — Single Student

- **RED:** Added a test confirming that a roster containing one student returns that student.
- **GREEN:** Implemented the required single-student behavior.
- **REFACTOR:** Reviewed method and test naming.
- **Result:** Test passed.

## Cycle 3 — Descending Average Order

- **RED:** Added a test using three students with distinct averages.
- **GREEN:** Implemented sorting by average in descending order.
- **REFACTOR:** Reviewed the sorting implementation and test helper.
- **Result:** Test passed.

## Cycle 4 — Alphabetical Tie-Breaking

- **RED:** Added a test with students having equal averages but different names.
- **GREEN:** Added alphabetical ordering as the secondary sorting criterion.
- **REFACTOR:** Reviewed the comparator and ran the test suite.
- **Result:** Test passed.

## Cycle 5 — Zero-Average Student

- **RED:** Added a test mixing a zero-average student with students having higher averages.
- **GREEN:** Verified the ranking places the zero-average student after students with higher averages.
- **REFACTOR:** Reviewed the comparator and confirmed that ranking uses a copy of the roster's student list.
- **Regression:** No regressions observed in the displayed test run.
- **Result:** All five ranking tests passed.

## Regression Testing

- The Java Test Runner screenshot showed **66/66 tests passing** after the ranking and tie-breaking changes.
- No regression was visible in that test run.
- Re-run the complete Java test suite after the final edits and update this section if any failures occur.

## Final Sorting Rules

1. Students with higher averages appear first.
2. Students with equal averages appear in alphabetical order by name.
3. The original roster order is not modified by ranking.
4. A null roster is rejected with `IllegalArgumentException`.
