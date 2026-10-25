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

## Lessons Learned

*To be completed after the code-review exercise.*

- **Most valuable issue caught:** Record the issue identified by the checklist that had the greatest impact on correctness or quality.
- **Checklist improvement:** Record one additional checkpoint that would make future code reviews more effective.
- **Reflection:** Explain how structured review comments and the Request Changes → Approve workflow improved the quality of the GradeBook implementation.