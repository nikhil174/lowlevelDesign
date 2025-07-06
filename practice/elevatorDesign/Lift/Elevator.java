package practice.elevatorDesign.Lift;

// Elevator class
public class Elevator {
    private static int idCounter = 0;
    private final int id;
    private InternalPanel internalPanel;
    private Status status = Status.IDLE;
    private Direction direction = Direction.IDLE;
    private int currentFloor = 0;
    private ElevatorSystem system;
    public Elevator(int numFloors) {
        this.id = ++idCounter;
        this.internalPanel = new InternalPanel(numFloors, this);
    }
    public void setSystem(ElevatorSystem system) { this.system = system; }
    public ElevatorSystem getSystem() { return system; }
    public int getId() { return id; }
    public void moveToFloor(int floor) {
        System.out.println("Elevator " + id + " moving to floor " + floor);
        currentFloor = floor;
        status = Status.MOVING;
        // ...simulate movement...
        status = Status.IDLE;
        internalPanel.display(currentFloor, direction);
    }
    public void openDoor() { System.out.println("Door opened at floor " + currentFloor); }
    public void closeDoor() { System.out.println("Door closed at floor " + currentFloor); }
    public int getCurrentFloor() { return currentFloor; }
    public Status getStatus() { return status; }
    public Direction getDirection() { return direction; }
    public InternalPanel getInternalPanel() { return internalPanel; }
}
