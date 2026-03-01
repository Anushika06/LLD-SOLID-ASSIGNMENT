# Student Club Management – ISP Refactoring (SOLID)

## Overview

This project demonstrates refactoring a Student Club Admin system to comply with the **Interface Segregation Principle (ISP)** from the SOLID principles.

The original system used a large, "fat" interface (`ClubAdminTools`) that forced all role-based tools (Treasurer, Secretary, Event Lead) to implement unrelated methods.

This refactoring introduces **capability-based interfaces**, ensuring each role depends only on the operations it actually needs.

---

# Objective

Refactor the system to:

* Remove the fat `ClubAdminTools` interface
* Split responsibilities into small, role-specific interfaces
* Eliminate dummy or irrelevant method implementations
* Ensure `ClubConsole` depends only on minimal interfaces
* Preserve the original console output and behavior

---

# Problem in Original Design

The original interface:

```java
public interface ClubAdminTools {
    void addIncome(double amt, String note);
    void addExpense(double amt, String note);
    void addMinutes(String text);
    void createEvent(String name, double budget);
    int getEventsCount();
}
```

## Issues

* Treasurer had to implement event and minutes methods
* Secretary had to implement finance methods
* Event lead had to implement finance and minutes methods
* Dummy implementations (`/* irrelevant */`) were added
* Some methods returned placeholder values like `0`
* Capabilities were unclear and tightly coupled

This violates the **Interface Segregation Principle**:

> Clients should not be forced to depend on methods they do not use.

---

# Refactored Design

The fat interface was removed and replaced with smaller, capability-based interfaces.

---

## FinanceOperations

```java
public interface FinanceOps {
    void addIncome(double amt, String note);
    void addExpense(double amt, String note);
}
```

Used by: `TreasurerTool`

---

## MinutesOperations

```java
public interface MinutesOps {
    void addMinutes(String text);
}
```

Used by: `SecretaryTool`

---

## EventOperations

```java
public interface EventOps {
    void createEvent(String name, double budget);
    int getEventsCount();
}
```

Used by: `EventLeadTool`

---

# Refactored Role Implementations

Each role now implements only the interface relevant to it.

---

## TreasurerTool

* Implements `FinanceOps`
* Handles ledger transactions only
* No irrelevant methods

---

## SecretaryTool

* Implements `MinutesOps`
* Handles meeting minutes only
* No finance or event methods

---

## EventLeadTool

* Implements `EventOps`
* Manages event creation and tracking
* No finance or minutes methods

---

# ClubConsole Refactoring

Before:

* Depended on `ClubAdminTools`
* Used a fat interface
* Mixed responsibilities

After:

* Depends on specific capability interfaces
* Uses abstraction properly
* Cleaner separation of concerns

```java
FinanceOps treasurer = new TreasurerTool(ledger);
MinutesOps secretary = new SecretaryTool(minutes);
EventOps lead = new EventLeadTool(events);
```

---

# Sample Output (Preserved)

```
=== Club Admin ===
Ledger: +5000 (sponsor)
Minutes added: "Meeting at 5pm"
Event created: HackNight (budget=2000)
Summary: ledgerBalance=5000, minutes=1, events=1
```

✔ Output remains unchanged
✔ Command order preserved

---

# Architectural Improvements

* Removed fat interface
* Eliminated dummy implementations
* Clarified role responsibilities
* Improved extensibility
* Improved maintainability
* Made system safer for future role additions

---

# Stretch Goal Possibility

A new role like **PublicityLead** can now be added without implementing finance or minutes methods.

Example:

```java
public class PublicityLeadTool implements EventOps {
    ...
}
```

No changes required to existing role implementations.

---

# How to Run

```bash
cd SOLID/Ex8/src
javac *.java
java Main
```


