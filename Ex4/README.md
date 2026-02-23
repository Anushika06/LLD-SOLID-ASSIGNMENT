# **Ex4 — OCP Refactor: Hostel Fee Calculator**

## **1. Overview**

This exercise focuses on refactoring a **Hostel Fee Calculation System** to adhere to the **Open/Closed Principle (OCP)**. The system calculates monthly rent and security deposits based on room types and optional add-ons (Mess, Laundry, Gym).

---

## **2. Problem in the Original Design**

The initial implementation used a centralized `switch-case` and a series of `if-else` blocks inside the `HostelFeeCalculator`, creating several architectural issues:

* **OCP Violation:** Adding a new room type (e.g., "Deluxe") or a new service (e.g., "WiFi") required modifying the core calculation logic.
* **Tight Coupling:** The calculator was responsible for pricing logic, generating IDs, formatting output, and saving data.
* **Brittle Logic:** Security deposits were hard-coded as a single value, making it impossible to have different deposit rates for different room types without further nested logic.
* **Inconsistent Arithmetic:** `double` primitives were used for currency throughout the logic, risking rounding errors and leading to scattered formatting code.

This violated the **Open/Closed Principle**:

> *Software entities should be open for extension, but closed for modification.*

---

## **3. Refactoring Objective**

The goal of the refactor was:
✔ **Abstract pricing** into a component-based model.

✔ **Eliminate conditional branching** in favor of polymorphism.

✔ **Enable "Extension by Addition"**—new features are added by creating new classes.

✔ **Centralize financial logic** using a dedicated `Money` type.

---

## **4. Refactored Design**

The system was redesigned using the **Strategy Pattern**, treating every cost factor (Rooms, Add-ons, and Extras) as a uniform "Component."

### **FeeComponent (Interface)**

**Responsibility:** Defines the contract for any billable item.

* `monthly()`: Returns the recurring fee.
* `deposit()`: Returns the one-time security deposit (utilizes a `default` method returning zero for add-ons).

---

### **Concrete Room Classes (SingleRoom, DoubleRoom, TripleRoom)**

**Responsibility:** Encapsulates the specific base rent and deposit for each room category.

* If a room type changes its price, only that specific class is updated.

---

### **Concrete Add-On Classes (MessAddOn, LaundryAddOn, GymAddOn)**

**Responsibility:** Encapsulates the cost of individual services.

* These classes only implement `monthly()`, as they typically do not require an additional security deposit.

---

### **PricingInfo (The Resolver)**

**Responsibility:** Acts as a Registry/Factory that maps legacy identifiers (integers and enums) to the new `FeeComponent` objects.

* This layer protects the new clean logic from the "Legacy" data structures used in `BookingRequest`.

---

### **HostelFeeCalculator (The Engine)**

**Responsibility:** A high-level aggregator.

* It is now **completely logic-free**. It simply iterates through the components and sums the totals.
* It supports a list of `extras`, allowing for ad-hoc charges (like `LateFee`) to be injected at runtime.

---

## **5. Key Improvements**

### **Open/Closed Compliance**

The calculation engine is now "Closed." To add a new room type, you simply create a new class and add it to the `PricingInfo` map. The `HostelFeeCalculator` code remains untouched.

### **Single Responsibility Compliance**

* **HostelFeeCalculator:** Only sums totals.
* **ReceiptPrinter:** Only handles console output.
* **BookingIdGenerator:** Only handles ID logic.
* **FeeComponent implementations:** Only handle individual pricing logic.

### **Financial Accuracy**

By using the `Money` class, all currency calculations are rounded and formatted consistently, preventing common floating-point errors.

---

## **6. OCP Justification**

The refactor adheres to OCP because:
✔ The engine can now handle **Late Fees**, **Taxes**, or **Discounts** as additional `FeeComponents` without changing a single line of the `calculate` method.

✔ New room tiers can be introduced without risking regressions in the existing pricing logic.

---

## **7. Behavioral Integrity**

Despite the architectural overhaul:
✔ The terminal output (Monthly, Deposit, Total) remains **identical** to the original version.

✔ The system still correctly integrates with the `FakeBookingRepo` for persistence.

---

## **8. Resulting Benefits**

* **Scalability:** The system can grow from 3 room types to 300 without increasing the complexity of the engine.
* **Maintainability:** Pricing changes are isolated to individual, small files.
* **Flexibility:** Supports dynamic "extras" that weren't possible in the rigid original design.

---

## **9. Conclusion**

This refactor moves the hostel system from a **procedural switch-based script** to a **flexible, component-based engine**. By leveraging OCP and the Strategy Pattern, the code is now professional, easy to test, and ready for future business requirements.