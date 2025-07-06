package practice.elevatorDesign.Lift;

import java.util.*;

import practice.elevatorDesign.Lift.DispatchStrategy.ElevatorDispatchStrategy;

// ElevatorSystem class (Singleton)
public class ElevatorSystem {
    private static ElevatorSystem instance;
    private List<Elevator> elevators;
    private List<Floor> floors;
    private ElevatorDispatchStrategy strategy;
    private Queue<Runnable> requestQueue = new LinkedList<>();
    private ElevatorSystem(int numFloors, int numElevators, ElevatorDispatchStrategy strategy) {
        this.floors = new ArrayList<>();
        for (int i = 0; i < numFloors; i++) floors.add(new Floor(i, this));
        this.elevators = new ArrayList<>();
        for (int i = 0; i < numElevators; i++) {
            Elevator elevator = new Elevator(numFloors);
            elevator.setSystem(this);
            elevators.add(elevator);
        }
        this.strategy = strategy;
    }
    public static ElevatorSystem getInstance(int numFloors, int numElevators, ElevatorDispatchStrategy strategy) {
        if (instance == null) instance = new ElevatorSystem(numFloors, numElevators, strategy);
        return instance;
    }
    public void setStrategy(ElevatorDispatchStrategy strategy) {
        this.strategy = strategy;
    }
    public void requestElevator(int floor, Direction direction) {
        requestQueue.add(() -> {
            Elevator elevator = strategy.selectElevator(elevators, floor, direction);
            if (elevator != null) {
                System.out.println("[Dispatch] Elevator " + elevator.getId() + " selected for floor " + floor + " (" + direction + ")");
                elevator.moveToFloor(floor);
                elevator.openDoor();
                elevator.closeDoor();
            } else {
                System.out.println("No elevator available for request at floor " + floor);
            }
        });
    }
    public void queueInternalRequest(Elevator elevator, int floor) {
        requestQueue.add(() -> {
            System.out.println("[Internal] Elevator " + elevator.getId() + " requested to floor " + floor);
            elevator.moveToFloor(floor);
            elevator.openDoor();
            elevator.closeDoor();
        });
    }
    public void processRequests() {
        while (!requestQueue.isEmpty()) {
            requestQueue.poll().run();
        }
    }
    public List<Floor> getFloors() { return floors; }
    public List<Elevator> getElevators() { return elevators; }
    // ...other system logic...
}
