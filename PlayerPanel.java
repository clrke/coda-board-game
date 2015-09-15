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

    public PlayerPanel(Arbiter arbiter, boolean isHuman) {
        super();
        this.isHuman = isHuman;
        this.arbiter = arbiter;

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
                    }

                    source.add(pieceToAdd);
                    source.getParent().validate();
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
}
