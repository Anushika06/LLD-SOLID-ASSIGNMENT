# **SST28-LLD101 — SOLID Refactoring Exercises**

This repository contains refactored solutions for the **SOLID Refactoring Practice** exercises of LLD101.

The focus of this work is **behavior-preserving refactoring** using only **core OOP principles**, without introducing design patterns unless explicitly required.

---

## **Objective**

The purpose of this repository is to demonstrate:

- Practical application of **SOLID principles**
- Identification of design smells
- Safe, incremental refactoring
- Separation of concerns
- Improved maintainability and extensibility

All refactors strictly preserve:

✔ Program behavior  
✔ Console output  
✔ Public APIs  
✔ Constraints defined in each exercise  

---

## **Approach**

Each exercise follows a consistent workflow:

1. Run the original program  
2. Observe and capture output  
3. Identify responsibility violations  
4. Refactor using OOP mechanisms:
   - Classes
   - Interfaces
   - Composition
   - Polymorphism
   - Encapsulation
5. Re-run to verify identical behavior  

No external libraries or frameworks were used.

---

## **SOLID Principles Practiced**

| Principle | Focus |
|----------|--------|
| **S — SRP** | Separation of responsibilities |
| **O — OCP** | Extension without modification |
| **L — LSP** | Safe substitutability |
| **I — ISP** | Small, focused interfaces |
| **D — DIP** | Dependency on abstractions |

---

## **Exercise Mapping**

| Exercises | Principle |
|----------|------------|
| **Ex1 – Ex2** | Single Responsibility Principle (SRP) |
| **Ex3 – Ex4** | Open Closed Principle (OCP) |
| **Ex5 – Ex6** | Liskov Substitution Principle (LSP) |

---

## **Refactoring Constraints**

All exercises were solved under the following rules:

- **Behavior must remain unchanged**
- **Exact console output preserved**
- **Default package only** (no `package` declarations)
- **Java 17 compatibility**
- **No design patterns unless required**
- **No external dependencies**

---

## **Running the Exercises**

From any exercise directory:

```bash
cd SOLID/ExN/src
javac *.java
java Main
```

## Refactoring Goals Achieved

Across exercises, refactoring improvements include:

- Elimination of "god classes"  
- Clear separation of business logic  
- Decoupled persistence mechanisms  
- Extensible rule systems  
- Improved testability  
- Reduced coupling  
- Higher cohesion  

---

## Key Learning Outcomes

These exercises reinforce:

- Why SOLID principles matter in real systems  
- How poor design increases the cost of change  
- How OOP alone can significantly improve architecture  
- The importance of safe, incremental refactoring  

---

## Summary

This repository represents a structured effort to convert intentionally messy designs into clean, modular, SOLID-compliant implementations, while preserving existing behavior.