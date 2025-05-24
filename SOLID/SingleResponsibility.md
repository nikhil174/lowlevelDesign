# Single Responsibility Principle (SRP) Example

## What is SRP?

The Single Responsibility Principle states that a class should have only one reason to change, meaning it should have only one job or responsibility. This principle helps keep code modular, maintainable, and easy to test.

---

## Example Explanation

In the following examples, we demonstrate both a violation and a correct application of the Single Responsibility Principle:

### SRP Violation Example

- The `Invoice` class below is responsible for multiple functionalities:
  - Calculating the total amount
  - Printing the invoice
  - Saving the invoice to the database
- This means the class has more than one reason to change:
  - If the calculation logic changes
  - If the printing format or method changes
  - If the database or storage logic changes
- This violates the Single Responsibility Principle (SRP), which states that a class should have only one reason to change.

```java
// Marker class representing a marker object
public class Marker {
    String name;
    String color;
    int year;
    int price;

    public Marker(String name, String color, int year, int price) {
        this.name = name;
        this.color = color;
        this.year = year;
        this.price = price;
    }
}

// SRP Violation Example (Invoice class)
public class Invoice {
    private Marker marker;
    private int quantity;

    public Invoice(Marker marker, int quantity) {
        this.marker = marker;
        this.quantity = quantity;
    }

    public int calculateTotal() {
        return this.marker.price * this.quantity;
    }

    public void printInvoice() {
        // Printing logic here
    }

    public void saveToDB() {
        // Database save logic here
    }
}
```

---

### SRP-Compliant Solution

To adhere to SRP, each responsibility should be separated into different classes. This makes the code more modular, maintainable, and easier to test.

- `Invoice` is responsible only for holding data and calculation logic.
- `InvoicePrinter` is responsible only for printing.
- `InvoiceDAO` is responsible only for saving to the database.

```java
// SRP-compliant classes for Invoice functionality
public class Invoice {
    private Marker marker;
    private int quantity;

    public Invoice(Marker marker, int quantity) {
        this.marker = marker;
        this.quantity = quantity;
    }

    public int calculateTotal() {
        return this.marker.price * this.quantity;
    }
}

public class InvoicePrinter {
    public void printInvoice(Invoice invoice) {
        // Code to print the invoice
    }
}

public class InvoiceDAO {
    public void saveToDB(Invoice invoice) {
        // Code to save to database
    }
}
```

**Explanation:**
- Separating the responsibilities into separate classes aligns with the Single Responsibility Principle.
- Each class now has only one reason to change:
  - `InvoiceDAO` is responsible for saving to the database.
  - `InvoicePrinter` is responsible for printing.
  - The `Invoice` class is responsible for calculations.
- This makes the code more modular, maintainable, and easier to test.

---

## Key Points

- **Each class has only one responsibility.**
- **Changes in one responsibility affect only one class.**
- **Code is easier to maintain, test, and extend.**

---

This example demonstrates how following SRP leads to clean, maintainable, and modular code.
