# Most Frequently Asked LLD Problems

This directory contains real-world system design problems commonly asked in top tech interviews. Each problem is documented with features and, where available, links to design pattern solutions and best practices.

## Problem List

1. **Parking Lot**
    - Features: Different vehicle sizes, multiple floors, entry/exit gates, ticketing, fees.
    - [Solution: See `parkingLot.md`](./parkingLot.md)
    - Patterns Used: Factory, Strategy, Null Object

2. **Library Management System**
    - Features: Book search, checkout/check-in, user registration, fine calculation.

3. **Elevator System**
    - Features: Multiple elevators, request queueing, optimization logic, direction-based scheduling.

4. **Cab Booking System (Uber/Ola)**
    - Features: Matching rider to driver, surge pricing, GPS tracking, ride history.

5. **Food Delivery System (Swiggy/Zomato)**
    - Features: Order placement, restaurant selection, delivery partner assignment, tracking.

6. **Hotel Booking System**
    - Features: Room availability, booking/reservation, payments, cancellation.

7. **Splitwise System**
    - Features: Groups, expense split, debt simplification, user balances.

8. **ATM System**
    - Features: PIN validation, cash withdrawal, transaction history, balance inquiry.

9. **Rate Limiter**
    - Types: Token Bucket, Leaky Bucket, Fixed Window, Sliding Window.

10. **Notification System**
    - Features: Email/SMS/Push support, retries, scheduling, priority handling.

11. **Online Movie Ticket Booking (BookMyShow)**
    - Features: Seat selection, locking, payment gateway, movie catalog.

12. **Chess Game / Tic-Tac-Toe / Snake & Ladder**
    - Focus: OOP, game rules, game flow, player management.

13. **Logging Framework (Log4j)**
    - Features: Log levels, appenders, filtering, formatting.

14. **Social Media Feed System (Facebook/Twitter)**
    - Features: Post creation, timeline generation, follower/following, newsfeed ranking.

15. **File Storage System (Google Drive)**
    - Features: File uploads, sharing, permissions, folders, version control.

---

✅ **Design Patterns by Priority of Usage**

Below are the most frequently used design patterns in real-world LLD problems, ranked by how often they appear in interview scenarios and system designs. Each pattern includes typical use cases and a brief explanation of its importance.

---

🔹 **1. Strategy Pattern**
- **Used in:**
    - Cab Booking (pricing, matching strategies)
    - Notification System (email/SMS strategy)
    - Parking Lot (parking fee strategy)
    - Rate Limiter (different limiting strategies)
- **Why important:** Lets you define a family of algorithms/behaviors and make them interchangeable at runtime.

🔹 **2. Observer Pattern**
- **Used in:**
    - Notification System (publish-subscribe model)
    - Feed System (updates to followers)
    - Logging System (notify all appenders)
    - File Storage (sync clients with cloud changes)
- **Why important:** Used when changes in one object should automatically notify others (decouples subject and observers).

🔹 **3. Singleton Pattern**
- **Used in:**
    - Logger
    - Rate Limiter
    - Configuration classes
    - Parking Lot Manager / Elevator Controller
- **Why important:** Ensures only one instance of a class is used across the application (shared resource, global state).

🔹 **4. Factory Pattern / Abstract Factory**
- **Used in:**
    - Notification System (create SMS, Email, Push objects)
    - Chess Game (create pieces)
    - Cab Booking (create vehicle type / user type)
    - Logger (create loggers for different levels)
- **Why important:** Handles object creation without exposing the instantiation logic; supports extensibility and decoupling.

🔹 **5. Decorator Pattern**
- **Used in:**
    - Logging System (add timestamp, thread info, etc.)
    - Notification System (adding retry, encryption, etc.)
    - Cab Booking / Food App (add-ons to pricing)
- **Why important:** Add responsibilities dynamically to objects without modifying their base classes.

🔹 **6. Command Pattern**
- **Used in:**
    - Elevator System (queueing requests)
    - Chess Game (undo/redo)
    - ATM (transaction management)
- **Why important:** Encapsulates a request as an object, useful for queuing, logging, and undo operations.

🔹 **7. Template Method Pattern**
- **Used in:**
    - Logging Framework (standard flow for log processing)
    - Notification System (standard structure for send method)
- **Why important:** Defines a skeleton of an algorithm and lets subclasses fill in specific steps.

🔹 **8. Builder Pattern**
- **Used in:**
    - Hotel Booking (complex booking object)
    - Notification (build message objects)
    - Feed or File objects (rich metadata)
- **Why important:** Good for constructing complex objects step by step, especially with many optional fields.

🔹 **9. State Pattern**
- **Used in:**
    - Elevator (idle, moving up/down, maintenance)
    - ATM (card inserted, pin entered, cash dispensed)
    - Parking Slot (available, occupied)
- **Why important:** Encapsulates object state and allows changing behavior based on internal state.

🔹 **10. Adapter Pattern**
- **Used in:**
    - Notification System (3rd-party email/SMS integration)
    - File Storage System (interface to different storage providers)
    - Payment Gateways (adapting to different providers)
- **Why important:** Useful when integrating third-party APIs or incompatible interfaces.

🧩 **Other Useful Patterns**
- **Proxy:** File Storage (access control proxy)
- **Composite:** File System (files and folders)
- **Flyweight:** Chess Game (reuse piece objects)
- **Mediator:** Elevator System (central controller)
- **Iterator:** Feed/Library navigation

---

### 🏁 Summary Table

| Priority | Pattern                | # of Appearances |
|----------|------------------------|------------------|
| 1        | Strategy               | ✅✅✅✅ (4+)      |
| 2        | Observer               | ✅✅✅✅ (4+)      |
| 3        | Singleton              | ✅✅✅            |
| 4        | Factory / Abstract     | ✅✅✅            |
| 5        | Decorator              | ✅✅              |
| 6        | Command                | ✅✅              |
| 7        | Template Method        | ✅✅              |
| 8        | Builder                | ✅✅              |
| 9        | State                  | ✅✅              |
| 10       | Adapter                | ✅✅              |

---

For a full mapping and explanation, see [`/patterns/patternTypes.md`](../patterns/patternTypes.md).