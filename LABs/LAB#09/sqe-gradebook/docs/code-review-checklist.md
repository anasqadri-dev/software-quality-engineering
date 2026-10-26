# Code Review Checklist — GradeBook

## 1. Correctness

- [ ] The implementation follows the intended requirements.
- [ ] Calculations and ranking logic handle normal inputs correctly.
- [ ] Boundary conditions and edge cases are handled.
- [ ] Empty or invalid input does not cause unexpected behavior.

## 2. Tests

- [ ] Tests cover the main functionality.
- [ ] Tests cover boundary conditions and edge cases.
- [ ] Expected results are checked explicitly.
- [ ] Existing tests continue to pass after changes.

## 3. Readability

- [ ] Method and variable names are meaningful.
- [ ] Methods are small and have clear responsibilities.
- [ ] Comments and documentation explain non-obvious behavior.
- [ ] Formatting and naming conventions are consistent.

## 4. Design

- [ ] The implementation follows the existing GradeBook architecture.
- [ ] Unnecessary code duplication is avoided.
- [ ] Responsibilities are separated appropriately.
- [ ] Changes do not unnecessarily break existing functionality.

## 5. Risk

- [ ] Invalid inputs cannot cause unexpected failures.
- [ ] The implementation does not introduce obvious security risks.
- [ ] Performance is reasonable for expected input sizes.
- [ ] Backward compatibility with existing functionality is preserved.

## Lessons Learned Retrospective

### Most Valuable Issue Caught

The most valuable issue caught during the review was the incorrect percentile formula, which assigned 0% to the highest-ranked student. The review also identified missing handling for empty rosters, null arguments, and students not present in the roster. These issues were addressed, and tests were added for the expected behavior and edge cases.

### Checklist Item to Add Next Time

Add a checkpoint to verify boundary conditions and invalid inputs, including the highest-ranked student, lowest-ranked student, single-student roster, empty roster, null arguments, and a student who does not belong to the roster.
