import strategy.SportsDriveStrategy;

public class SportsDriveVehicle extends Vehicle {
    SportsDriveVehicle() {
        super(new SportsDriveStrategy());
    }
}
