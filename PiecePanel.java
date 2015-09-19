package coda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PiecePanel extends JButton {

    private Arbiter arbiter;
    public int index;
    public Color color;
    private String value;
    private boolean isRevealedToAll;
    private boolean isRevealedToPlayer;
    public boolean isPicked;

    public PiecePanel(Arbiter arbiter, Color color, String value, boolean isRevealedToAll) {
        this.setBackground(color);

        this.arbiter = arbiter;
        this.color = color;
        this.value = value;
        this.isRevealedToAll = isRevealedToAll;
        this.isPicked = false;

        this.setForeground(this.color == Color.black ? Color.WHITE : Color.BLACK);
        this.setText(" ");

        this.setFocusable(false);

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                PiecePanel piece = (PiecePanel) actionEvent.getSource();

                if (piece.isPicked) {
                    piece.revealToPlayer();
                } else {
                    piece.provideToPlayer();
                }
            }
        });
    }

    public PiecePanel(Arbiter arbiter, Color color, String value) {
        this(arbiter, color, value, true);
    }

    public String getValue() {
        return this.value;
    }

    public void revealToAll() {
        if (! this.isRevealedToAll) {
            this.isRevealedToAll = true;
            this.revealToPlayer();
        }
    }

    public void revealToPlayer() {
        if ( ! this.isRevealedToPlayer) {
            this.isRevealedToPlayer = true;
            this.setText(this.value);

            this.getParent().validate();
        }
    }

    public void provideToPlayer() {
        this.arbiter.providePlayerWithTile(this);
        this.revealToPlayer();
    }

    public String getColorShortcut() {
        if (this.color.getRed() == 0) {
            return "B";
        } else {
            return "W";
        }
    }

    public String getColorName() {
        if (this.color.getRed() == 0) {
            return "Black";
        } else {
            return "White";
        }
    }

    @Override
    public String toString() {
        return this.getColorShortcut() + this.getValue();
    }
}
