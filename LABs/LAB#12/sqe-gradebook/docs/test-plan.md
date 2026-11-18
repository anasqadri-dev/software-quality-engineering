# GradeBook Test Plan

## 1. Introduction

This test plan defines the testing approach for the GradeBook project. The project is a Java-based grade management library centered on the `Student` class, which stores a student's name, ID number, and scores. The purpose of testing is to verify that score management and student information functions behave correctly, handle invalid inputs safely, and maintain data integrity. Testing will also verify that previously fixed defects do not reappear.

## 2. Test Items

The primary test item is the Java `Student` class located at `src/gradebook/Student.java`.

The main functionality under test includes:
- Creating a `Student` object with a name and ID number.
- Adding valid scores using `add_score()`.
- Rejecting scores outside the range 0–100.
- Retrieving the student's name using `getStudentName()`.
- Retrieving the number of recorded scores using `getScoreCount()`.
- Handling student score data consistently after valid and invalid operations.

## 3. Features to be Tested

The following features will be tested:

- Student object creation with valid name and ID.
- Student name retrieval.
- Student ID storage and student identification behavior.
- Adding valid scores from 0 to 100.
- Rejecting negative scores.
- Rejecting scores greater than 100.
- Maintaining the correct score count.
- Handling an empty score list safely.
- Regression testing for previously fixed defects such as duplicate student IDs, negative scores, and empty-score average handling.

## 4. Features Not to be Tested

A graphical user interface is excluded because GradeBook is currently implemented as a Java library and does not contain a GUI. Database integration and network communication are also excluded because the current codebase does not implement persistent storage or external services. Performance testing at large production scale is outside the scope of this lab because the project is a small educational Java codebase.

## 5. Approach

Testing will primarily use functional and regression testing techniques. Positive test cases will verify that valid inputs produce the expected results, while negative test cases will verify that invalid inputs are rejected correctly. Boundary-value testing will be used for score limits such as 0 and 100, as well as invalid values below 0 and above 100. Regression tests will be executed for previously fixed defects to ensure that changes do not reintroduce earlier problems.

## 6. Pass/Fail Criteria

The test cycle will be considered a PASS when at least 95% of all planned test cases pass and 100% of Critical and High severity defects are closed or have an approved resolution. No Critical defects may remain open at release. Any failed test case related to a core GradeBook function must be investigated and either fixed or formally accepted before release.

The test cycle will be considered a FAIL if fewer than 95% of planned test cases pass, if any Critical defect remains open, or if a core functional requirement cannot be demonstrated successfully.

## 7. Test Deliverables

The following testing artifacts will be produced:

- `docs/test-plan.md` containing the overall test strategy.
- `docs/test-cases.md` containing detailed test cases.
- Requirements Traceability Matrix linking requirements to test cases.
- Java test source files under the `tests/` directory.
- Test execution results and evidence where required.
- Defect reports for any newly discovered defects.

## 8. Environmental Needs

Testing will be performed on a Windows development environment using Java 17 or later. The project source code is located under `src/gradebook`, and test code will be maintained under the `tests` directory. A Java compiler and command-line environment are required to compile and execute the tests. Git will be used to track changes to the source code and testing artifacts.

## 9. Schedule

Testing activities will be performed during the Lab 04 exercise. The test plan and requirements will be documented first, followed by detailed test cases and requirements traceability. Functional and negative tests will then be executed, followed by regression testing of previously fixed defects. Final review will confirm that the defined pass/fail criteria have been satisfied.

## 10. Risks and Contingencies

The small size of the current GradeBook implementation may limit the number of realistic production-level scenarios that can be tested. Changes to the `Student` class may introduce regressions in existing functionality, so regression tests will be executed after modifications. Incomplete requirements may also result in missing test coverage; therefore, requirements will be explicitly mapped to test cases through the RTM. If a required behavior is not implemented in the current codebase, it will be documented rather than silently assumed to exist.