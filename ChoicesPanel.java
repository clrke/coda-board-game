package coda;

import javax.swing.*;
import java.awt.*;

public class ChoicesPanel extends JPanel {

    public ChoicesPanel() {
        super();

        this.setLayout(new GridLayout(1, 2));

        this.add(new ChoiceButton("Continue"));
    }
}