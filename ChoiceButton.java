package coda;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ChoiceButton extends JButton {
    public ChoiceButton(String value, ActionListener actionListener) {
        super(value);

        this.addActionListener(actionListener);
    }
}
