# GradeBook Test Plan

## Functional Requirements

| Requirement ID | Requirement |
|---|---|
| REQ-1 | The system shall allow a valid score between 0 and 100 to be added to a student. |
| REQ-2 | The system shall reject scores below 0 or above 100. |
| REQ-3 | The system shall calculate the average of all recorded scores correctly. |
| REQ-4 | The system shall handle a student with no recorded scores without crashing. |
| REQ-5 | The system shall prevent duplicate student IDs. |
| REQ-6 | The system shall compare student names without treating uppercase and lowercase letters as different. |
| REQ-7 | The system shall correctly handle the minimum score boundary of 0 and the maximum score boundary of 100. |
| REQ-8 | The system shall convert numeric scores to the appropriate grade letter, including scores at grade boundaries. |