package coda;

import java.awt.*;

public class HorizontalPlayerPanel extends PlayerPanel {

    public HorizontalPlayerPanel(Arbiter arbiter, boolean isHuman) {
        super(arbiter, isHuman);

        this.setLayout(new GridLayout(2, 13, 10, 10));

        for(int i = 0; i < 26; i++) {
            super.add(new EmptyPanel(this.getBackground()));
        }
    }

    public HorizontalPlayerPanel(Arbiter arbiter) {
        this(arbiter, false);
    }

    @Override
    public Component add(Component component) {
        if (this.getPiecesCount() % 2 != 0) {
            this.remove(0);
        } else {
            super.add(new EmptyPanel(this.getBackground()), 0);
        }
        super.add(component);
        return component;
    }
}
