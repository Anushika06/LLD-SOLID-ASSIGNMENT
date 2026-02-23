This README outlines the refactoring of the **Notification Sender Hierarchy** to adhere to the **Liskov Substitution Principle (LSP)**.

# Refactoring Report: Notification Sender Hierarchy (LSP)

## 1. Problem Overview

The original design contained several violations of the Liskov Substitution Principle that made the `NotificationSender` hierarchy fragile and difficult to use polymorphically:

* **Tightened Preconditions:** `WhatsAppSender` threw a runtime exception for valid `Notification` objects if the phone number lacked a `+` prefix.
* **Semantic Changes:** `EmailSender` silently truncated message bodies, altering the intent of the notification without notifying the caller.
* **Ignored Fields:** `SmsSender` ignored the subject field entirely, even though the base contract implied a notification would be delivered as provided.
* **Leaky Abstractions:** The `Main` method required specific `try-catch` blocks and knowledge of `WhatsAppSender` to handle unique failure modes, proving the subtypes were not truly substitutable.

## 2. Refactored Solution

The refactor focuses on creating a stable base contract where all subtypes behave predictably when treated as the base `NotificationSender` type.

### Key Changes:

* **Template Method Pattern:** The `NotificationSender` now uses a `final` `send` method to enforce a standard workflow: `validate(n)` followed by `sendNotification(n)`.
* **Explicit Validation:** Instead of silent failures or surprise runtime crashes, validation is now a formal part of the contract. Each sender (e.g., `EmailSender`, `SmsSender`) implements its own `validate` logic to ensure required fields are present.
* **Predictable Error Handling:** The system uses a standard `IllegalArgumentException` for validation failures. The caller in `Main` can now iterate through a list of any `NotificationSender` and handle errors uniformly.
* **Preservation of Meaning:** Silent truncation has been removed. The `EmailSender` now sends the full body provided in the `Notification`.
* **Channel Identification:** A new `getChannel()` method allows the caller to identify which sender failed or succeeded without using `instanceof`.

## 3. Refactored Class Structure

* **`NotificationSender` (Abstract Base):** Defines the `final send(Notification n)` template method and abstract hooks for `validate`, `sendNotification`, and `getChannel`.
* **`EmailSender`:** Validates that an email address is present and prints the email notification.
* **`SmsSender`:** Validates the presence of a phone number and prints the SMS.
* **`WhatsAppSender`:** Enforces the specific `+` prefix requirement within the `validate` hook, ensuring the caller is notified of the constraint via a standard exception.
* **`PushNotificationSender`:** Demonstrates the ease of adding new channels (Stretch Goal).

## 4. How to Run

1. Compile the Java files:
```bash
javac *.java

```


2. Run the demonstration:
```bash
java Main

```



## 5. Sample Output

The refactored output remains consistent with the required demo behavior but handles the "Push" channel and errors more robustly:

```text
=== Notification Demo ===
EMAIL -> to=riya@sst.edu subject=Welcome body=Hello and welcome to SST!
SMS -> to=9876543210 body=Hello and welcome to SST!
WhatsApp ERROR: phone must start with + and country code
PUSH -> deviceOwner=riya@sst.edu title=Welcome message=Hello and welcome to SST!
AUDIT entries=4

```

*(Note: Audit entries increased to 4 due to the addition of the Push notification channel).*