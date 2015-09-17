package coda;

import java.awt.*;

public class HorizontalPlayerPanel extends PlayerPanel {

    public HorizontalPlayerPanel(Arbiter arbiter, String name, boolean isHuman) {
        super(arbiter, isHuman);

        this.setName(name);
        this.setLayout(new GridLayout(2, 13, 10, 10));

        for (int i = 0; i < 26; i++) {
            super.add(new EmptyPanel(this.getBackground()));
        }
    }

    public HorizontalPlayerPanel(Arbiter arbiter, String name) {
        this(arbiter, name, false);
    }

    public Component add(PiecePanel piece, int index) {
        return super.add(piece, index+this.getFillersCount());
    }

    @Override
    public void adjust() {
        if (this.getPiecesCount() % 2 != 0) {
            this.remove(0);
        } else {
            super.add(new EmptyPanel(this.getBackground()), 0);
        }
    }
}
