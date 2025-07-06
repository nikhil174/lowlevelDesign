package practice.elevatorDesign.Lift;

// Floor class
public class Floor {
    private int floorNumber;
    private ExternalPanel externalPanel;
    private ElevatorSystem system;
    public Floor(int floorNumber, ElevatorSystem system) {
        this.floorNumber = floorNumber;
        this.system = system;
        this.externalPanel = new ExternalPanel(floorNumber, system);
    }
    public int getFloorNumber() { return floorNumber; }
    public ExternalPanel getExternalPanel() { return externalPanel; }
    public ElevatorSystem getSystem() { return system; }
}
