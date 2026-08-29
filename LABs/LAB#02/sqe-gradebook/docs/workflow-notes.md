# Workflow Notes

## Idea → Released Workflow

```text
IDEA
  │
  ▼
ISSUE
  │
  │ QA: Clarifies requirements, acceptance criteria,
  │     and identifies possible quality risks.
  ▼
BRANCH
  │
  │ QA: Helps define what should be tested for the change.
  ▼
PULL REQUEST (PR)
  │
  │ QA: Checks that the change includes appropriate tests
  │     and that quality requirements are addressed.
  ▼
REVIEW
  │
  │ QA: Reviews the implementation and test coverage,
  │     and identifies defects or quality concerns.
  ▼
MERGE
  │
  │ QA: Confirms review requirements are satisfied
  │     before the change is integrated.
  ▼
CI (Continuous Integration)
  │
  │ QA: Monitors automated tests and quality checks
  │     and investigates failures.
  ▼
RELEASE
  │
  │ QA: Verifies that the completed change meets
  │     release requirements before delivery.
  ▼
RELEASED
```

## QA Intervention at Each Stage

| Stage       | QA Engineer Intervention                                                                |
| ----------- | --------------------------------------------------------------------------------------- |
| **Idea**    | Clarify requirements, acceptance criteria, and potential quality risks.                 |
| **Issue**   | Ensure the issue clearly describes the required behavior and conditions for acceptance. |
| **Branch**  | Identify the testing needs and quality checks associated with the change.               |
| **PR**      | Check that appropriate tests and documentation have been included.                      |
| **Review**  | Review the implementation, test coverage, and potential defects.                        |
| **Merge**   | Confirm required reviews and quality conditions are satisfied before integration.       |
| **CI**      | Monitor automated tests and quality checks and investigate failures.                    |
| **Release** | Verify that the change satisfies release requirements and is ready for delivery.        |

## Summary

The GradeBook workflow integrates QA throughout development rather than treating testing as a final step. A change begins as an idea, is recorded as an Issue, implemented on a Branch, submitted through a PR, reviewed, merged, checked by CI, and finally released. QA can contribute quality checks and risk identification throughout this entire flow.

## Task 3 — Deliberate Merge Conflict

### Cause of the Conflict

The merge conflict occurred because two feature branches modified the same lines in `Student.java` in different ways.

The first branch, `feature/rename-field-a`, renamed the student identification field to `studentId`.

The second branch, `feature/rename-field-b`, renamed the same field to `idNumber`.

After the first branch was merged into `main`, Git could not automatically determine which version should be used when the second branch was merged into `main`.

### Conflict Resolution

The conflict was resolved locally on `feature/rename-field-b`.

The following commands were used:

```bash
git switch feature/rename-field-b
git merge main