package practice.elevatorDesign.Lift.DispatchStrategy;

import java.util.*;
import practice.elevatorDesign.Lift.Elevator;
import practice.elevatorDesign.Lift.Direction;
import practice.elevatorDesign.Lift.Status;

// DirectionalStrategy (example)
public class DirectionalStrategy implements ElevatorDispatchStrategy {
    private static DirectionalStrategy instance;
    private DirectionalStrategy() {}
    public static DirectionalStrategy getInstance() {
        if (instance == null) instance = new DirectionalStrategy();
        return instance;
    }
    @Override
    public Elevator selectElevator(List<Elevator> elevators, int requestedFloor, Direction direction) {
        // Simple logic: pick first idle or closest elevator in same direction
        Elevator best = null;
        int minDist = Integer.MAX_VALUE;
        for (Elevator e : elevators) {
            if (e.getStatus() == Status.IDLE || e.getDirection() == direction) {
                int dist = Math.abs(e.getCurrentFloor() - requestedFloor);
                if (dist < minDist) {
                    minDist = dist;
                    best = e;
                }
            }
        }
        return best;
    }
}
