import javax.swing.*;
import java.awt.*;

public class PiecePanel extends JButton {

    private Arbiter arbiter;
    public int index;
    public Color color;
    private String value;
    private boolean isRevealedToAll;
    private boolean isRevealedToPlayer;
    public boolean isPicked;

    public PiecePanel(Arbiter arbiter, Color color, String value,
                      PieceActionListener actionListener, boolean isRevealedToAll) {
        this.setBackground(color);

        this.arbiter = arbiter;
        this.color = color;
        this.value = value;
        this.isRevealedToAll = isRevealedToAll;
        this.isPicked = false;

        this.setForeground(this.color == Color.black ? Color.WHITE : Color.BLACK);
        this.setText(" ");

        this.setFocusable(false);

        this.addActionListener(actionListener);
    }

    public PiecePanel(Arbiter arbiter, Color color, String value, PieceActionListener actionListener) {
        this(arbiter, color, value, actionListener, true);
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

    public boolean isRevealedToAll() {
        return this.isRevealedToAll;
    }

    public void revealToPlayer() {
        if ( ! this.isRevealedToPlayer) {
            this.isRevealedToPlayer = true;
            this.setText(this.value);

            this.getParent().validate();
        }
    }

    public boolean isRevealedToPlayer() {
        return this.isRevealedToPlayer;
    }

    public void provideToPlayer() {
        if (this.arbiter.allowsProvision()) {
            this.arbiter.providePlayerWithTile(this);
            this.revealToPlayer();
        }
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
