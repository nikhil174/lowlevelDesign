package practice.elevatorDesign;

import practice.elevatorDesign.Lift.ElevatorSystem;
import practice.elevatorDesign.Lift.DispatchStrategy.DirectionalStrategy;
// Main class for demonstration
public class Main {
    public static void main(String[] args) {
        ElevatorSystem system = ElevatorSystem.getInstance(5, 2, DirectionalStrategy.getInstance());
        // Initial state
        for (int i = 0; i < system.getElevators().size(); i++) {
            System.out.println("Elevator " + i + " is at floor " + system.getElevators().get(i).getCurrentFloor() +
                    " with status " + system.getElevators().get(i).getStatus() +
                    " and direction " + system.getElevators().get(i).getDirection());
        }

        // Complex requests
        // External requests
        system.getFloors().get(1).getExternalPanel().pressUp();   // Someone at floor 1 presses UP
        system.getElevators().get(0).getInternalPanel().requestFloor(4); // Elevator 0: go to 4
        system.getFloors().get(4).getExternalPanel().pressDown(); // Someone at floor 4 presses DOWN
        system.getFloors().get(1).getExternalPanel().pressUp();   // Someone at floor 2 presses UP
        system.getFloors().get(0).getExternalPanel().pressUp();   // Someone at ground floor presses UP
        system.getFloors().get(3).getExternalPanel().pressDown(); // Someone at floor 3 presses DOWN

        // Internal requests (simulate passengers inside elevators)
        system.getElevators().get(1).getInternalPanel().requestFloor(2); // Elevator 1: go to 2
        system.getElevators().get(0).getInternalPanel().requestFloor(1); // Elevator 0: go to 1
        system.getElevators().get(1).getInternalPanel().requestFloor(0); // Elevator 1: go to 0

        // Final state
        system.processRequests();
        for (int i = 0; i < system.getElevators().size(); i++) {
            System.out.println("Elevator " + i + " is at floor " + system.getElevators().get(i).getCurrentFloor() +
                    " with status " + system.getElevators().get(i).getStatus() +
                    " and direction " + system.getElevators().get(i).getDirection());
        }
    }
}
