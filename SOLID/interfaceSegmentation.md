# Interface Segregation Principle (ISP) 

## What is ISP?

The Interface Segregation Principle states that clients should not be forced to depend on interfaces they do not use. It promotes creating smaller, more specific interfaces so that implementing classes only need to provide methods that are relevant to them.

---

## ISP Violation Example

In this example, the `RestaurantEmployee` interface has methods for washing dishes, serving customers, and cooking food. The `Waiter` class is forced to implement all methods, even those it doesn't need.

```java
interface RestaurantEmployee {
    void washDishes();
    void serveCustomers();
    void cookFood();
}

class Waiter implements RestaurantEmployee {
    @Override
    public void washDishes() {
        throw new UnsupportedOperationException("Waiter doesn't wash dishes");
    }
    @Override
    public void serveCustomers() {
        // Waiter serves customers
    }
    @Override
    public void cookFood() {
        throw new UnsupportedOperationException("Waiter doesn't cook food");
    }
}
```

**Violation:**  
- The `Waiter` class is forced to implement methods it does not need, violating ISP.

---

## ISP-Compliant Solution

Split the interface into smaller, more specific interfaces so each class only implements what it needs.

```java
interface DishWasher {
    void washDishes();
}

interface Waiter {
    void serveCustomers();
}

interface Chef {
    void cookFood();
}

class RestaurantWaiter implements Waiter {
    @Override
    public void serveCustomers() {
        // Waiter serves customers
    }
}
```

**Correct Use:**  
- Now, each class only implements the methods relevant to its role, following the Interface Segregation Principle.

---

## Key Points

- Create small, focused interfaces.
- Classes should not be forced to implement methods they do not use.
- ISP leads to more maintainable and flexible code.

---
