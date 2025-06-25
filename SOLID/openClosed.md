# Open/Closed Principle (OCP) Example

## What is OCP?

The Open/Closed Principle states that software entities (classes, modules, functions, etc.) should be open for extension, but closed for modification. This means you should be able to add new functionality without changing the existing code.

---

## OCP Violation Example

Suppose you have an `InvoiceDAO` class that saves invoices to a database. If you want to add new functionality (e.g., saving to a file), you have to modify the existing class, which violates the OCP.

```java
public class InvoiceDAO {
    public void saveToDB(Invoice invoice) {
        // Code to save to database
    }
    
    public void saveToFile(Invoice invoice) {
        // Code to save invoice to a file
    }
}
```

**Problem:**
- If in the future you need to support a new type of database or storage, you have to modify the `InvoiceDAO` class again, which breaks the Open/Closed Principle.

---

## OCP-Compliant Solution

To follow OCP, use an interface and provide separate implementations for each storage type. This way, you can add new functionality by creating new classes, not by modifying existing ones.

```java
// Interface for InvoiceDAO
interface InvoiceDAO {
    void save(Invoice invoice);
}

// Implementation for saving to Database
class DatabaseInvoiceDAO implements InvoiceDAO {
    @Override
    public void save(Invoice invoice) {
        // Code to save to database
    }
}

// Implementation for saving to File
class FileInvoiceDAO implements InvoiceDAO {
    @Override
    public void save(Invoice invoice) {
        // Code to save to file
    }
}
```

**Explanation:**
- Now, the code is open for extension (add new storage types by creating new classes) but closed for modification (no need to change existing classes).
- This approach makes the code more flexible, robust, and maintainable.

---

## Key Points

- **Open for extension:** You can add new features or behaviors by adding new classes.
- **Closed for modification:** Existing, tested code does not need to be changed.
- **Helps prevent bugs and makes code easier to maintain and scale.**

---

This example demonstrates how following OCP leads to flexible, robust, and maintainable code.
