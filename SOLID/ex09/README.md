# Assignment Evaluation Pipeline – SOLID (DIP Refactoring)

## Overview

This project demonstrates refactoring an Assignment Evaluation Pipeline to comply with the **Dependency Inversion Principle (DIP)** from the SOLID design principles.

The original implementation tightly coupled the high-level evaluation pipeline to concrete low-level classes such as `CodeGrader`, `PlagiarismChecker`, and `ReportWriter`.

This refactoring introduces **small abstractions and constructor-based dependency injection**, making the system extensible, testable, and maintainable.

---

# Objective

Refactor the evaluation system to:

* Remove hard-coded instantiation of concrete classes
* Introduce minimal abstractions
* Inject dependencies into the pipeline
* Preserve console output and order
* Enable multiple grading strategies without modifying pipeline logic

---

# Problem in Original Design

Original `EvaluationPipeline` directly instantiated its dependencies:

```java
Rubric rubric = new Rubric();
PlagiarismChecker pc = new PlagiarismChecker();
CodeGrader grader = new CodeGrader();
ReportWriter writer = new ReportWriter();
```

## Issues

1. High-level module depends directly on low-level implementations
2. Hard to test (cannot inject mocks)
3. Changing grading logic requires editing pipeline code
4. No abstraction boundaries
5. Configuration tightly embedded

This violates the **Dependency Inversion Principle**:

> High-level modules should not depend on low-level modules.
> Both should depend on abstractions.

---

# Refactored Architecture

We introduced minimal interfaces for each dependency.

---

## PlagService

```java
public interface PlagService {
    int check(Submission s);
}
```

---

## GradingService

```java
public interface GradingService {
    int grade(Submission s);
}
```

---

## ReportService

```java
public interface ReportService {
    String write(Submission s, int plag, int code);
}
```

---

# Updated Pipeline (Now DIP-Compliant)

```java
public class EvaluationPipeline {

    private final PlagService plagiarismService;
    private final GradingService gradingService;
    private final ReportService reportService;

    public EvaluationPipeline(
            PlagService plagiarismService,
            GradingService gradingService,
            ReportService reportService) {

        this.plagiarismService = plagiarismService;
        this.gradingService = gradingService;
        this.reportService = reportService;
    }

    public void evaluate(Submission sub) {

        int plag = PlagService.check(sub);
        System.out.println("PlagiarismScore=" + plag);

        int code = gradingService.grade(sub);
        System.out.println("CodeScore=" + code);

        String reportName = reportService.write(sub, plag, code);
        System.out.println("Report written: " + reportName);

        int total = plag + code;
        String result = (total >= 90) ? "PASS" : "FAIL";
        System.out.println("FINAL: " + result + " (total=" + total + ")");
    }
}
```

The pipeline now depends only on **abstractions**, not concrete classes.

---

# Strategy Pattern Emergence

Because the pipeline depends on `GradingService`, we can now add multiple grading strategies.

## Example: Default Strategy

```java
public class CodeGrader implements GradingService {
    private final Rubric rubric;

    public CodeGrader(Rubric rubric) {
        this.rubric = rubric;
    }

    @Override
    public int grade(Submission s) {
        int base = Math.min(80, 50 + s.code.length() % 40);
        return base + rubric.bonus;
    }
}
```

## Example: Second Strategy (Lenient Grader)

```java
public class LenientCodeGrader implements GradingService {

    @Override
    public int grade(Submission s) {
        return 85 + (s.code.length() % 10);
    }
}
```

No changes were made to `EvaluationPipeline` to support this.

---

# Sample Output (Preserved)

```text
=== Evaluation Pipeline ===
PlagiarismScore=12
CodeScore=78
Report written: report-23BCS1007.txt
FINAL: PASS (total=90)
```

✔ Output preserved
✔ Line order unchanged

---

# Architectural Improvements

* Eliminated tight coupling
* Enabled easy unit testing (mock services)
* Introduced constructor-based dependency injection
* Enabled multiple grading strategies
* Improved flexibility and maintainability

---

# Testability Example

Because of abstractions, you can now easily inject a test double:

```java
GradingService mockGrader = s -> 100;
```

No need to run real grading logic during testing.

---

# How to Run

```bash
cd SOLID/Ex9/src
javac *.java
java Main
```
