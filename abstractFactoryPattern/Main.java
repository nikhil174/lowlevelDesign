// factory of factory patterns
// creating objects which belong to a family of similar objects.

import UiFactory.MacUIFactory;
import UiFactory.WindowsUIFactory;

public class Main {
    public static void main(String[] args) {
        Application app = new Application(new MacUIFactory());
        app.paint();
        Application app2 = new Application(new WindowsUIFactory());
        app2.paint();
    }
}