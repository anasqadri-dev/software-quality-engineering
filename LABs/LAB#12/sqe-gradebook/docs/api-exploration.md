# Gradebook REST API — Manual Exploration

## 1. Overview
The Gradebook REST API was manually tested using PowerShell HTTP requests. The purpose was to verify student registration, score recording, grade calculation, grade-scale lookup, and error handling.

**Base URL:** `http://localhost:8080`

## 2. Positive API Testing
| Endpoint | Method | Test Description | Expected Status | Result |
|---|---|---|---:|---|
| `/students` | POST | Register a student | 201 | Pass |
| `/students` | GET | Retrieve all students | 200 | Pass |
| `/students/S1001` | GET | Retrieve one student | 200 | Pass |
| `/students/S1001/scores` | POST | Record assessment scores | 201 | Pass |
| `/students/S1001/grade` | GET | Retrieve average and letter grade | 200 | Pass |
| `/grade-scale?score=90` | GET | Look up letter grade | 200 | Pass |

### Representative request and response examples

The following examples document the API contract. The response bodies show the expected JSON structure for the tested scenarios.

**1. Register a student**

Request:
```http
POST /students
Content-Type: application/json

{
  "studentId": "S1001",
  "name": "Amina Khan"
}
```

Expected response — HTTP 201:
```json
{
  "studentId": "S1001",
  "name": "Amina Khan",
  "average": 0
}
```

**2. Record a score**

Request:
```http
POST /students/S1001/scores
Content-Type: application/json

{
  "score": 85
}
```

Expected response — HTTP 201:
```json
{
  "studentId": "S1001",
  "score": 85,
  "average": 85
}
```

**3. Retrieve the student's grade**

After recording scores of 85 and 90, request:
```http
GET /students/S1001/grade
```

Expected response — HTTP 200:
```json
{
  "studentId": "S1001",
  "average": 88,
  "letterGrade": "B"
}
```

**4. Look up a grade-scale value**

Request:
```http
GET /grade-scale?score=90
```

Expected response — HTTP 200:
```json
{
  "score": 90,
  "letterGrade": "A"
}
```

**5. Reject an invalid score**

Request:
```http
POST /students/S1001/scores
Content-Type: application/json

{
  "score": 101
}
```

Expected response — HTTP 400:
```json
{
  "error": "score must be between 0 and 100"
}
```

### Observed Grade Calculation
Student S1001 received scores of 85 and 90.
- Calculated average: 88
- Letter grade: B

The average was rounded to the nearest whole number.

## 3. Negative API Testing
| Test ID | Test Scenario | Expected Status | Actual Status | Result |
|---|---|---:|---:|---|
| N1 | Retrieve unknown student | 404 | 404 | Pass |
| N2 | Register student with invalid ID | 400 | 400 | Pass |
| N3 | Register student without name | 400 | 400 | Pass |
| N4 | Register duplicate student ID | 409 | 409 | Pass |
| N5 | Record scores outside 0–100 | 400 | 400 | Pass |
| N6 | Record more than five scores | 400 | 400 | Pass |
| N7 | Retrieve grade without scores | 400 | 400 | Pass |
| N9 | Test grade-scale boundaries | 200 / 400 | As expected | Pass |

## 4. Grade Scale Boundary Results

| Score | Expected Grade | Actual Grade |
|---:|---|---|
| 0 | F | F |
| 59 | F | F |
| 60 | D | D |
| 69 | D | D |
| 70 | C | C |
| 79 | C | C |
| 80 | B | B |
| 89 | B | B |
| 90 | A | A |
| 100 | A | A |

Invalid values -1 and 101 returned HTTP 400.

## 5. Query Parameter Validation

| Test Scenario | Expected Status | Actual Status |
|---|---:|---:|
| Missing score parameter | 400 | 400 |
| Nonnumeric score parameter | 400 | 400 |

## 6. Observations
1. Student registration successfully returned HTTP 201.
2. The API correctly rejected duplicate student IDs.
3. Scores outside the permitted range were rejected.
4. The API allowed a maximum of five assessment scores per student.
5. Average calculations and letter-grade classifications matched the expected results.
6. Requests for unknown students returned HTTP 404.
7. Requests for grades without recorded scores returned HTTP 400.
8. **Initial defect (before the fix):** the student response included an extra `scores` property. This violated the intended JSON response contract and was identified by the schema validation task. The property was subsequently excluded using `@JsonIgnore`; the corrected response is validated by the automated schema test.

## 7. Conclusion
Manual exploration confirmed that the implemented Gradebook REST API successfully handles the tested positive and negative scenarios. The observed responses matched the expected HTTP status codes and grade calculations. Automated API testing and JSON Schema validation provide further verification.

## JSON Schema Validation and Performance Testing

### JSON Schema Validation

A JSON Schema was created to validate the structure of the `GET /students` response.

The schema verifies that:
- The response is an array of student objects.
- Each student contains `studentId`, `name`, and `average`.
- `studentId` follows the format `S` followed by four digits.
- `average` is an integer between 0 and 100.
- No additional properties are allowed.

During the initial schema test, validation failed because the student response included an extra `scores` property. This violated the API response contract.

To resolve this issue, the `@JsonIgnore` annotation was added to the `getScores()` method in `Student.java`. This prevents the internal scores list from appearing in the JSON response.

After the correction, the schema validation test passed successfully.

### Response-Time Testing

A performance test was added to measure the response time of `GET /students`.

The test performs a warm-up request and then measures a second request. The response must be successful and complete in less than 1000 milliseconds.

**Previously observed response time:** 12 ms

**Result:** Passed — the recorded response time was below the 1000 ms threshold. Response times can vary with machine load and environment.
