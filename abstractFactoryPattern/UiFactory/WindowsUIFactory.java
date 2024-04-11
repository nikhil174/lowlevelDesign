package UiFactory;

import components.Button;
import components.Checkbox;
import os.windows.WindowsButton;
import os.windows.WindowsCheckbox;

public class WindowsUIFactory implements UIFactory {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}
