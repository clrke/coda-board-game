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
    private InformationPanel pnlInformation;
    private MainPanel pnlMain;

    private PlayerPanel[] pnlPlayers;
    private GameState status;

    public GameFrame() {
        super("Coda Board Game");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new BorderLayout());

        this.arbiter = new Arbiter();
        this.pnlMain = new MainPanel();
        this.pnlBoard = new BoardPanel(this.arbiter, this.pnlMain);
        this.pnlInformation = new InformationPanel();

        this.pnlMain.initialize(this.pnlBoard, this.pnlInformation, this);

        this.arbiter.setMainPanel(this.pnlMain);

        this.pnlPlayer1 = new HorizontalPlayerPanel(this.arbiter, "SOUTH PLAYER", true);
        this.pnlPlayer2 = new VerticalPlayerPanel(this.arbiter, "WEST PLAYER");
        this.pnlPlayer3 = new HorizontalPlayerPanel(this.arbiter, "NORTH PLAYER");
        this.pnlPlayer4 = new VerticalPlayerPanel(this.arbiter, "EAST PLAYER");

        this.add(pnlPlayer1, BorderLayout.SOUTH);
        this.add(pnlPlayer2, BorderLayout.WEST);
        this.add(pnlPlayer3, BorderLayout.NORTH);
        this.add(pnlPlayer4, BorderLayout.EAST);
        this.add(pnlMain, BorderLayout.CENTER);

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

    public void setStatus(GameState status) {
        this.status = status;

        if (status == GameState.PLAYER_MUST_CLICK_CONTINUE) {
            this.pnlInformation.setButtons(new ChoiceButton("Continue"));
        } else if (status == GameState.PLAYER_MUST_CHOOSE_POSITION_FOR_DASH_TILE) {
            this.pnlInformation.setButtons();
        }
    }

    public GameState getStatus() {
        return status;
    }
}
