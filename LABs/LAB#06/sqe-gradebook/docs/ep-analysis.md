# Equivalence Partitioning Analysis — GradeBook

## 1. Introduction

This document contains the Equivalence Partitioning (EP) analysis for the GradeBook system.

Equivalence Partitioning divides an input domain into classes where the system is expected to behave similarly. One representative value is selected from each class to reduce redundant testing while maintaining useful defect-detection coverage.

---

## 2. Input 1 — Student Score

The score domain is divided into valid and invalid equivalence classes.

| Class ID | Equivalence Class | Input Range | Representative Value | Expected Result   |
| -------- | ----------------- | ----------- | -------------------: | ----------------- |
| SC-1     | Invalid-low       | score < 0   |                  -10 | Reject with error |
| SC-2     | F grade           | 0–59        |                   45 | Return F          |
| SC-3     | D grade           | 60–69       |                   65 | Return D          |
| SC-4     | C grade           | 70–79       |                   75 | Return C          |
| SC-5     | B grade           | 80–89       |                   85 | Return B          |
| SC-6     | A grade           | 90–100      |                   95 | Return A          |
| SC-7     | Invalid-high      | score > 100 |                  150 | Reject with error |

---

## 3. Input 2 — Number of Scores per Student

The business rule requires a student to have between 1 and 6 scores.

| Class ID | Equivalence Class | Input Range      | Representative Value | Expected Result   |
| -------- | ----------------- | ---------------- | -------------------: | ----------------- |
| NS-1     | Invalid-low       | 0 scores         |                    0 | Reject with error |
| NS-2     | Valid             | 1–6 scores       |                    3 | Accept            |
| NS-3     | Invalid-high      | 7 or more scores |                    8 | Reject with error |

---

## 4. Input 3 — Student Name

The student name must be a non-empty string with a maximum length of 50 characters and may contain only letters, spaces, and hyphens.

| Class ID | Equivalence Class  | Input Example           | Expected Result   |
| -------- | ------------------ | ----------------------- | ----------------- |
| NM-1     | Valid typical name | Ali Khan                | Accept            |
| NM-2     | Empty string       | ""                      | Reject with error |
| NM-3     | Over-length name   | More than 50 characters | Reject with error |
| NM-4     | Digits or symbols  | Ali123                  | Reject with error |

---

## 5. EP Test Representatives

The selected representatives are:

### Score

- -10 -> Invalid-low
- 45 -> F
- 65 -> D
- 75 -> C
- 85 -> B
- 95 -> A
- 150 -> Invalid-high

### Number of Scores

- 0 -> Invalid-low
- 3 -> Valid
- 8 -> Invalid-high

### Student Name

- "Ali Khan" -> Valid
- "" -> Empty
- More than 50 characters -> Over-length
- "Ali123" -> Contains digits/symbols

---

## 6. Limitation of Equivalence Partitioning

Equivalence Partitioning does not focus on exact boundary values. Therefore, it can miss off-by-one defects at the edges of equivalence classes.

For example, score classes contain boundaries such as 59/60, 69/70, 79/80, 89/90, and 100. EP selects a representative from each class but does not test every boundary.

Boundary Value Analysis will be used in a later lab to specifically target these edge values.

---

## 7. Full EP Test Execution Summary

The complete JUnit test suite was executed after implementing the score-count and name-validation functionality.

### Test Summary

| Test Area | Result |
|---|---|
| Letter Grade EP Tests | PASS — 7/7 |
| Score Count EP Tests | PASS — 3/3 |
| Name Validation EP Tests | PASS — 4/4 |
| Full Test Suite | PASS |

### Name Validation Results

| Class | Representative | Result |
|---|---|---|
| Valid typical name | "Ali Khan" | PASS |
| Empty string | "" | PASS |
| Over-length name | 51 characters | PASS |
| Digits/symbols | "Ali123" | PASS |

### Overall Result

All implemented Equivalence Partitioning tests passed successfully. The test suite covers the defined equivalence classes for letter grades, score counts, and student names.

> Note: Equivalence Partitioning is not intended to replace Boundary Value Analysis. Boundary values such as the exact 50-character name limit and score/grade boundaries require additional boundary-focused testing.