import Stratagy.SportsDriveStratagy;

public class SportsDriveVehicle extends Vehicle {
    SportsDriveVehicle() {
        super(new SportsDriveStratagy());
    }
}
