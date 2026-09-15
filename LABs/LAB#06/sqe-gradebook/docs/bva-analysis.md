# Boundary Value Analysis — GradeBook

## 1. Introduction

This document contains the **Boundary Value Analysis (BVA)** for the GradeBook system.

Boundary Value Analysis focuses on values at and immediately around the boundaries of equivalence classes. It is used together with **Equivalence Partitioning (EP)** to detect defects such as off-by-one errors and incorrect boundary conditions.

The following GradeBook inputs are analyzed:

1. Numeric score used by `letterGrade(score)`
2. Number of scores handled by `Roster.add_student(student)`
3. Student name length handled by `validateName(name)`

---

# 2. BVA for `letterGrade(score)`

## 2.1 Score Domain

The valid score domain is:

```text
0–100
```

The letter-grade cut-offs are:

```text
60 → D
70 → C
80 → B
90 → A
```

Therefore, the important boundaries are:

```text
0
60
70
80
90
100
```

For each boundary, the value immediately below, the boundary itself, and the value immediately above are tested.

---

## 2.2 Score Domain Lower Boundary — 0

| Boundary | Value - 1 | Expected | Boundary Value | Expected | Value + 1 | Expected |
| -------- | --------: | -------- | -------------: | -------- | --------: | -------- |
| 0        |        -1 | Reject   |              0 | F        |         1 | F        |

### Explanation

- `-1` is outside the valid score domain and must be rejected.
- `0` is the minimum valid score and should receive `F`.
- `1` is just above the minimum and should also receive `F`.

---

## 2.3 F/D Boundary — 60

| Boundary | Value - 1 | Expected | Boundary Value | Expected | Value + 1 | Expected |
| -------- | --------: | -------- | -------------: | -------- | --------: | -------- |
| 60       |        59 | F        |             60 | D        |        61 | D        |

### Explanation

- `59` belongs to the F class.
- `60` is the lowest score that receives D.
- `61` remains within the D class.

---

## 2.4 D/C Boundary — 70

| Boundary | Value - 1 | Expected | Boundary Value | Expected | Value + 1 | Expected |
| -------- | --------: | -------- | -------------: | -------- | --------: | -------- |
| 70       |        69 | D        |             70 | C        |        71 | C        |

### Explanation

- `69` belongs to the D class.
- `70` is the lowest score that receives C.
- `71` remains within the C class.

---

## 2.5 C/B Boundary — 80

| Boundary | Value - 1 | Expected | Boundary Value | Expected | Value + 1 | Expected |
| -------- | --------: | -------- | -------------: | -------- | --------: | -------- |
| 80       |        79 | C        |             80 | B        |        81 | B        |

### Explanation

- `79` belongs to the C class.
- `80` is the lowest score that receives B.
- `81` remains within the B class.

---

## 2.6 B/A Boundary — 90

| Boundary | Value - 1 | Expected | Boundary Value | Expected | Value + 1 | Expected |
| -------- | --------: | -------- | -------------: | -------- | --------: | -------- |
| 90       |        89 | B        |             90 | A        |        91 | A        |

### Explanation

- `89` belongs to the B class.
- `90` is the lowest score that receives A.
- `91` remains within the A class.

---

## 2.7 Score Domain Upper Boundary — 100

| Boundary | Value - 1 | Expected | Boundary Value | Expected | Value + 1 | Expected |
| -------- | --------: | -------- | -------------: | -------- | --------: | -------- |
| 100      |        99 | A        |            100 | A        |       101 | Reject   |

### Explanation

- `99` is within the A class.
- `100` is the maximum valid score.
- `101` is outside the valid score domain and must be rejected.

---

## 2.8 Complete `letterGrade()` Boundary Set

| Boundary | Value - 1 | Expected | Boundary Value | Expected | Value + 1 | Expected |
| -------- | --------: | -------- | -------------: | -------- | --------: | -------- |
| 0        |        -1 | Reject   |              0 | F        |         1 | F        |
| 60       |        59 | F        |             60 | D        |        61 | D        |
| 70       |        69 | D        |             70 | C        |        71 | C        |
| 80       |        79 | C        |             80 | B        |        81 | B        |
| 90       |        89 | B        |             90 | A        |        91 | A        |
| 100      |        99 | A        |            100 | A        |       101 | Reject   |

---

# 3. BVA for `Roster.add_student(student)`

## 3.1 Score-Count Rule

According to the Lab 5 business rule, a student must have:

```text
1–6 scores
```

Therefore:

- `0` scores → Invalid
- `1–6` scores → Valid
- `7+` scores → Invalid

The important boundaries are:

```text
1
6
```

---

## 3.2 Minimum Score Count Boundary — 1

| Boundary | Value - 1 | Expected | Boundary Value | Expected | Value + 1 | Expected |
| -------- | --------: | -------- | -------------: | -------- | --------: | -------- |
| 1 score  |         0 | Reject   |              1 | Accept   |         2 | Accept   |

### Explanation

- `0` scores are below the minimum required number of scores.
- `1` score is the minimum valid number.
- `2` scores are still within the valid range.

---

## 3.3 Maximum Score Count Boundary — 6

| Boundary | Value - 1 | Expected | Boundary Value | Expected | Value + 1 | Expected |
| -------- | --------: | -------- | -------------: | -------- | --------: | -------- |
| 6 scores |         5 | Accept   |              6 | Accept   |         7 | Reject   |

### Explanation

- `5` scores are within the valid range.
- `6` scores are the maximum allowed.
- `7` scores exceed the allowed limit.

---

## 3.4 Complete `Roster.add_student()` Boundary Set

| Boundary | Value - 1 | Expected | Boundary Value | Expected | Value + 1 | Expected |
| -------- | --------: | -------- | -------------: | -------- | --------: | -------- |
| 1        |         0 | Reject   |              1 | Accept   |         2 | Accept   |
| 6        |         5 | Accept   |              6 | Accept   |         7 | Reject   |

---

# 4. BVA for `validateName(name)`

## 4.1 Name-Length Rule

A valid student name must:

- Contain at least 1 character.
- Contain no more than 50 characters.
- Contain only letters, spaces, and hyphens.

Therefore, the important length boundaries are:

```text
0
50
```

---

## 4.2 Minimum Name-Length Boundary — 0

| Boundary     | Value - 1 | Expected | Boundary Value | Expected | Value + 1 | Expected |
| ------------ | --------- | -------- | -------------: | -------- | --------: | -------- |
| 0 characters | N/A       | N/A      |              0 | Reject   |         1 | Accept   |

### Explanation

A string cannot have a negative length, so there is no meaningful `-1` character input.

The minimum practical boundary is therefore:

- `0` characters → Reject
- `1` character → Accept

---

## 4.3 Maximum Name-Length Boundary — 50

| Boundary      | Value - 1 | Expected | Boundary Value | Expected | Value + 1 | Expected |
| ------------- | --------: | -------- | -------------: | -------- | --------: | -------- |
| 50 characters |        49 | Accept   |             50 | Accept   |        51 | Reject   |

### Explanation

- A 49-character valid name is within the allowed range.
- A 50-character valid name is exactly at the maximum.
- A 51-character name exceeds the maximum and must be rejected.

---

## 4.4 Complete `validateName()` Boundary Set

| Boundary | Value - 1 | Expected | Boundary Value | Expected | Value + 1 | Expected |
| -------- | --------- | -------- | -------------: | -------- | --------: | -------- |
| 0        | N/A       | N/A      |              0 | Reject   |         1 | Accept   |
| 50       | 49        | Accept   |             50 | Accept   |        51 | Reject   |

---

# 5. Combined BVA Summary

| Function                      | Boundary Values          | Important BVA Values                                                     |
| ----------------------------- | ------------------------ | ------------------------------------------------------------------------ |
| `letterGrade(score)`          | `0, 60, 70, 80, 90, 100` | `-1, 0, 1, 59, 60, 61, 69, 70, 71, 79, 80, 81, 89, 90, 91, 99, 100, 101` |
| `Roster.add_student(student)` | `1, 6` scores            | `0, 1, 2, 5, 6, 7`                                                       |
| `validateName(name)`          | `0, 50` characters       | `0, 1, 49, 50, 51`                                                       |

---

# 6. BVA and Equivalence Partitioning

Equivalence Partitioning from Lab 5 selected representative values from each class. Boundary Value Analysis complements EP by specifically testing values at the edges of those classes.

For example, the score classes around the A/B boundary are:

```text
80–89 → B
90–100 → A
```

Equivalence Partitioning (EP) may select representative values such as:

```text
85 → B
95 → A
```

Boundary Value Analysis (BVA) additionally tests:

```text
89 → B
90 → A
91 → A
```

This makes it possible to detect off-by-one errors that may not be detected by representative-value testing alone.

---

# 7. BVA Limitation

Boundary Value Analysis focuses on values around known boundaries, but it does not replace other testing techniques.

It should be combined with:

- Equivalence Partitioning
- Decision Table Testing
- State Transition Testing
- Error Guessing
- Other appropriate software testing techniques

Using multiple testing techniques provides broader test coverage and increases the likelihood of detecting defects.

---

# 8. Basis for Selected Boundaries

The Lab 06 manual explicitly identifies the important score boundaries as:

```text
0 / 100
59 / 60
69 / 70
79 / 80
89 / 90
```

The BVA principle is to test:

```text
value - 1
value
value + 1
```

For the score-domain edges, the required values include:

```text
-1, 0, 1
99, 100, 101
```

For the `Roster.add_student(student)` rule, the valid range is:

```text
1–6 scores
```

Therefore, the BVA values are:

```text
0, 1, 2
5, 6, 7
```

For student names, the maximum allowed length is:

```text
50 characters
```

Therefore, the relevant BVA values are:

```text
0, 1, 49, 50, 51
```

---

# 9. Final BVA Test Values

The complete set of Boundary Value Analysis test values for the GradeBook system is summarized below.

## `letterGrade(score)`

```text
-1, 0, 1
59, 60, 61
69, 70, 71
79, 80, 81
89, 90, 91
99, 100, 101
```

## `Roster.add_student(student)`

```text
0, 1, 2
5, 6, 7
```

## `validateName(name)`

```text
0, 1, 49, 50, 51
```

These values provide focused coverage of the lower and upper boundaries of the relevant equivalence classes.
