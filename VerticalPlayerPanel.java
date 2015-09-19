package coda;

import java.awt.*;

public class VerticalPlayerPanel extends PlayerPanel {

    public VerticalPlayerPanel(Arbiter arbiter, String name, boolean isHuman) {
        super(arbiter, isHuman);

        this.setName(name);
        this.setLayout(new GridLayout(13, 1, 10, 10));

        super.add(new EmptyPanel(this.getBackground()));
    }

    public VerticalPlayerPanel(Arbiter arbiter, String name) {
        this(arbiter, name, false);
    }

    public Component add(PiecePanel piece, int index) {
        if (this.getFillersCount() > 0) {
            for (Component filler : this.getFillers()) {
                this.remove(filler);
            }
        }
        return super.add(piece, index);
    }
}

