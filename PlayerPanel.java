package coda;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class PlayerPanel extends JPanel {

    public boolean isHuman;
    private Arbiter arbiter;
    protected boolean isSuccessful;
    public int indexForDashTile;

    public PlayerPanel(Arbiter arbiter, boolean isHuman) {
        super();
        this.isHuman = isHuman;
        this.arbiter = arbiter;
        this.isSuccessful = false;
        this.indexForDashTile = -1;

        this.setBackground(new Color(86, 119, 252));

        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                PlayerPanel source = (PlayerPanel) mouseEvent.getSource();

                try {
                    PiecePanel pieceToAdd = source.requestForRandomTile();

                    if (source.isHuman) {
                        pieceToAdd.revealToPlayer();
                        if ( ! pieceToAdd.getValue().equals("-")) {
                            source.add(pieceToAdd);
                            source.getParent().validate();
                        }
                    } else {
                        source.add(pieceToAdd);
                        source.getParent().validate();
                    }

                } catch(NoTilesLeftException e) {
                    System.out.println(e.getMessage());
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

    public PiecePanel requestForRandomTile() throws NoTilesLeftException {
        return this.arbiter.requestForRandomTile();
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
            if (piece.getValue().equals("-")) {
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
}
