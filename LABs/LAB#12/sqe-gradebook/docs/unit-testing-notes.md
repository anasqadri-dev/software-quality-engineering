
# Unit Testing Notes — Lab 07

## 1. Testing Framework

This project uses Java and JUnit 5 for its gradebook unit tests.
The Java tests are executed through the Java Test Runner in
Visual Studio Code.

The repository also has a pytest configuration for the separate
Python-based test discovery requirement specified in the lab
instructions.

## 2. Verbose Test Output

### Command

```powershell
pytest -v tests/
```

### Output

```text
============================ test session starts ============================
platform win32 -- Python 3.14.2, pytest-9.1.1, pluggy-1.7.0
rootdir: D:\BS Software Engineering\BS-5\Software Quality Engineering
configfile: pytest.ini
plugins: anyio-4.12.1
collected 0 items

=========================== no tests ran in 0.03s ===========================
ERROR: file or directory not found: tests/
```

### When to use verbose output

Verbose output (`-v`) displays individual test names and their
results. It is useful when identifying which specific test passed
or failed.

### Interpretation

Pytest could not find the requested `tests/` directory at the
repository root, so it collected no tests. This output does not
indicate that the Java/JUnit tests failed.

## 3. Short Traceback Output

### Command

```powershell
pytest --tb=short
```

### Output

```text
============================ test session starts ============================
platform win32 -- Python 3.14.2, pytest-9.1.1, pluggy-1.7.0
rootdir: D:\BS Software Engineering\BS-5\Software Quality Engineering
configfile: pytest.ini
plugins: anyio-4.12.1
collected 0 items

=========================== warnings summary ===============================
PytestConfigWarning: No files were found in testpaths; consider removing
or adjusting your testpaths configuration. Searching recursively from
the current directory instead.

============================= 1 warning in 0.08s ===========================
```

### When to use short traceback output

The `--tb=short` option provides a shorter traceback when a Python
test fails. It is useful when quickly locating the relevant failure
without reading a lengthy traceback.

### Interpretation

Pytest did not find any tests in the configured test path. It
reported a warning and searched recursively, but still collected
no tests. Since no tests failed, there was no failure traceback
to shorten.

## 4. Java/JUnit Test Results

The Java test suite was run separately using the VS Code Java Test
Runner.

The recorded Lab 07 screenshot showed:

- Tests passed: 60
- Tests failed: 0
- Execution time: approximately 3.2 seconds

These Java/JUnit results are separate from the pytest command
results above.

## 5. Test Naming and Discovery

Java tests use JUnit annotations such as `@Test` and
`@ParameterizedTest`. The Java Test Runner discovers tests using
the Java testing framework and project configuration.

Examples from Lab 07 include:

- `ClassAverageTest.java`
- `AddScoreParameterizedTest.java`
- `SaveToFileTest.java`
- `LetterGradeTest.java`
- `NameValidationTest.java`

## 6. CI Readiness

A Java CI workflow should configure a suitable JDK, compile the
Java source and test files, run the JUnit test suite, and fail if
compilation or tests fail.

The pytest configuration is Python-specific and does not execute
Java/JUnit tests. The successful local Java test run does not by
itself confirm that a CI workflow has been configured or passed.
