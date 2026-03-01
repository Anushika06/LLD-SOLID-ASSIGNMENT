# 🏫 Smart Classroom System – SOLID (ISP) Refactoring

## 📌 Overview

This project demonstrates refactoring a Smart Classroom system to properly follow the **Interface Segregation Principle (ISP)** from the SOLID design principles.

The original design used a large "fat interface" that forced all devices to implement unrelated methods.
This refactoring introduces **capability-based interfaces**, resulting in a cleaner, extensible, and more maintainable architecture.

---

# 🎯 Objective

Refactor the system to:

* Remove the fat interface
* Split responsibilities into smaller capability interfaces
* Ensure devices implement only what they actually support
* Make the controller depend on abstractions
* Preserve original console output
* Add a new `SmartBoard` device without modifying existing logic

---

# 🔴 Problem in Original Design

Previously, all devices implemented a single interface containing multiple unrelated methods:

```java
public interface SmartClassroomDevice {
    void powerOn();
    void powerOff();
    void setBrightness(int pct);
    void setTemperatureC(int c);
    int scanAttendance();
    void connectInput(String port);
}
```

### ❌ Issues

* AC had brightness and attendance methods
* Scanner had temperature methods
* Projector had attendance methods
* Dummy implementations hid logical issues
* Adding a new device required implementing irrelevant methods
* Controller relied on concrete types or string checks

This violated the **Interface Segregation Principle**:

> Clients should not be forced to depend on methods they do not use.

---

# 🟢 Refactored Design

The fat interface was replaced with **small, focused capability interfaces**.

---

## 🔌 Core Power Interface

```java
public interface SmartClassroomDevice {
    void powerOn();
    void powerOff();
}
```

---

## 🧩 Capability Interfaces

### Brightness Control

```java
public interface BrightnessController {
    void setBrightness(int pct);
}
```

### Temperature Control

```java
public interface TemperatureController {
    void setTemperatureC(int c);
}
```

### Attendance Scanning

```java
public interface AttendanceScannable {
    int scanAttendance();
}
```

### Input Connection

```java
public interface InputConnector {
    void connectInput(String port);
}
```

Each interface now represents **exactly one responsibility**.

---

# 🏗 Device Implementations

Each device implements only relevant capabilities.

---

## 🖥 Projector

Implements:

* `SmartClassroomDevice`
* `InputConnector`

---

## 💡 LightsPanel

Implements:

* `SmartClassroomDevice`
* `BrightnessController`

---

## ❄ AirConditioner

Implements:

* `SmartClassroomDevice`
* `TemperatureController`

---

## 🧾 AttendanceScanner

Implements:

* `SmartClassroomDevice`
* `AttendanceScannable`

---

## 🖊 SmartBoard (Stretch Goal)

Implements:

* `SmartClassroomDevice`
* `InputConnector`
* `BrightnessController`

The SmartBoard was added **without modifying existing controller logic**, demonstrating proper OCP compliance.

---

# 🗃 DeviceRegistry Improvements

The registry was redesigned to support capability-based lookup.

### Before

* Stored `List<SmartClassroomDevice>`
* Retrieved devices using string names

### After

* Stores `List<Object>`
* Uses generics for type-safe capability retrieval

```java
public <T> T getFirstOfType(Class<T> type)
public <T> List<T> getAllOfType(Class<T> type)
```

This allows retrieving devices based on what they **can do**, not what they **are**.

---

# 🎮 Controller Design

The controller now depends on abstractions:

```java
BrightnessController lights =
    reg.getFirstOfType(BrightnessController.class);

TemperatureController ac =
    reg.getFirstOfType(TemperatureController.class);
```

Shutdown sequence powers off **all power-capable devices**:

```java
reg.getAllOfType(SmartClassroomDevice.class)
   .forEach(device -> device.powerOff());
```

No concrete type checks.
No string comparisons.
No dummy logic.

---

# 📤 Sample Output

```
=== Smart Classroom ===
Projector ON (HDMI-1)
Lights set to 60%
AC set to 24C
Attendance scanned: present=3
Shutdown sequence:
Projector OFF
Lights OFF
AC OFF
```
---

# 🚀 Architectural Improvements

* Eliminated fat interface
* Removed dummy implementations
* Introduced capability-based design
* Enabled safe extensibility
* Made shutdown fully abstraction-driven
* Improved type safety using generics
* Added SmartBoard without code modification elsewhere

---

# 🛠 How to Run

```bash
cd src
javac *.java
java Main
```



