# **Ex3 — OCP Refactor: Placement Eligibility Engine**

## **1. Overview**

This exercise focuses on evolving a rigid **Placement Eligibility System** into an extensible framework by applying the **Open/Closed Principle (OCP)** and **Dependency Inversion Principle (DIP)**. The system evaluates a student's eligibility for placement based on academic, attendance, and disciplinary criteria.

---

## **2. Problem in the Original Design**

In the initial implementation, the eligibility logic was likely contained within a single engine that handled every business rule directly.

* **OCP Violation:** Adding a new eligibility requirement (e.g., "Paid Fees") would require modifying the core engine's source code.
* **Tight Coupling:** The engine was directly responsible for calculating logic and managing configurations.
* **Maintenance Risk:** Hard-coded `if-else` chains make the code difficult to read and risky to modify as the number of rules grows.

This violated the **Open/Closed Principle**, which states:

> *Software entities should be open for extension, but closed for modification.*

---

## **3. Refactoring Objective**

The goal of the refactor was:
✔ **Abstract the rules** so the engine doesn't need to know the specific details of each check.

✔ **Enable "Plug-and-Play"** functionality for new eligibility criteria.

✔ **Invert dependencies** so the engine depends on an abstraction (`EligibilityRule`), not concrete implementations.

✔ **Preserve exact program output** while improving internal architecture.

---

## **4. Refactored Design**

The system was decomposed into a modular, interface-driven architecture:

### **EligibilityRule (Interface)**

**Responsibility:** Defines the standard contract for any eligibility check.

* Provides the `apply` method that all specific rules must implement.

---

### **Concrete Rules (CGRCheck, AttendanceCheck, etc.)**

**Responsibility:** Each class encapsulates exactly one business requirement.

* **CGRCheck:** Validates the student's CGR against the configured threshold.
* **AttendanceCheck:** Validates the student's attendance percentage.
* **CreditsCheck:** Ensures the student has earned the minimum required credits.
* **DisciplinaryCheck:** Checks for any disciplinary flags.

---

### **EligibilityEngine**

**Responsibility:** Coordinates the execution of the rules.

* **Rule Agnostic:** It iterates through a `List<EligibilityRule>` without knowing what the specific rules are.
* **Process Management:** It aggregates reasons for ineligibility and handles the final status and storage.

---

### **RuleInput (Config)**

**Responsibility:** Acts as a data carrier for threshold parameters (e.g., `minCgr`, `minAttendance`).

* Allows rules to remain flexible by reading values from a central configuration.

---

### **FakeEligibilityStore**

**Responsibility:** Handles the persistence of the evaluation result.

* Separates the storage action from the evaluation logic.

---

## **5. Key Improvements**

### **Open/Closed Compliance**

The engine is now closed for modification. You can add a "SeniorYearRule" or "InternshipRule" simply by creating a new class and adding it to the list in `Main.java`.

### **Single Responsibility Compliance**

* **Rules:** Only handle specific logic checks.
* **Engine:** Only handles the orchestration of the evaluation.
* **Printer:** Only handles the formatting of the final report.

### **Dependency Inversion**

`EligibilityEngine` no longer depends on concrete classes like `CGRCheck`. It depends on the `EligibilityRule` abstraction, making it highly modular.

### **Better Testability**

Individual rules can now be tested in isolation by passing mock `StudentProfile` and `RuleInput` objects to their `apply` methods.

---

## **6. OCP Justification**

The refactor adheres to OCP because:
✔ The engine's `evaluate()` method is generic and does not change when rules change.

✔ New features are added by **adding new classes**, not by **altering the core engine**.

---

## **7. Behavioral Integrity**

Despite the structural changes:
✔ The program output remains consistent with the original requirements.

✔ The internal data structures for `StudentProfile` and `RuleInput` are preserved to maintain compatibility.

---

## **8. Resulting Benefits**

* **Scalability:** Easily accommodates an increasing number of eligibility criteria.
* **Maintainability:** Rules are isolated in their own files, reducing the risk of side effects.
* **Flexibility:** Different sets of rules can be injected into the engine for different departments or programs.

---

## **9. Conclusion**

This refactor transforms a monolithic evaluation script into a **modular rules engine**. By using the Strategy Pattern and OCP, the system is now resilient to change and prepared for future extensions.

**Would you like me to analyze your next exercise or refine this documentation further?**