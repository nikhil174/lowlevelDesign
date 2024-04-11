package UiFactory;

import components.Button;
import components.Checkbox;

public interface UIFactory {
    Button createButton();
    Checkbox createCheckbox();
}
