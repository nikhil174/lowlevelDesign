import UiFactory.UIFactory;
import components.Button;
import components.Checkbox;

public class Application {
    private Button button;
    private Checkbox checkbox;

    Application(UIFactory factory) {
        this.button = factory.createButton();
        this.checkbox = factory.createCheckbox();
    }

    public void paint() {
        button.paint();
        checkbox.paint();
    }
}
