package coda;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    private GameFrame gameFrame;
    private BoardPanel pnlBoard;
    private InformationPanel pnlInformation;

    public MainPanel() {
        super();
    }

    public void initialize(BoardPanel pnlBoard, InformationPanel pnlInformation, GameFrame gameFrame) {
        this.pnlBoard = pnlBoard;
        this.pnlInformation = pnlInformation;
        this.gameFrame = gameFrame;

        this.setLayout(new BorderLayout());

        this.add(pnlBoard, BorderLayout.CENTER);
        this.add(pnlInformation, BorderLayout.SOUTH);
    }

    public PiecePanel requestForRandomTile(PlayerPanel requester) {
        try {
            PiecePanel piece = this.pnlBoard.popTile();

            this.pnlInformation.log(requester.getName() + " picked up a " + piece.getColorName() + " tile!");

            return piece;
        } catch (NoTilesLeftException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void providePlayerWithTile(PiecePanel piece) {
        pnlBoard.providePlayerWithTile(piece);

        if (this.gameFrame.getStatus() == GameState.PLAYER_MUST_CLICK_CONTINUE) {
            this.pnlInformation.log("You picked up " + piece + "!");
        } else if (this.gameFrame.getStatus() == GameState.PLAYER_MUST_CHOOSE_POSITION_FOR_DASH_TILE) {
            this.pnlInformation.log("You must choose a position for " + piece + "!");
        }
    }

    public PlayerPanel getPlayer() {
        return gameFrame.getPlayer();
    }

    public void setStatus(GameState status) {
        this.gameFrame.setStatus(status);
    }
}
