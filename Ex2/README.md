# **Ex2 — SRP Refactor: Campus Cafeteria Billing**

## **1. Overview**

This exercise refactors a **Campus Cafeteria Billing System** using the **Single Responsibility Principle (SRP)**.

The system generates invoices by:

- Processing order lines  
- Calculating pricing details  
- Applying tax policies  
- Applying discount rules  
- Formatting invoices  
- Persisting invoices  

The refactor focuses on separating these concerns into dedicated components.

---

## **2. Problem in the Original Design**

In the initial implementation, the `CafeteriaSystem.checkout()` method performed multiple unrelated tasks:

- Menu lookup  
- Subtotal calculation  
- Tax computation  
- Discount computation  
- Invoice formatting  
- Persistence  

This led to:

- A large, complex method  
- Hard-coded tax and discount logic  
- Tight coupling between business logic and presentation  
- Poor extensibility  
- Difficult testing  

This violated the **Single Responsibility Principle**, which states:

> *A class should have only one reason to change.*

---

## **3. Refactoring Objective**

The refactor aimed to:

✔ Separate calculation logic  
✔ Isolate business rules  
✔ Decouple persistence  
✔ Decouple formatting  
✔ Preserve exact program output  

---

## **4. Refactored Design**

The system was decomposed into focused components.

---

### **PricingCalculator**
**Responsibility:**  
Handles pricing-related computations.

- Calculates subtotal  
- Applies tax policies  
- Applies discounts  
- Produces a structured `PricingResult`

✔ No formatting  
✔ No persistence  

---

### **PricingResult**
**Responsibility:**  
Acts as a structured data holder.

- Stores subtotal, tax, discount, total  
- Transfers computation results cleanly  

---

### **TaxPolicy (Interface)**
**Responsibility:**  
Defines tax calculation behavior.

Allows multiple implementations:

- `StudentTaxPolicy`  
- `StaffTaxPolicy`

✔ Extensible without modifying existing logic  

---

### **Discount (Interface)**
**Responsibility:**  
Defines discount calculation behavior.

Allows multiple implementations:

- `StudentDiscount`  
- `StaffDiscount`

✔ New discounts added via new classes  

---

### **Invoice**
**Responsibility:**  
Represents invoice data.

- Stores invoice details  
- Decouples data from formatting  

---

### **LineDetail**
**Responsibility:**  
Represents individual invoice lines.

- Separates line structure from calculations  

---

### **InvoiceFormatter**
**Responsibility:**  
Handles invoice presentation.

- Formats invoice text  
- Controls output layout  

✔ No calculations  
✔ No business rules  

---

### **InvoiceRepository (Interface)**
**Responsibility:**  
Defines persistence behavior.

---

### **FileStore (Implementation)**
**Responsibility:**  
Stores invoices in memory.

✔ Replaceable persistence mechanism  

---

### **CafeteriaSystem**
**Responsibility:**  
Orchestrates the billing workflow.

Coordinates:

`Order → PricingCalculator → Invoice → Formatter → Repository`

✔ No rule logic  
✔ No formatting  
✔ No storage knowledge  

---

## **5. Key Improvements**

### ✔ **Single Responsibility Compliance**

Each class now has a clearly defined purpose:

- Calculations → `PricingCalculator`  
- Rules → `TaxPolicy`, `Discount`  
- Data → `Invoice`, `PricingResult`, `LineDetail`  
- Formatting → `InvoiceFormatter`  
- Persistence → `InvoiceRepository`, `FileStore`  
- Workflow → `CafeteriaSystem`

---

### ✔ **Loose Coupling**

`CafeteriaSystem` depends on abstractions:

- `TaxPolicy`  
- `Discount`  
- `InvoiceRepository`

✔ Implementation details are replaceable  

---

### ✔ **Improved Extensibility**

New rules can be added without modifying core logic:

- Add discount → Implement `Discount`  
- Add tax rule → Implement `TaxPolicy`

---

### ✔ **Better Testability**

Business rules and calculations can now be tested independently:

- Pricing logic without formatting  
- Discount logic without persistence  
- Tax logic without console IO  

---

### ✔ **Cleaner Business Workflow**

The billing flow becomes predictable:

`Build Order → Calculate Pricing → Create Invoice → Format → Save`

---

## **6. SRP Justification**

The refactor adheres to SRP because:

✔ Calculation logic is isolated  
✔ Business rules are isolated  
✔ Formatting logic is isolated  
✔ Persistence logic is isolated  
✔ Workflow logic is isolated  

Each module now has **only one reason to change**.

---

## **7. Behavioral Integrity**

Despite structural changes:

✔ Invoice output remains **exactly unchanged**  
✔ `MenuItem` and `OrderLine` fields preserved  
✔ No external libraries introduced  

---

## **8. Resulting Benefits**

- Easier rule extension  
- Reduced modification risk  
- Higher readability  
- Better modularity  
- Professional design structure  

---

## **9. Conclusion**

The refactor transforms a monolithic billing method into a **modular, SRP-compliant architecture**.

The system is now:

✔ Flexible  
✔ Maintainable  
✔ Testable  
✔ Extensible  