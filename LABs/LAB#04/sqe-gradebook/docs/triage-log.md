# Triage Log

## Purpose

This triage meeting ranks the five reported GradeBook defects according to their impact, urgency, and priority. The goal is to decide which defects should be fixed first and which defects can be deferred from the current sprint.

## Issue Ranking

| Rank | Issue | Defect                                                  | Severity | Priority | Sprint Decision       |
| ---- | ----- | ------------------------------------------------------- | -------- | -------- | --------------------- |
| 1    | #24   | Negative scores can be accepted                         | High     | P1       | Fix this sprint       |
| 2    | #25   | Duplicate student IDs are allowed                       | Medium   | P1       | Fix this sprint       |
| 3    | #23   | Average calculation crashes for students with no scores | Medium   | P1       | Fix this sprint       |
| 4    | #26   | Average calculation rounds incorrectly                  | Medium   | P2       | Won't fix this sprint |
| 5    | #27   | Student name comparison is case-sensitive               | Low      | P2       | Won't fix this sprint |

## Triage Reasoning

### 1. Issue #24 — Negative Scores Can Be Accepted

**Severity:** High  
**Priority:** P1

This defect can allow invalid academic data to enter the GradeBook. Since scores below 0 are outside the valid score range, accepting them can directly affect the correctness of student grades. It is therefore the highest-ranked defect and should be fixed immediately.

### 2. Issue #25 — Duplicate Student IDs Are Allowed

**Severity:** Medium  
**Priority:** P1

Duplicate student IDs create a data-integrity problem because an ID is expected to uniquely identify a student. Although the system may continue running, duplicate identifiers can cause scores or grade information to be associated with the wrong student. It is therefore treated as a P1 issue despite having medium severity.

### 3. Issue #23 — Average Calculation Crashes for Students With No Scores

**Severity:** Medium  
**Priority:** P1

This defect causes the average calculation to fail when a student has no recorded scores. It affects an important GradeBook operation and can prevent users from obtaining grade statistics. Its P1 priority reflects the urgency of fixing a failure in grade calculation.

### 4. Issue #26 — Average Calculation Rounds Incorrectly

**Severity:** Medium  
**Priority:** P2

This defect produces inaccurate displayed averages but does not prevent the GradeBook from continuing to operate. Because the impact is lower than invalid data acceptance, duplicate identifiers, and calculation failures, it is deferred from the current sprint.

### 5. Issue #27 — Student Name Comparison Is Case-Sensitive

**Severity:** Low  
**Priority:** P2

This issue mainly affects usability because searches can fail when the capitalization of a student's name differs. It does not corrupt data or prevent the main GradeBook functionality from working, so it has the lowest urgency among the five defects.

## Severity vs Priority Trade-offs

### Issue #25 — Duplicate Student IDs

Issue #25 has **Medium severity but P1 priority**. The severity is medium because the application does not necessarily crash when duplicate IDs are created. However, the priority is P1 because student identification is fundamental to a GradeBook and duplicate identifiers can lead to incorrect student records or grade information. This means the issue should be fixed urgently even though its severity is not classified as High.

### Issue #23 — Average Calculation Crash

Issue #23 has **Medium severity but P1 priority**. Its severity is medium because the failure occurs specifically when a student has no scores rather than affecting every GradeBook operation. However, the priority is P1 because average calculation is an important grade-management function and a failure during this operation directly affects users trying to obtain grade statistics.

### Issue #26 — Incorrect Average Rounding

Issue #26 has **Medium severity but P2 priority**. Although incorrect rounding produces inaccurate grade information, the GradeBook remains usable and the calculation still produces an average. Therefore, it is important to fix but can reasonably be deferred until higher-priority defects are addressed.

### Issue #27 — Case-Sensitive Name Comparison

Issue #27 has **Low severity and P2 priority** because it mainly affects search convenience and does not cause data loss, corruption, or application failure.

## Sprint Decision

The following issues will be fixed during the current sprint:

- #24 — Negative scores can be accepted
- #25 — Duplicate student IDs are allowed
- #23 — Average calculation crashes for students with no scores

The following issues will **not** be fixed during this sprint:

- #26 — Average calculation rounds incorrectly
- #27 — Student name comparison is case-sensitive

The two deferred issues are lower urgency compared with the defects that can introduce invalid data, compromise student identification, or prevent important GradeBook calculations from working.
