# Campus Transport Booking – SOLID (DIP Refactoring)

## Overview

This project demonstrates refactoring a Campus Transport Booking system to comply with the **Dependency Inversion Principle (DIP)** from the SOLID principles.

The original implementation tightly coupled the booking logic to concrete services such as `DistanceCalculator`, `DriverAllocator`, and `PaymentGateway`.

This refactoring introduces **small service abstractions and constructor-based dependency injection**, making the system:

* Extensible
* Testable
* Maintainable
* Easy to evolve without modifying booking logic

---

# Objective

Refactor the booking system to:

* Remove direct instantiation of concrete services
* Introduce minimal service abstractions
* Inject dependencies via constructor
* Preserve receipt output format and order
* Allow swapping implementations without editing booking logic
* Enable mock services for testing

---

# Problem in Original Design

Original `TransportBookingService` directly instantiated its dependencies:

```java
DistanceCalculator dist = new DistanceCalculator();
DriverAllocator alloc = new DriverAllocator();
PaymentGateway pay = new PaymentGateway();
```

## Issues

1. High-level booking logic depends on concrete implementations
2. Hard to test without real services
3. Cannot add new payment method without editing booking logic
4. Business rules mixed with infrastructure calls
5. No abstraction boundaries

This violates the **Dependency Inversion Principle**:

> High-level modules should not depend on low-level modules.
> Both should depend on abstractions.

---

# Refactored Architecture

We introduced three minimal service interfaces.

---

## DistanceService

```java
public interface DistanceService {
    double km(GeoPoint a, GeoPoint b);
}
```

---

## DriverAllocationService

```java
public interface DriverAllocationService {
    String allocate(String studentId);
}
```

---

## PaymentService

```java
public interface PaymentService {
    String charge(String studentId, double amount);
}
```

---

# Refactored TransportBookingService

```java
public class TransportBookingService {

    private final DistanceService distanceService;
    private final DriverAllocationService driverService;
    private final PaymentService paymentService;

    public TransportBookingService(
            DistanceService distanceService,
            DriverAllocationService driverService,
            PaymentService paymentService) {

        this.distanceService = distanceService;
        this.driverService = driverService;
        this.paymentService = paymentService;
    }

    public void book(TripRequest req) {

        double km = distanceService.km(req.from, req.to);
        System.out.println("DistanceKm=" + km);

        String driver = driverService.allocate(req.studentId);
        System.out.println("Driver=" + driver);

        // Business pricing rule
        double fare = 50.0 + km * 6.6666666667;
        fare = Math.round(fare * 100.0) / 100.0;

        String txn = paymentService.charge(req.studentId, fare);
        System.out.println("Payment=PAID txn=" + txn);

        BookingReceipt r = new BookingReceipt("R-501", fare);
        System.out.println("RECEIPT: " + r.id + " | fare=" + String.format("%.2f", r.fare));
    }
}
```

Now the booking service depends only on **abstractions**, not concrete classes.

---

# Sample Output (Preserved)

```text
=== Transport Booking ===
DistanceKm=6.0
Driver=DRV-17
Payment=PAID txn=TXN-9001
RECEIPT: R-501 | fare=90.00
```

✔ Output format preserved
✔ Line order unchanged
✔ Business behavior identical

---

# Testability Improvement (Mock Services)

Because of DIP, we can now inject mock services for testing.

## MockDriverAllocator

```java
public class MockDriverAllocator implements DriverAllocationService {
    @Override
    public String allocate(String studentId) {
        return "MOCK-DRV-01";
    }
}
```

## MockPaymentGateway

```java
public class MockPaymentGateway implements PaymentService {
    @Override
    public String charge(String studentId, double amount) {
        return "MOCK-TXN-0001";
    }
}
```

These can be injected without modifying booking logic.

---

# Swappable Implementations

The system now allows:

* Real `PaymentGateway`
* StripePaymentGateway (future)
* MockPaymentGateway (tests)
* SurgeDriverAllocator (future)
* FakeDistanceService (tests)

All without editing `TransportBookingService`.

---

# 🚀 Architectural Improvements

* Eliminated tight coupling
* Introduced constructor-based dependency injection
* Enabled test doubles
* Clean separation of business logic and infrastructure
* Improved flexibility and extensibility

---

# 🛠 How to Run

```bash
cd SOLID/Ex10/src
javac *.java
java Main
```
