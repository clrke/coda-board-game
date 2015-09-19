package coda;

import javax.swing.*;
import java.awt.*;

public class ChoicesPanel extends JPanel {

    public ChoicesPanel() {
        super();

        this.setLayout(new GridLayout(1, 2));
    }

    public void setButtons(ChoiceButton... buttons) {
        this.removeAll();
        for (ChoiceButton button : buttons) {
            this.add(button);
        }
    }
}
