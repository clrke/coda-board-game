package coda;

import java.awt.*;

public class VerticalPlayerPanel extends PlayerPanel {

    public VerticalPlayerPanel(Arbiter arbiter, String name, boolean isHuman) {
        super(arbiter, isHuman);

        this.setName(name);
        this.setLayout(new GridLayout(13, 1, 10, 10));
    }

    public VerticalPlayerPanel(Arbiter arbiter, String name) {
        this(arbiter, name, false);
    }

    public Component add(PiecePanel piece, int index) {
        return super.add(piece, index);
    }
}

