# Liskov Substitution Principle (LSP) Example

## What is LSP?

The Liskov Substitution Principle states that objects of a superclass should be replaceable with objects of a subclass without affecting the correctness of the program. Subtypes must honor the contracts of their base types.

---

## LSP Violation Example

The following code defines the `Bike` interface and the `Motorcycle` and `Bicycle` classes. The `Bicycle` class violates the Liskov Substitution Principle by throwing an error in the `turnOnEngine` method because it does not fulfill the contract defined by the `Bike` interface.

```java
interface Bike {
    void turnOnEngine();
    void accelerate();
}

class Motorcycle implements Bike {
    @Override
    public void turnOnEngine() {
        // implementation for motorcycle
    }
    @Override
    public void accelerate() {
        // implementation for motorcycle
    }
}

class Bicycle implements Bike {
    @Override
    public void turnOnEngine() {
        throw new UnsupportedOperationException("Bicycles don't have engines!");
    }
    @Override
    public void accelerate() {
        // implementation for bicycle
    }
}
```

**LSP Violation:**
- The `Bicycle` class cannot be substituted for the `Bike` interface without causing errors, violating LSP.
- To fix this, design interfaces so that all subclasses can fulfill the contract without throwing errors for unsupported operations.

---

## LSP-Compliant Solution

A better design is to split the interface so that only classes with engines implement the engine-related methods:

```java
interface Bike {
    void accelerate();
}

interface EngineBike extends Bike {
    void turnOnEngine();
}

class Motorcycle implements EngineBike {
    @Override
    public void turnOnEngine() {
        // implementation for motorcycle
    }
    @Override
    public void accelerate() {
        // implementation for motorcycle
    }
}

class Bicycle implements Bike {
    @Override
    public void accelerate() {
        // implementation for bicycle
    }
}
```

**Correct Use:**
- Now, both `Motorcycle` and `Bicycle` can be used wherever a `Bike` is expected, and only engine bikes implement `turnOnEngine()`.
- This design respects LSP and avoids runtime errors.

---

## Key Points

- Subtypes must be substitutable for their base types without breaking the program.
- Violating LSP leads to unexpected behavior and bugs.
- Design hierarchies so subclasses honor the contracts of their superclasses.
- Use interface segregation to avoid forcing subclasses to implement unsupported operations.

---

This example demonstrates how following LSP leads to more robust and predictable code.
