package coda;

import javax.swing.JFrame;
import java.awt.BorderLayout;


public class GameFrame extends JFrame {

    private Arbiter arbiter;

    private HorizontalPlayerPanel pnlPlayer1;
    private VerticalPlayerPanel pnlPlayer2;
    private HorizontalPlayerPanel pnlPlayer3;
    private VerticalPlayerPanel pnlPlayer4;
    private BoardPanel pnlBoard;

    private PlayerPanel[] pnlPlayers;

    public GameFrame() {
        super("Coda Board Game");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new BorderLayout());

        this.arbiter = new Arbiter();
        this.pnlBoard = new BoardPanel(this.arbiter, this);

        this.arbiter.setBoardPanel(this.pnlBoard);

        this.pnlPlayer1 = new HorizontalPlayerPanel(this.arbiter, true);
        this.pnlPlayer2 = new VerticalPlayerPanel(this.arbiter);
        this.pnlPlayer3 = new HorizontalPlayerPanel(this.arbiter);
        this.pnlPlayer4 = new VerticalPlayerPanel(this.arbiter);

        this.add(pnlPlayer1, BorderLayout.SOUTH);
        this.add(pnlPlayer2, BorderLayout.WEST);
        this.add(pnlPlayer3, BorderLayout.NORTH);
        this.add(pnlPlayer4, BorderLayout.EAST);
        this.add(pnlBoard, BorderLayout.CENTER);

        this.pnlPlayers = new PlayerPanel[]{pnlPlayer1, pnlPlayer2, pnlPlayer3, pnlPlayer4};

        this.setSize(1000, 700);
    }

    public PlayerPanel getPlayer() {
        for (PlayerPanel player : pnlPlayers) {
            if (player.isHuman) {
                return player;
            }
        }
        return pnlPlayers[0];
    }
}
