package practice.elevatorDesign.Lift;

// ExternalPanel class
public class ExternalPanel {
    private boolean upPressed = false;
    private boolean downPressed = false;
    private int floorNumber;
    private ElevatorSystem system;
    public ExternalPanel(int floorNumber, ElevatorSystem system) {
        this.floorNumber = floorNumber;
        this.system = system;
    }
    public void pressUp() {
        upPressed = true;
        System.out.println("ExternalPanel: Request UP from floor " + floorNumber);
        system.requestElevator(floorNumber, Direction.UP);
    }
    public void pressDown() {
        downPressed = true;
        System.out.println("ExternalPanel: Request DOWN from floor " + floorNumber);
        system.requestElevator(floorNumber, Direction.DOWN);
    }
    public boolean isUpPressed() { return upPressed; }
    public boolean isDownPressed() { return downPressed; }
    public ElevatorSystem getSystem() { return system; }
}
