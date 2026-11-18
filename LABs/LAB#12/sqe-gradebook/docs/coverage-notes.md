# Lab 11 — Automated Test Coverage (Java GradeBook)

## Project and tooling

The course handout presents GradeBook and LibraryHub alternatives and demonstrates coverage with Python `pytest-cov`. This submission continues the GradeBook choice used throughout this semester and applies the equivalent Java workflow:

- Production language: Java
- Test framework: JUnit 5
- Coverage tool: JaCoCo
- Build and threshold enforcement: Maven
- Source directory: `src/gradebook`
- Test directory: `tests`

The Lab 11 copy is isolated under `LABs/LAB#11/sqe-gradebook`. Previous lab folders are not modified.

Run the final suite and enforce the configured coverage thresholds:

```powershell
mvn -f "LABs/LAB#11/sqe-gradebook/pom.xml" clean verify
```

Generate only the HTML report after running tests:

```powershell
mvn -f "LABs/LAB#11/sqe-gradebook/pom.xml" clean test jacoco:report
```

Open:

```text
LABs/LAB#11/sqe-gradebook/target/site/jacoco/index.html
```

## Task 1 — Baseline report

The baseline was measured from the Lab 11 snapshot before the new coverage-gap tests were added:

- Baseline revision: `a9c6c13e6c1db052474247f0c0b99089c6ff84fd`
- Tests executed: 72
- Test failures/errors: 0
- Production classes analyzed: 5

| Metric | Baseline |
|---|---:|
| Line coverage | **95.05%** (96 / 101 lines) |
| Branch coverage | **93.10%** (54 / 58 branches) |
| Instruction coverage | 93.16% (368 / 395 instructions) |

The baseline HTML report is included in the GitHub Actions artifact under `baseline/index.html`. The per-class table in this report shows the weakest source files.

## Task 2 — Three weakest source files / functions

The three lowest source-file line-coverage results in the baseline report were:

| Source file | Baseline line coverage | Uncovered lines / branches | Interpretation |
|---|---:|---|---|
| `Roster.java` | 93.94% (31 / 33) | Lines 22 and 42 | The default file-writer lambda (`Files.writeString`) was not exercised, and the student-count accessor had no direct assertion. |
| `GradeBook.java` | 94.74% (18 / 19) | Line 3; one branch at line 43 | The single missed line maps to the implicit default constructor of this static-method utility class. The null-name side of the `name == null || name.isEmpty()` validation condition was not tested. |
| `RankStudents.java` | 95.45% (21 / 22) | Line 26 and one branch at line 25 | The explicit null-roster exception path in `rank_students` was not exercised. |

A further branch-coverage hotspot was `Student.java`: its baseline line coverage was 96.00%, but branch coverage was only **87.50%**. The duplicate-student-ID rejection path (lines 16–17) and the null-name short-circuit path in `validateName` (line 55) were absent from the JUnit coverage.

## Task 3 — Close the coverage gaps

The following tests were added to the Lab 11 copy:

- `RosterDefaultWriterTest`: uses JUnit's temporary directory and the real default file writer to verify that the output file is created and contains the expected student average.
- `RosterCoverageTest`: verifies defensive-copy behavior, empty output, insertion order, student count, and the `SecurityException` file-write error path.
- `RankStudentsCoverageTest`: covers the null-roster guard, verifies ranking returns a copy without changing roster order, and covers percentile rejection paths.
- `GradeBookCoverageTest`: covers null and empty names, maximum name length, invalid characters, and the `Student.validateName` null/length paths.
- `StudentConstructorCoverageTest`: verifies duplicate student IDs are rejected and checks the exception message.
- `GradeBookIOErrorTest`: verifies the custom exception preserves both its message and original cause.

Existing boundary and regression tests were retained. The baseline suite and new tests run together through the Maven Surefire JUnit 5 provider.

## Task 4 — Coverage versus test quality

The existing `LetterGradeTest.testSharedReferenceScoresAreAccepted` test previously looped through shared scores and used `assertDoesNotThrow`. That assertion only established that the method did not throw; it did not verify whether the returned letter grades were correct. The test could therefore execute the conversion code while allowing an incorrect result to pass.

The test was strengthened to assert the expected mappings directly:

- 80.0 → B
- 90.0 → A
- 70.0 → C

This was a coverage trap: execution coverage was achieved, but the assertion did not validate the observable result. The revised test now checks behavior, not merely execution.

The existing `RosterTest` was also strengthened to assert the roster count after both accepted and rejected student additions, rather than relying only on `assertDoesNotThrow` / `assertThrows`.

## Task 5 — Enforce and test the threshold

The Maven JaCoCo `check` goal is bound to the `verify` phase. It enforces:

- Minimum line coverage: **85%**
- Minimum branch coverage: **97%**

The configured branch threshold is intentionally stricter than the handout's general minimum because the completed small GradeBook codebase now achieves 100% branch coverage.

The GitHub Actions workflow includes a threshold regression experiment. It copies the Lab 11 project to a temporary runner directory, disables `AddScoreParameterizedTest.java` in that disposable copy, and runs Maven `verify`. The workflow expects JaCoCo's coverage rule to reject the weakened test suite. The tracked test file is never modified or removed. The normal final build then remains unchanged and passes its configured thresholds.

## Final report

| Metric | Baseline | Final |
|---|---:|---:|
| Tests executed | 72 | **89** |
| Test failures/errors | 0 | **0** |
| Line coverage | 95.05% | **99.01%** (100 / 101 lines) |
| Branch coverage | 93.10% | **100.00%** (58 / 58 branches) |
| Instruction coverage | 93.16% | **99.24%** (392 / 395 instructions) |

Final per-class line and branch coverage:

| Source class | Final line coverage | Final branch coverage |
|---|---:|---:|
| `Student` | 100% | 100% |
| `Roster` | 100% | 100% |
| `RankStudents` | 100% | 100% |
| `GradeBook` | 94.74% | 100% |
| `GradeBookIOError` | 100% | N/A (no branches) |

The one uncovered GradeBook line is the implicit default constructor generated for the class containing static utility methods; no production decision branch remains uncovered. Overall line coverage is 99.01%, safely above the required 85% threshold.

## CI evidence and HTML reports

GitHub Actions workflow: `.github/workflows/lab11-coverage.yml`

The workflow:
1. Rebuilds the baseline JaCoCo HTML report from the saved pre-test Lab 11 snapshot.
2. Runs all final JUnit tests.
3. Runs `mvn clean verify`, enforcing line and branch thresholds.
4. Runs the disposable weakened-suite experiment and confirms JaCoCo rejects it.
5. Uploads baseline and final JaCoCo HTML reports, XML reports, and Surefire test results as the `lab11-jacoco-html-reports` artifact.

The artifact contains:
- `baseline/index.html` — baseline summary and per-class coverage
- `final/index.html` — final summary and per-class coverage
- `baseline-jacoco.xml` and `final-jacoco.xml` — machine-readable coverage data
- Surefire reports — test execution evidence

Download the artifact from the successful Lab 11 workflow run and open each `index.html` in a browser to capture the required before/after summary-table screenshots for the lab submission.
