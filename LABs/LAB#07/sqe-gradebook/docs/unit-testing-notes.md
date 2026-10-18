
# Unit Testing Notes — Lab 07

## 1. Test Framework

This project uses Java and JUnit 5 for unit testing. Tests are
executed through the Java Test Runner in Visual Studio Code.

The tests cover student score validation, grade calculation, name
validation, roster management, class averages, file-saving behavior,
and error handling.

## 2. Running All Tests

### Procedure

1. Open the project in Visual Studio Code.
2. Open the Testing panel from the Activity Bar.
3. Select **Run Tests** to execute the discovered test suite.
4. Review the test results in Test Explorer.

### Recorded Test Result

The test run shown in VS Code Test Explorer reported:

- **Tests passed:** 60
- **Tests failed:** 0
- **Execution time:** Approximately 3.2 seconds

These results are from the recorded test run shown in the Lab 07
workspace screenshot.

## 3. Detailed Test Results

Test Explorer displays individual test classes and test cases.
Green check marks indicate successful tests.

This detailed view is useful when:

- Verifying individual test cases.
- Identifying which test failed after a code change.
- Checking parameterized test cases separately.
- Confirming that existing tests still pass after refactoring.

## 4. Failure Output and Debugging

When a test fails, select the failed test in Test Explorer and
inspect the available failure details.

Use the detailed failure information when the expected result
differs from the actual result or when an exception is unexpected.

For a quick overview, check the total number of passed and failed
tests. For debugging, inspect the specific failed test and its
failure details.

The screenshot's Test Results pane reports that the test run did
not record additional output. Therefore, no unobserved console
output or failure traceback is reproduced in these notes.

## 5. Test Naming and Discovery

Java tests use descriptive class and method names.

Examples from this project include:

- `ClassAverageTest.java`
- `AddScoreParameterizedTest.java`
- `SaveToFileTest.java`
- `LetterGradeTest.java`
- `NameValidationTest.java`

The Java Test Runner discovers tests based on the configured Java
testing framework and its test annotations, including JUnit 5
annotations such as `@Test` and `@ParameterizedTest`.

Descriptive test names make failures easier to locate and understand.

## 6. CI Readiness

For continuous integration, the repository should use a Java-aware
test runner with a configured JDK and the required JUnit dependencies.

A CI workflow should:

1. Check out the repository.
2. Set up the required JDK.
3. Resolve or configure the project's JUnit dependencies.
4. Compile the source and test files.
5. Run the complete test suite.
6. Fail the workflow if compilation or any test fails.

The current test results establish a passing local test run. A
successful CI run must be verified separately after a Java CI
workflow is configured.
