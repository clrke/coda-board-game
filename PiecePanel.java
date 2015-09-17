package coda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PiecePanel extends JPanel {

    private Arbiter arbiter;
    public int index;
    public Color color;
    private String value;
    private boolean isRevealedToAll;
    private boolean isRevealedToPlayer;
    public boolean isPicked;

    private JLabel lblValue;

    public PiecePanel(Arbiter arbiter, Color color, String value, boolean isRevealedToAll) {
        this.setBackground(color);

        this.arbiter = arbiter;
        this.color = color;
        this.value = value;
        this.isRevealedToAll = isRevealedToAll;
        this.isPicked = false;

        this.lblValue = new JLabel(this.isRevealedToAll ? value : "    ");
        this.add(this.lblValue);

        this.lblValue.setForeground(this.color == Color.black ? Color.WHITE : Color.BLACK);

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                PiecePanel source = (PiecePanel) mouseEvent.getSource();

                if (source.isPicked) {
                    source.revealToPlayer();
                } else {
                    source.providePlayerWithTile(source);
                }
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

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
            this.lblValue.setText(this.value);

            this.getParent().validate();
        }
    }

    public void providePlayerWithTile(PiecePanel piece) {
        this.arbiter.providePlayerWithTile(piece);
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
