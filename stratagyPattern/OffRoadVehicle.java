import Stratagy.NormalDriveStratagy;

public class OffRoadVehicle extends Vehicle {
    OffRoadVehicle() {
        super(new NormalDriveStratagy());
    }
}
