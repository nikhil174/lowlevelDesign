package practice.elevatorDesign.Lift.DispatchStrategy;

import java.util.*;
import practice.elevatorDesign.Lift.Elevator;
import practice.elevatorDesign.Lift.Direction;

// ElevatorDispatchStrategy interface
public interface ElevatorDispatchStrategy {
    Elevator selectElevator(List<Elevator> elevators, int requestedFloor, Direction direction);
}
