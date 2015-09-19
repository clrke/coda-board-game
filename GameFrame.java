package coda;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Collections;


public class GameFrame extends JFrame {

    private Arbiter arbiter;

    private HorizontalPlayerPanel pnlPlayer1;
    private VerticalPlayerPanel pnlPlayer2;
    private HorizontalPlayerPanel pnlPlayer3;
    private VerticalPlayerPanel pnlPlayer4;
    private BoardPanel pnlBoard;
    private InformationPanel pnlInformation;
    private MainPanel pnlMain;
    private ChoiceButton btnContinue;
    private ChoiceButton btnStop;
    private ChoiceButton[] btnPlayerCounts;
    private ChoiceButton[] btnDirections;
    private ChoiceButton[] btnNumbers;

    private PlayerPanel[] pnlPlayers;
    private GameState status;

    private ContinueListener continueListener;
    private StopListener stopListener;
    private PlayerCountListener playerCountListener;
    private DirectionListener directionListener;
    private NumberListener numberListener;

    private int roundNumber;
    private int roundNumberOfStartGame;

    private int currentPlayerIndex = 0;

    private Phase currentPhase;
    private boolean isFirstGuess;
    private PiecePanel chosenTile;

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

        this.continueListener = new ContinueListener(this);
        this.stopListener = new StopListener(this);
        this.playerCountListener = new PlayerCountListener(this);
        this.directionListener = new DirectionListener(this);
        this.numberListener = new NumberListener(this);

        this.btnContinue = new ChoiceButton("Continue", this.continueListener);
        this.btnStop = new ChoiceButton("Stop", this.stopListener);
        this.btnPlayerCounts = new ChoiceButton[] {
                new ChoiceButton("2", this.playerCountListener),
                new ChoiceButton("3", this.playerCountListener),
                new ChoiceButton("4", this.playerCountListener),
        };
        this.btnDirections = new ChoiceButton[] {
                new ChoiceButton("Left", this.directionListener),
                new ChoiceButton("Right", this.directionListener)
        };
        this.btnNumbers = new ChoiceButton[] {
                new ChoiceButton("1", this.numberListener),
                new ChoiceButton("2", this.numberListener),
                new ChoiceButton("3", this.numberListener),
                new ChoiceButton("4", this.numberListener),
                new ChoiceButton("5", this.numberListener),
                new ChoiceButton("6", this.numberListener),
                new ChoiceButton("7", this.numberListener),
                new ChoiceButton("8", this.numberListener),
                new ChoiceButton("9", this.numberListener),
                new ChoiceButton("10", this.numberListener),
                new ChoiceButton("11", this.numberListener),
                new ChoiceButton("12", this.numberListener),
                new ChoiceButton("-", this.numberListener),
        };

        this.pnlInformation.log("New Game! Please choose number of players:\n");

        this.setStatus(GameState.PLAYER_MUST_CHOOSE_NUMBER_OF_PLAYERS);

        this.roundNumber = 1;
        this.roundNumberOfStartGame = 4;

        this.setSize(1000, 700);
    }

    public void setNumberOfPlayers(int playersCount) {
        this.pnlInformation.log("Number of players for this game: " + playersCount + "\n");

        switch (playersCount) {
            case 2:
                this.pnlPlayers = new PlayerPanel[] {
                        this.pnlPlayer1,
                        this.pnlPlayer3,
                };
                this.roundNumberOfStartGame = 5;
                break;
            case 3:
                this.pnlPlayers = new PlayerPanel[] {
                        this.pnlPlayer1,
                        this.pnlPlayer2,
                        this.pnlPlayer4,
                };
                this.roundNumberOfStartGame = 5;
                break;
            case 4:
                this.pnlPlayers = new PlayerPanel[] {
                        this.pnlPlayer1,
                        this.pnlPlayer2,
                        this.pnlPlayer3,
                        this.pnlPlayer4,
                };
                this.roundNumberOfStartGame = 4;
                break;
            default:
                this.pnlPlayers = new PlayerPanel[]{
                        this.pnlPlayer1
                };
                break;
        }

        this.shufflePlayers();

        this.logPlayers();

        this.setStatus(GameState.PLAYER_MUST_CLICK_CONTINUE);
        this.currentPhase = Phase.PICK;
    }

    public void shufflePlayers() {
        ArrayList<PlayerPanel> list = new ArrayList<>();
        for (PlayerPanel player : this.pnlPlayers) {
            list.add(player);
        }

        Collections.shuffle(list);

        for (int i = 0; i < list.size(); i++) {
            this.pnlPlayers[i] = list.get(i);
        }
    }

    private void logPlayers() {
        for(PlayerPanel player : this.pnlPlayers) {
            this.pnlInformation.log(player.getName());
        }
    }

    public PlayerPanel getPlayer() {
        for (PlayerPanel player : pnlPlayers) {
            if (player.isHuman) {
                return player;
            }
        }
        return pnlPlayers[0];
    }

    public PlayerPanel getCurrentPlayer() {
        return this.pnlPlayers[this.currentPlayerIndex];
    }

    public PlayerPanel nextPlayer() {
        if (this.currentPlayerIndex + 1 == this.pnlPlayers.length) {
            this.roundNumber++;
        }

        this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.pnlPlayers.length;

        return getCurrentPlayer();
    }

    public int getRoundNumber() {
        return this.roundNumber;
    }

    public void resume() {
        PlayerPanel player = this.getCurrentPlayer();

        if (this.currentPhase == Phase.PICK && this.pnlBoard.noTilesLeft()) {
            this.stopPickPhase();
        }

        if (this.currentPhase == Phase.PICK) {
            if (player.isHuman) {
                this.setStatus(GameState.PLAYER_MUST_PICK_A_TILE);
                this.pnlInformation.log("Your turn! Please pick a tile.");
            } else {
                this.setStatus(GameState.PLAYER_MUST_CLICK_CONTINUE);
                try {
                    PiecePanel piece = player.requestForRandomTile();
                    player.add(piece);
                    player.getParent().validate();
                } catch (NoTilesLeftException e) {
                    System.out.println(e.getMessage());
                }
                this.currentPhase = Phase.GUESS;
            }
        } else if (this.roundNumber >= this.roundNumberOfStartGame && this.currentPhase == Phase.GUESS) {
            if (player.isHuman) {
                this.setStatus(GameState.PLAYER_MUST_CHOOSE_OPPONENT_TILE);
                this.pnlInformation.log("Please guess one of your opponent's tiles.");
            } else {
                if (this.isFirstGuess) {
                    this.setStatus(GameState.PLAYER_MUST_CLICK_CONTINUE);
                    this.pnlInformation.log(player.getName() + " has guessed but it's wrong.");
                } else {
                    this.stopGuessPhase();
                }
            }
            this.isFirstGuess = false;
        } else {

            if (this.pnlBoard.noTilesLeft()) {
                do {
                    player = this.nextPlayer();
                } while (player.isDefeated());
            } else {
                player = this.nextPlayer();
            }

            this.currentPhase = Phase.PICK;

            this.setStatus(GameState.PLAYER_MUST_CLICK_CONTINUE);
            this.pnlInformation.log(player.getName() + "'s turn!");
        }
    }

    public void setStatus(GameState status) {
        this.status = status;

        if (status == GameState.PLAYER_MUST_CHOOSE_NUMBER_OF_PLAYERS) {
            this.pnlInformation.setButtons(this.btnPlayerCounts);
        } else if (status == GameState.PLAYER_MUST_CLICK_CONTINUE) {
            this.pnlInformation.setButtons(this.btnContinue);
        } else if (status == GameState.PLAYER_MUST_GUESS_OPPONENT_TILE) {
            this.pnlInformation.setButtons(this.btnNumbers);
        } else if (status == GameState.PLAYER_MUST_CHOOSE_POSITION_FOR_DASH_TILE_RELATIVE_TO_CHOSEN_TILE) {
            this.pnlInformation.setButtons(this.btnDirections);
        } else if (status == GameState.PLAYER_MUST_CHOOSE_OPPONENT_TILE) {
            if (this.isFirstGuess) {
                this.btnStop.setEnabled(false);
            } else {
                this.btnStop.setEnabled(true);
            }
            this.pnlInformation.setButtons(this.btnStop);
        } else if (status == GameState.PLAYER_MUST_PICK_A_TILE) {
            this.pnlInformation.setButtons();
        } else if (status == GameState.PLAYER_MUST_CHOOSE_POSITION_FOR_DASH_TILE) {
            this.pnlInformation.setButtons();
        }

        this.pnlInformation.validate();
    }

    public GameState getStatus() {
        return status;
    }

    public void stopPickPhase() {
        this.currentPhase = Phase.GUESS;
        this.isFirstGuess = true;
    }

    public void stopGuessPhase() {
        this.currentPhase = Phase.STOP;
        this.resume();
    }

    public void putDashTileAt(Direction direction) {
        PlayerPanel player = this.getPlayer();

        if (direction == Direction.RIGHT) {
            int indexForDashTile = player.getIndexForDashTile();
            player.setIndexForDashTile(indexForDashTile + 1, true);
        }

        this.pnlMain.providePlayerWithTile(this.chosenTile);

        this.validate();
    }

    public void performPlayerGuess(String guess) {
        PiecePanel chosenTile = this.chosenTile;
        PlayerPanel player = this.getPlayer();

        if (chosenTile.getValue().equals(guess)) {
            this.pnlInformation.log("Correct guess!");
            chosenTile.revealToAll();
            this.setStatus(GameState.PLAYER_MUST_CLICK_CONTINUE);
        } else {
            this.pnlInformation.log("Incorrect guess! It was a " + chosenTile.toString() + "!");
            chosenTile.revealToAll();
            player.add(chosenTile);

            this.validate();

            stopGuessPhase();
        }
    }

    public void setChosenTile(PiecePanel chosenTile) {
        this.chosenTile = chosenTile;
    }
}
