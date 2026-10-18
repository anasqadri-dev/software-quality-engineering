# Equivalence Partitioning and Boundary Value Analysis — GradeBook

## 1. Introduction

This document contains the Equivalence Partitioning (EP) and Boundary Value Analysis (BVA) for the GradeBook system.

Equivalence Partitioning divides an input domain into classes where the system is expected to behave similarly. One representative value is selected from each class to reduce redundant testing while maintaining useful defect-detection coverage.

Boundary Value Analysis complements Equivalence Partitioning by focusing on values at, just below, and just above the boundaries of input classes. This helps identify defects caused by incorrect boundary conditions, such as off-by-one errors.

---

## 2. Input 1 — Student Score

The score domain is divided into valid and invalid equivalence classes.

| Class ID | Equivalence Class | Input Range   | Representative Value | Expected Result   |
| :------- | :---------------- | :------------ | :------------------: | :---------------- |
| **SC-1** | Invalid-low       | `score < 0`   |         -10          | Reject with error |
| **SC-2** | F grade           | `0–59`        |          45          | Return F          |
| **SC-3** | D grade           | `60–69`       |          65          | Return D          |
| **SC-4** | C grade           | `70–79`       |          75          | Return C          |
| **SC-5** | B grade           | `80–89`       |          85          | Return B          |
| **SC-6** | A grade           | `90–100`      |          95          | Return A          |
| **SC-7** | Invalid-high      | `score > 100` |         150          | Reject with error |

---

## 3. Input 2 — Number of Scores per Student

The business rule requires a student to have between 1 and 6 scores.

| Class ID | Equivalence Class | Input Range      | Representative Value | Expected Result   |
| :------- | :---------------- | :--------------- | :------------------: | :---------------- |
| **NS-1** | Invalid-low       | 0 scores         |          0           | Reject with error |
| **NS-2** | Valid             | 1–6 scores       |          3           | Accept            |
| **NS-3** | Invalid-high      | 7 or more scores |          8           | Reject with error |

---

## 4. Input 3 — Student Name

The student name must be a non-empty string with a maximum length of 50 characters and may contain only letters, spaces, and hyphens.

| Class ID | Equivalence Class  | Input Example           | Expected Result   |
| :------- | :----------------- | :---------------------- | :---------------- |
| **NM-1** | Valid typical name | Ali Khan                | Accept            |
| **NM-2** | Empty string       | `""`                    | Reject with error |
| **NM-3** | Over-length name   | More than 50 characters | Reject with error |
| **NM-4** | Digits or symbols  | Ali123                  | Reject with error |

---

## 5. EP Test Representatives

The selected representatives are:

### Score

- `-10` -> Invalid-low
- `45` -> F
- `65` -> D
- `75` -> C
- `85` -> B
- `95` -> A
- `150` -> Invalid-high

### Number of Scores

- `0` -> Invalid-low
- `3` -> Valid
- `8` -> Invalid-high

### Student Name

- `"Ali Khan"` -> Valid
- `""` -> Empty
- `More than 50 characters` -> Over-length
- `"Ali123"` -> Contains digits/symbols

---

## 6. Limitation of Equivalence Partitioning

Equivalence Partitioning does not focus on exact boundary values. Therefore, it can miss off-by-one defects at the edges of equivalence classes.

For example, score classes contain boundaries such as 59/60, 69/70, 79/80, 89/90, and 100. EP selects a representative from each class but does not test every boundary.

Boundary Value Analysis was therefore used to specifically target these edge values.

---

## 7. Boundary Value Analysis

### 7.1 Purpose

Boundary Value Analysis was applied to the three input areas identified in the Equivalence Partitioning analysis:

1. Letter-grade score boundaries
2. Number of scores per student
3. Student-name length

For each boundary, values at the boundary and immediately adjacent to it were tested where applicable.

---

### 7.2 BVA — Letter Grade Boundaries

The grading scale contains the following boundaries:

- F/D: 59 and 60
- D/C: 69 and 70
- C/B: 79 and 80
- B/A: 89 and 90
- Overall lower boundary: 0
- Overall upper boundary: 100

Invalid values below 0 and above 100 were also included.

| Test Value | Expected Grade |
| :--------: | :------------- |
|   **-1**   | INVALID        |
|   **0**    | F              |
|   **1**    | F              |
|   **59**   | F              |
|   **60**   | D              |
|   **61**   | D              |
|   **69**   | D              |
|   **70**   | C              |
|   **71**   | C              |
|   **79**   | C              |
|   **80**   | B              |
|   **81**   | B              |
|   **89**   | B              |
|   **90**   | A              |
|   **91**   | A              |
|   **99**   | A              |
|  **100**   | A              |
|  **101**   | INVALID        |

The BVA tests verify that the GradeBook correctly handles values at and around every defined grade boundary.

---

### 7.3 BVA — Number of Scores per Student

The valid score-count range is 1–6 scores.

The following boundary values were selected:

| Score Count | Expected Result |
| :---------: | :-------------- |
|    **0**    | Reject          |
|    **1**    | Accept          |
|    **2**    | Accept          |
|    **5**    | Accept          |
|    **6**    | Accept          |
|    **7**    | Reject          |

These values test the lower boundary, upper boundary, and values immediately surrounding the valid range.

During execution, the test for exactly 6 scores initially failed.

#### Defect Found

The original validation condition in `Roster.java` was:

```java
if (scoreCount < 1 || scoreCount >= 6)
```

This incorrectly rejected a student with exactly 6 scores.  
The expected rule is:

```java
1 <= scoreCount <= 6
```

The defect was reported in **GitHub Issue #32**: _Bug: Roster rejects valid maximum of 6 scores_.

The defect was fixed by changing the condition to:

```java
if (scoreCount < 1 || scoreCount > 6)
```

After the fix, the boundary test for 6 scores passed successfully.

---

### 7.4 BVA — Student Name Length

The student-name rule requires:

```
1 <= name length <= 50
```

The following boundary values were tested:

| Name Length | Expected Result |
| :---------: | :-------------- |
|    **0**    | Reject          |
|    **1**    | Accept          |
|   **49**    | Accept          |
|   **50**    | Accept          |
|   **51**    | Reject          |

These tests verify both the minimum and maximum valid name-length boundaries.  
The BVA tests confirmed that a name of exactly 50 characters is accepted, while a name exceeding the maximum length is rejected.

---

## 8. BVA Test Implementation

Dedicated JUnit BVA test classes were added to the project:

- `LetterGradeBVATest.java`
- `RosterBVATest.java`
- `NameLengthBVATest.java`

The tests use parameterized test cases to execute multiple boundary values against the same test method. This approach reduces duplicated test code while providing coverage for the selected boundary values.

---

## 9. BVA Defect Detection and Fix

The Roster BVA tests successfully detected an off-by-one defect at the upper boundary.

- **Defect:** A student with exactly 6 scores was incorrectly rejected.
- **GitHub Issue:** Issue #32 — _Bug: Roster rejects valid maximum of 6 scores_
- **Fix Branch:** `bug/bva-roster-six-score-boundary`
- **Pull Request:** PR #33 — _fix(roster): accept six scores as valid_

The fix changed the upper-bound validation from `>= 6` to `> 6`. The pull request was merged after the corrected tests passed.

---

## 10. Test Execution Results

After implementing the BVA tests and fixing the detected Roster boundary defect, the complete JUnit test suite was executed.

### Final Test Result

```
46/46 tests passed
```

The VS Code Test Explorer confirmed that all tests passed successfully after the fix.

| Test Area                   | Result           |
| :-------------------------- | :--------------- |
| **Letter Grade BVA**        | PASS             |
| **Roster Score-Count BVA**  | PASS             |
| **Student Name-Length BVA** | PASS             |
| **Full Test Suite**         | **PASS — 46/46** |

---

## 11. Overall Analysis

Equivalence Partitioning provided representative coverage for the valid and invalid input classes of the GradeBook system.

Boundary Value Analysis provided additional coverage around the edges of those classes and successfully detected an off-by-one defect in the Roster score-count validation.

The defect demonstrated the value of BVA because the representative EP value of 3 scores passed successfully, while testing the exact upper boundary of 6 scores exposed the incorrect validation condition.

After correcting the defect, the complete test suite passed with **46/46 tests passing**. Therefore, the combined EP and BVA approach provided stronger coverage than either technique alone.
