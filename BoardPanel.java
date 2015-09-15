package coda;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class BoardPanel extends JPanel {

    private ArrayList<PiecePanel> pieces;

    private GameFrame gameFrame;

    public BoardPanel(Arbiter arbiter, GameFrame gameFrame) {
        super();

        this.gameFrame = gameFrame;

        this.setBackground(new Color(64, 196, 255));

        this.setLayout(new GridLayout(3, 9, 10, 10));

        this.setBorder(new EmptyBorder(10, 10, 10, 10));

        this.pieces = new ArrayList<>();

        Color[] colors = Game.pieceColors;
        String[] values = Game.pieceValues;

        for (Color color : colors) {
            for (String value : values) {
                this.pieces.add(new PiecePanel(arbiter, color, value, false));
            }
        }

        Collections.shuffle(this.pieces);

        for (int i = 0; i < this.pieces.size(); i++) {
            pieces.get(i).index = i;
            this.add(pieces.get(i));
        }
    }

    public PiecePanel popTile() throws NoTilesLeftException {
        if ( ! this.noTilesLeft()) {
            int pieceIndex = (int)(Math.random() * this.pieces.size());
            PiecePanel piece = this.pieces.remove(pieceIndex);

            JPanel emptyPanel = new JPanel();
            emptyPanel.setBackground(this.getBackground());

            this.add(emptyPanel, piece.index + 1);

            piece.isPicked = true;

            return piece;
        }
        throw new NoTilesLeftException();
    }

    public boolean noTilesLeft() {
        return this.pieces.size() == 0;
    }

    public void providePlayerWithTile(PiecePanel piece) {
        gameFrame.providePlayerWithTile(piece);
        piece.isPicked = true;
    }
}
