package coda;

import java.awt.*;

public class VerticalPlayerPanel extends PlayerPanel {

    public VerticalPlayerPanel(Arbiter arbiter, boolean isHuman) {
        super(arbiter, isHuman);

        this.setLayout(new GridLayout(13, 1, 10, 10));
    }

    public VerticalPlayerPanel(Arbiter arbiter) {
        this(arbiter, false);
    }
}

