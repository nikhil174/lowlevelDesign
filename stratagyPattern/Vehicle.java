import strategy.DriveStrategy;

public class Vehicle {
    DriveStrategy driveObject;

    // this is known as constructor injection
    Vehicle(DriveStrategy driveObject) {
        this.driveObject = driveObject;
    }

    public void drive() {
        driveObject.drive();
    }
}
