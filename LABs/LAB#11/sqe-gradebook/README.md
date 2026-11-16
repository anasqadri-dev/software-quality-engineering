# SQE GradeBook — Lab 11 (Java Test Coverage)

GradeBook is the Java project used throughout the Software Quality Engineering labs. Lab 11 measures and improves automated test coverage using JUnit 5 and JaCoCo (the Java equivalents of the handout's pytest and pytest-cov tools).

## Requirements

- JDK 17 or later
- Apache Maven 3.9 or later

## Run the tests and enforce coverage

From the repository root:

```bash
mvn -f "LABs/LAB#11/sqe-gradebook/pom.xml" clean verify
```

The build runs the JUnit 5 suite and JaCoCo, then enforces:

- Line coverage: at least 85%
- Branch coverage: at least 97%

A violation causes Maven's `verify` phase to fail.

## Generate the HTML report

```bash
mvn -f "LABs/LAB#11/sqe-gradebook/pom.xml" clean test jacoco:report
```

Open the report:

```text
LABs/LAB#11/sqe-gradebook/target/site/jacoco/index.html
```

The report includes project-level and per-class line/branch coverage. The GitHub Actions workflow also generates a baseline HTML report from the pre-remediation snapshot and uploads both reports as the `lab11-jacoco-html-reports` artifact.

## Lab 11 notes

See [docs/coverage-notes.md](docs/coverage-notes.md) for the measured baseline, weakest classes, targeted tests, coverage-versus-quality discussion, final percentages, and CI evidence.
