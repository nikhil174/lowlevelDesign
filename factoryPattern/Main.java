// factory pattern is used when we want to create objects based on certain condition.
public class Main {
    public static void main(String[] args) {
        ShapeFactory shapeFactoryObj = new ShapeFactory();
        // Shape shapeObj = shapeFactoryObj.getShape("CIRCLE");
        Shape shapeObj = shapeFactoryObj.getShape("RECTANGLE");
        shapeObj.draw();
    }
}
