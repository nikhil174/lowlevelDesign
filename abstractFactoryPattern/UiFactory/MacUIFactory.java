package UiFactory;

import components.Button;
import components.Checkbox;
import os.mac.MacButton;
import os.mac.MacCheckbox;

public class MacUIFactory implements UIFactory {
    @Override
    public Button createButton() {
        return new MacButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }
}
