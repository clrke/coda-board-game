package coda;

import javax.swing.*;
import java.awt.*;

public class HorizontalPlayerPanel extends PlayerPanel {

    public HorizontalPlayerPanel(Arbiter arbiter, boolean isHuman) {
        super(arbiter, isHuman);

        this.setLayout(new GridLayout(2, 13, 10, 10));

        for(int i = 0; i < 26; i++) {
            JPanel pnlEmpty = new JPanel();
            pnlEmpty.setBackground(this.getBackground());
            this.add(pnlEmpty);
        }
    }

    public HorizontalPlayerPanel(Arbiter arbiter) {
        this(arbiter, false);
    }
}
