# Dependency Inversion Principle (DIP) Example

## What is DIP?

The Dependency Inversion Principle states that:
- High-level modules should not depend on low-level modules. Both should depend on abstractions.
- Abstractions should not depend on details. Details should depend on abstractions.
- Classes should depend on interfaces rather than concrete classes. This helps to make your code more flexible and easier to maintain.

---

## High-level vs Low-level Modules Example

**Example:**  
Consider a `MacBook` class (high-level module) that uses a keyboard and a mouse. The specific types of keyboard and mouse (e.g., `WiredKeyboard`, `WirelessMouse`) are low-level modules.

**Violation:**
```java
class WiredKeyboard {}
class WirelessMouse {}

class MacBook {
    private WiredKeyboard keyboard;
    private WirelessMouse mouse;

    public MacBook() {
        this.keyboard = new WiredKeyboard();
        this.mouse = new WirelessMouse();
    }
}
```
- Here, `MacBook` (high-level) directly depends on concrete classes (`WiredKeyboard`, `WirelessMouse`).

---

**DIP-Compliant Solution:**
```java
interface Keyboard {}
interface Mouse {}

class WiredKeyboard implements Keyboard {}
class WirelessMouse implements Mouse {}

class MacBook {
    private Keyboard keyboard;
    private Mouse mouse;

    public MacBook(Keyboard keyboard, Mouse mouse) {
        this.keyboard = keyboard;
        this.mouse = mouse;
    }
}
```
- Now, `MacBook` depends on the `Keyboard` and `Mouse` interfaces, not specific implementations.

---

## Key Points

- High-level modules contain business logic (e.g., `MacBook`).
- Low-level modules handle details (e.g., `WiredKeyboard`, `WirelessMouse`).
- Both should depend on abstractions for flexibility and maintainability.

---
