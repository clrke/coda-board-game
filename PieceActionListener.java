import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PieceActionListener implements ActionListener {

    private MainPanel pnlMain;

    public PieceActionListener(MainPanel pnlMain) {
        this.pnlMain = pnlMain;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        PiecePanel piece = (PiecePanel) actionEvent.getSource();
        GameState status = pnlMain.getStatus();

        switch (status) {
            case PLAYER_MUST_PICK_A_TILE:
                if ( ! piece.isPicked) {
                    piece.provideToPlayer();
                }
                break;
            case PLAYER_MUST_CHOOSE_OPPONENT_TILE:
            case PLAYER_MUST_GUESS_OPPONENT_TILE:
                if (piece.isPicked) {
                    pnlMain.setChosenTile(piece);
                    pnlMain.setStatus(GameState.PLAYER_MUST_GUESS_OPPONENT_TILE);
                }
                break;
            case PLAYER_MUST_CHOOSE_POSITION_FOR_DASH_TILE:
                if (piece.isPicked) {
                    pnlMain.getPlayer().setIndexForDashTile(piece);
                    pnlMain.setStatus(GameState.PLAYER_MUST_CHOOSE_POSITION_FOR_DASH_TILE_RELATIVE_TO_CHOSEN_TILE);
                }
                break;
        }
    }
}
