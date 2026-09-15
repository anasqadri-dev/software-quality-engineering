# Requirements Traceability Matrix (RTM)

## Purpose

This Requirements Traceability Matrix maps each GradeBook functional requirement to the test cases that verify it. The matrix ensures that every requirement has at least one corresponding test case and helps identify any gaps in test coverage.

| Requirement ID | Requirement | Linked Test Case IDs | Coverage Status |
|---|---|---|---|
| REQ-1 | The system shall accept valid scores between 0 and 100. | TC-001, TC-009, TC-010 | Covered |
| REQ-2 | The system shall reject negative scores. | TC-002 | Covered |
| REQ-3 | The system shall reject invalid or non-numeric score input. | TC-003 | Covered |
| REQ-4 | The system shall calculate the average correctly when one or more scores are present. | TC-004, TC-006 | Covered |
| REQ-5 | The system shall handle a student with no scores without crashing. | TC-005 | Covered |
| REQ-6 | Student IDs shall be unique and duplicate IDs shall be rejected. | TC-007 | Covered |
| REQ-7 | Student name comparisons shall be case-insensitive. | TC-008 | Covered |
| REQ-8 | The system shall convert numeric scores into the correct grade letters, including boundary values. | TC-011, TC-012 | Covered |

## Coverage Summary

- Total requirements: **8**
- Requirements with linked test cases: **8**
- Requirements with zero linked test cases: **0**
- Coverage: **100%**

## Gap Analysis

No requirements currently have zero linked test cases. All eight requirements have at least one corresponding test case in `docs/test-cases.md`.

The RTM was reviewed against the 12 planned test cases to ensure that the requirements are traceable to executable verification activities. The boundary, negative/error-path, and normal functional scenarios are represented in the matrix.