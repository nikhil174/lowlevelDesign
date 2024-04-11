import Stratagy.DriveStratagy;

public class Vehicle {
    DriveStratagy driveObject;

    // this is know as constructor injection
    Vehicle(DriveStratagy driveObject) {
        this.driveObject = driveObject;
    }

    public void drive() {
        driveObject.drive();
    }
}
