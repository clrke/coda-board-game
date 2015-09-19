import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class PlayerPanel extends JPanel {

    public boolean isHuman;
    private Arbiter arbiter;
    protected boolean isSuccessful;
    protected int indexForDashTile;

    public PlayerPanel(Arbiter arbiter, boolean isHuman) {
        super();
        this.isHuman = isHuman;
        this.arbiter = arbiter;
        this.isSuccessful = false;
        this.indexForDashTile = -1;

        this.setBackground(new Color(86, 119, 252));

        this.setBorder(new EmptyBorder(10, 10, 10, 10));
    }

    public PiecePanel requestForRandomTile() throws NoTilesLeftException {
        return this.arbiter.requestForRandomTile(this);
    }

    public ArrayList<PiecePanel> getPieces() {
        ArrayList<PiecePanel> pieces = new ArrayList<>();

        for (Component component : this.getComponents()) {
            if (component instanceof PiecePanel) {
                pieces.add((PiecePanel) component);
            }
        }

        return pieces;
    }

    public int getPiecesCount() {
        int count = 0;

        for (Component component : this.getComponents()) {
            if (component instanceof PiecePanel) {
                count++;
            }
        }

        return count;
    }

    public ArrayList<Component> getFillers() {
        ArrayList<Component> fillers = new ArrayList<>();

        for (Component component : this.getComponents()) {
            if (!(component instanceof PiecePanel)) {
                fillers.add(component);
            }
        }

        return fillers;
    }

    public int getFillersCount() {
        int count = 0;

        for (Component component : this.getComponents()) {
            if (!(component instanceof PiecePanel)) {
                count++;
            }
        }

        return count;
    }

    public boolean isDefeated() {
        return this.getPiecesCount() == 0;
    }

    public int getIndexFor(PiecePanel piece) {
        if (piece.getValue().equals("-")) {
            return (int) (Math.random() * this.getPiecesCount());
        }
        ArrayList<PiecePanel> pieces = this.getPieces();
        int i = 0;
        for (; i < pieces.size(); i++) {
            PiecePanel piece2 = pieces.get(i);

            if ( ! piece2.getValue().equals("-")) {
                int value1 = Integer.parseInt(piece.getValue());
                int value2 = Integer.parseInt(piece2.getValue());
                if (value1 == value2) {
                    if (piece.color == Color.black) {
                        return i;
                    }

                    return i+1;
                } else if (value1 < value2) {
                    return i;
                }
            }
        }
        return i;
    }

    public void adjust() {
    }

    public Component add(PiecePanel piece, int index) {
        if (this.getPiecesCount() > 0 || index == this.getComponentCount()) {
            super.add(piece, index);
        } else {
            super.add(piece);
        }
        this.isSuccessful = true;
        this.adjust();

        return piece;
    }

    public Component add(PiecePanel piece) {
        if (this.isHuman) {
            if (piece.getValue().equals("-") && this.getPiecesCount() > 0 && ! piece.isRevealedToAll()) {
                if (this.indexForDashTile < 0) {
                    this.isSuccessful = false;
                } else {
                    this.add(piece, this.indexForDashTile);
                    this.indexForDashTile = -1;
                }
            } else {
                this.add(piece, this.getIndexFor(piece));
            }
        } else {
            this.add(piece, this.getIndexFor(piece));
        }
        return piece;
    }

    public boolean isSuccessful() {
        return this.isSuccessful;
    }

    protected int getIndexOf(Component component) {
        int i;
        Component[] components = this.getComponents();
        for (i = 0; i < this.getComponentCount(); i++) {
            if (components[i] == component) {
                return i;
            }
        }
        return i;
    }

    public void setIndexForDashTile(PiecePanel piecePanel) {
        this.setIndexForDashTile(this.getIndexOf(piecePanel));
    }

    public void setIndexForDashTile(int index, boolean definite) {
        this.indexForDashTile = index;
    }

    public void setIndexForDashTile(int index) {
        this.setIndexForDashTile(index, false);
    }

    public int getIndexForDashTile() {
        return this.indexForDashTile;
    }
}
