package practice.elevatorDesign.Lift;

import java.util.*;

// InternalPanel class
public class InternalPanel {
    private int currentFloor;
    private Direction direction;
    private List<Integer> floorButtons;
    private Elevator elevator; // reference to the owning elevator
    public InternalPanel(int numFloors, Elevator elevator) {
        this.currentFloor = 0;
        this.direction = Direction.IDLE;
        this.floorButtons = new ArrayList<>();
        for (int i = 0; i < numFloors; i++) floorButtons.add(i);
        this.elevator = elevator;
    }
    public void display(int floor, Direction dir) {
        this.currentFloor = floor;
        this.direction = dir;
        System.out.println("Floor: " + currentFloor + ", Direction: " + direction);
    }
    public void requestFloor(int floor) {
        System.out.println("InternalPanel: Request to floor " + floor);
        elevator.getSystem().queueInternalRequest(elevator, floor);
    }
}
