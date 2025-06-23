# Parking Lot Design – Factory, Strategy, and Null Object Patterns

## Requirements
- Vehicles enter the parking lot, take a ticket, are assigned the nearest available parking spot, pay at the exit, and leave.
- Support for multiple entrances and exits (currently 1 each, extensible).
- Support for multiple vehicle types (2-wheeler, 4-wheeler, extensible for future types).
- Parking spots are assigned based on vehicle type and proximity (nearest spot, cheapest, etc.) using a strategy.
- Payment can be per entry or per minute (support both, extensible).
- System should handle unsupported vehicle types gracefully (no null pointer exceptions).
- Parking spot management should be extensible for new strategies (e.g., least fees, reserved spots).

## Objects
- **Vehicle**: Represents a vehicle entering the lot.
    - vehicleNo: String
    - vehicleType: enum (2WHEELER, 4WHEELER, ...)
- **Ticket**: Issued at entry.
    - entryTime: DateTime
    - parkingSpot: ParkingSpot
    - vehicle: Vehicle
- **EntranceGate**: Handles entry logic.
    - findParkingSpot(): Uses ParkingSpotManager and ParkingStrategy to assign a spot.
    - updateParkingSpot(): Marks spot as occupied.
    - generateTicket(): Issues a ticket with entry time, vehicle, and spot info.
- **ParkingSpot**: Represents a spot in the lot.
    - id: String
    - isEmpty: boolean
    - vehicle: Vehicle
    - price: double
    - type: enum (2WHEELER, 4WHEELER, ...)
- **ExitGate**: Handles exit logic.
    - calculateCost(): Uses PricingStrategy to compute parking fee based on duration and spot type.
    - processPayment(): Handles payment and marks spot as available.
    - updateParkingSpot(): Marks spot as empty.
- **PricingStrategy**: Interface for different pricing algorithms (e.g., flat rate, hourly, dynamic pricing).
- **PricingStrategyFactory**: Factory for creating appropriate PricingStrategy objects based on rules (e.g., vehicle type, time of day, special events).
- **ParkingSpotManager (Abstract Class)**: Base class for managing parking spots, with concrete managers for each vehicle type (e.g., TwoWheelerSpotManager, FourWheelerSpotManager). Uses a ParkingStrategy. Can also act as a factory for creating and managing parking spots for its vehicle type. Abstract class is used to share common logic and state among all managers.
- **ParkingStrategy**: Interface for different spot assignment strategies (nearest, least fees, etc).
- **Factory Pattern**: Used for creating Vehicle, ParkingSpot, and PricingStrategy objects.
- **Null Object Pattern**: Used for unsupported vehicle or spot types.
- **Strategy Pattern**: Used for flexible parking spot assignment logic.

---

## Patterns Used
- **Factory Pattern**: Centralizes creation of vehicles, parking spots, and pricing strategies.
- **Null Object Pattern**: Provides safe fallback for unsupported types.
- **Strategy Pattern**: Allows dynamic selection of parking spot assignment logic (e.g., nearest, cheapest).
- **ParkingSpotManager as Factory**: Each manager can encapsulate the creation and management of parking spots for its type, acting as a factory for spots.

---

```java
// Vehicle interface and implementations
interface Vehicle {
    String getNumber();
    String getType();
}

class TwoWheeler implements Vehicle {
    private String number;
    public TwoWheeler(String number) { this.number = number; }
    public String getNumber() { return number; }
    public String getType() { return "2WHEELER"; }
}

class FourWheeler implements Vehicle {
    private String number;
    public FourWheeler(String number) { this.number = number; }
    public String getNumber() { return number; }
    public String getType() { return "4WHEELER"; }
}

// Null Object
class NullVehicle implements Vehicle {
    public String getNumber() { return "N/A"; }
    public String getType() { return "NONE"; }
}

// Vehicle Factory
class VehicleFactory {
    public static Vehicle getVehicle(String type, String number) {
        if (type == null) return new NullVehicle();
        switch (type.toUpperCase()) {
            case "2WHEELER": return new TwoWheeler(number);
            case "4WHEELER": return new FourWheeler(number);
            default: return new NullVehicle();
        }
    }
}

// ParkingSpot interface and implementations
interface ParkingSpot {
    String getId();
    String getType();
    boolean isEmpty();
    void assignVehicle(Vehicle v);
}

class TwoWheelerSpot implements ParkingSpot {
    private String id;
    private boolean empty = true;
    private Vehicle vehicle;
    public TwoWheelerSpot(String id) { this.id = id; }
    public String getId() { return id; }
    public String getType() { return "2WHEELER"; }
    public boolean isEmpty() { return empty; }
    public void assignVehicle(Vehicle v) { this.vehicle = v; this.empty = false; }
}

class FourWheelerSpot implements ParkingSpot {
    private String id;
    private boolean empty = true;
    private Vehicle vehicle;
    public FourWheelerSpot(String id) { this.id = id; }
    public String getId() { return id; }
    public String getType() { return "4WHEELER"; }
    public boolean isEmpty() { return empty; }
    public void assignVehicle(Vehicle v) { this.vehicle = v; this.empty = false; }
}

// Null Object
class NullParkingSpot implements ParkingSpot {
    public String getId() { return "N/A"; }
    public String getType() { return "NONE"; }
    public boolean isEmpty() { return true; }
    public void assignVehicle(Vehicle v) { /* Do nothing */ }
}

// ParkingSpot Factory
class ParkingSpotFactory {
    public static ParkingSpot getSpot(String type, String id) {
        if (type == null) return new NullParkingSpot();
        switch (type.toUpperCase()) {
            case "2WHEELER": return new TwoWheelerSpot(id);
            case "4WHEELER": return new FourWheelerSpot(id);
            default: return new NullParkingSpot();
        }
    }
}

// ParkingStrategy interface and implementations
interface ParkingStrategy {
    ParkingSpot findSpot(List<ParkingSpot> spots);
}

// NearestSpotStrategy: finds the first available spot (default)
class NearestSpotStrategy implements ParkingStrategy {
    public ParkingSpot findSpot(List<ParkingSpot> spots) {
        for (ParkingSpot spot : spots) {
            if (spot.isEmpty()) {
                return spot;
            }
        }
        return new NullParkingSpot();
    }
}

// CheapestSpotStrategy: finds the available spot with the lowest price
class CheapestSpotStrategy implements ParkingStrategy {
    public ParkingSpot findSpot(List<ParkingSpot> spots) {
        ParkingSpot cheapest = new NullParkingSpot();
        double minPrice = Double.MAX_VALUE;
        for (ParkingSpot spot : spots) {
            if (spot.isEmpty() && spot instanceof TwoWheelerSpot || spot instanceof FourWheelerSpot) {
                if (spot instanceof TwoWheelerSpot) {
                    // Example: set price for demo
                    ((TwoWheelerSpot) spot).price = 10.0;
                } else if (spot instanceof FourWheelerSpot) {
                    ((FourWheelerSpot) spot).price = 20.0;
                }
                if (spot.price < minPrice) {
                    minPrice = spot.price;
                    cheapest = spot;
                }
            }
        }
        return cheapest;
    }
}

// PricingStrategy interface and implementations
interface PricingStrategy {
    double computeCost(ParkingSpot spot, long durationMinutes);
}

// Flat rate pricing
class FlatRatePricing implements PricingStrategy {
    private double rate;
    public FlatRatePricing(double rate) { this.rate = rate; }
    public double computeCost(ParkingSpot spot, long durationMinutes) {
        return rate;
    }
}

// Hourly pricing
class HourlyPricing implements PricingStrategy {
    private double hourlyRate;
    public HourlyPricing(double hourlyRate) { this.hourlyRate = hourlyRate; }
    public double computeCost(ParkingSpot spot, long durationMinutes) {
        return Math.ceil(durationMinutes / 60.0) * hourlyRate;
    }
}

// PricingStrategyFactory example
class PricingStrategyFactory {
    public static PricingStrategy getPricingStrategy(String type) {
        switch (type.toUpperCase()) {
            case "FLAT": return new FlatRatePricing(50.0);
            case "HOURLY": return new HourlyPricing(20.0);
            default: return new FlatRatePricing(50.0);
        }
    }
}

// Abstract ParkingSpotManager
abstract class ParkingSpotManager {
    protected List<ParkingSpot> spots;
    protected ParkingStrategy strategy;
    public ParkingSpotManager(List<ParkingSpot> spots, ParkingStrategy strategy) {
        this.spots = spots;
        this.strategy = strategy;
    }
    public ParkingSpot findAvailableSpot() {
        return strategy.findSpot(spots);
    }
    public void parkVehicle(ParkingSpot spot, Vehicle v) {
        spot.assignVehicle(v);
    }
    public void removeVehicle(ParkingSpot spot) {
        spot.assignVehicle(new NullVehicle());
    }
}

// TwoWheelerSpotManager
class TwoWheelerSpotManager extends ParkingSpotManager {
    public TwoWheelerSpotManager(List<ParkingSpot> spots, ParkingStrategy strategy) { super(spots, strategy); }
}

// FourWheelerSpotManager
class FourWheelerSpotManager extends ParkingSpotManager {
    public FourWheelerSpotManager(List<ParkingSpot> spots, ParkingStrategy strategy) { super(spots, strategy); }
}

// Entrance Gate 
class EntranceGate {
    private ParkingSpotManager manager;
    public EntranceGate(ParkingSpotManager manager) { this.manager = manager; }
    public Ticket admitVehicle(Vehicle v) {
        ParkingSpot spot = manager.findAvailableSpot();
        manager.parkVehicle(spot, v);
        Ticket ticket = new Ticket(System.currentTimeMillis(), spot, v);
        return ticket;
    }
}

// Exit Gate 
class ExitGate {
    private PricingStrategy pricingStrategy;
    private ParkingSpotManager manager;
    public ExitGate(String pricingType, ParkingSpotManager manager) {
        this.pricingStrategy = PricingStrategyFactory.getPricingStrategy(pricingType);
        this.manager = manager;
    }
    public double processExit(Ticket ticket) {
        long duration = (System.currentTimeMillis() - ticket.getEntryTime()) / 60000; // in minutes
        double cost = pricingStrategy.computeCost(ticket.getParkingSpot(), duration);
        manager.removeVehicle(ticket.getParkingSpot());
        return cost;
    }
}

// Ticket class for demonstration
class Ticket {
    private long entryTime;
    private ParkingSpot spot;
    private Vehicle vehicle;
    public Ticket(long entryTime, ParkingSpot spot, Vehicle vehicle) {
        this.entryTime = entryTime;
        this.spot = spot;
        this.vehicle = vehicle;
    }
    public long getEntryTime() { return entryTime; }
    public ParkingSpot getParkingSpot() { return spot; }
    public Vehicle getVehicle() { return vehicle; }
}

// Example usage with ParkingSpotFactory
public class Main {
    public static void main(String[] args) {
        // Create parking spots using ParkingSpotFactory
        List<ParkingSpot> twoWheelerSpots = new ArrayList<>();
        twoWheelerSpots.add(ParkingSpotFactory.getSpot("2WHEELER", "S1"));
        twoWheelerSpots.add(ParkingSpotFactory.getSpot("2WHEELER", "S2"));
        List<ParkingSpot> fourWheelerSpots = new ArrayList<>();
        fourWheelerSpots.add(ParkingSpotFactory.getSpot("4WHEELER", "S3"));
        fourWheelerSpots.add(ParkingSpotFactory.getSpot("4WHEELER", "S4"));

        ParkingStrategy nearest = new NearestSpotStrategy();
        ParkingStrategy cheapest = new CheapestSpotStrategy();

        ParkingSpotManager twoWheelerManager = new TwoWheelerSpotManager(twoWheelerSpots, nearest);
        ParkingSpotManager fourWheelerManager = new FourWheelerSpotManager(fourWheelerSpots, cheapest);

        // Entry process using EntranceGate
        EntranceGate entranceGate = new EntranceGate(twoWheelerManager); // Example for 2Wheeler
        Vehicle v1 = VehicleFactory.getVehicle("2WHEELER", "MH12AB1234");
        Ticket ticket1 = entranceGate.admitVehicle(v1);

        EntranceGate entranceGate2 = new EntranceGate(fourWheelerManager); // Example for 4Wheeler
        Vehicle v2 = VehicleFactory.getVehicle("4WHEELER", "MH12XY9999");
        Ticket ticket2 = entranceGate2.admitVehicle(v2);

        // Exit process using ExitGate and PricingStrategyFactory
        ExitGate exitGate = new ExitGate("FLAT", twoWheelerManager); // Flat pricing for 2Wheeler
        double cost1 = exitGate.processExit(ticket1);
        System.out.println(v1.getType() + ": " + v1.getNumber() + " -> Spot: " + ticket1.getParkingSpot().getId() + ", Cost: " + cost1);

        ExitGate exitGate2 = new ExitGate("HOURLY", fourWheelerManager); // Hourly pricing for 4Wheeler
        double cost2 = exitGate2.processExit(ticket2);
        System.out.println(v2.getType() + ": " + v2.getNumber() + " -> Spot: " + ticket2.getParkingSpot().getId() + ", Cost: " + cost2);
    }
}
```

---

## Why Not a Factory for ParkingSpotManager?
- In the current design, `ParkingSpotManager` instances (e.g., `TwoWheelerSpotManager`, `FourWheelerSpotManager`) are created directly in the code, not via a factory.
- This is often acceptable when:
    - The number and types of managers are fixed or known at compile time.
    - The construction logic is simple and does not require encapsulation or abstraction.
- **When to use a Factory for ParkingSpotManager:**
    - If you want to centralize and abstract the creation logic for different managers (e.g., based on configuration, vehicle type, or runtime conditions).
    - If you want to make the system more extensible for new manager types without changing client code.
    - If the construction of managers becomes complex (e.g., with dependencies, strategies, or external resources).
- **How to add a Factory:**
    - Create a `ParkingSpotManagerFactory` with a method like `getManager(String type, List<ParkingSpot> spots, ParkingStrategy strategy)` that returns the appropriate manager instance.

**Summary:**
- You can add a factory for `ParkingSpotManager` if you want more flexibility, abstraction, or extensibility.
- For simple or static scenarios, direct instantiation is fine.

---

**Key Takeaways:**
- EntranceGate and ExitGate use managers and strategies for extensible logic.
- PricingStrategyFactory (Factory Pattern) centralizes creation of pricing strategies, making it easy to add or change pricing rules.
- Factory Pattern is used for object creation throughout the design (vehicles, spots, pricing strategies).
- CostComputationFactor can be extended for dynamic pricing, discounts, or special rules.
- Each vehicle type can have its own ParkingSpotManager for specialized logic and extensibility.
- Factory Pattern centralizes object creation and makes the system extensible for new types.
- Null Object Pattern avoids null pointer exceptions and provides safe fallback behavior for unsupported types.
- Strategy Pattern allows dynamic change of parking spot assignment logic.
- ParkingSpotManager can also act as a factory for creating and managing parking spots for its vehicle type.
- This approach keeps the parking lot logic robust, extensible, and easy to maintain.


### doubts 
1. Is ParkingSpotManager (as an abstract class) part of the Factory Pattern?
Yes, it can be!
If ParkingSpotManager is responsible for creating and managing parking spots (e.g., createSpot() or addSpot() methods), then it acts as a factory for parking spots.
In this case, it encapsulates the creation logic for different types of parking spots, which is a key idea of the Factory Pattern.

2. Why use an abstract class instead of an interface for ParkingSpotManager?
Abstract Class:
Allows you to provide shared code (fields like List<ParkingSpot> spots, and common methods like parkVehicle()).
You can define both abstract methods (to be implemented by subclasses) and concrete methods (shared logic).
Useful when you want to avoid code duplication and provide a base implementation.

Interface:
Only defines method signatures (no fields, no shared code—except static/final fields and default methods in modern Java).
Use when you want to define a contract but don’t need to share code.

Summary:
Use an abstract class when you want to share code and state among all managers.
Use an interface if you only want to define a contract, and each implementation is completely independent.
In your parking lot file, using an abstract class for ParkingSpotManager is appropriate because it allows you to share common logic and state (like the list of spots) among all specific managers (e.g., TwoWheelerSpotManager, FourWheelerSpotManager).