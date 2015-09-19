package coda;

public class Arbiter {

    private MainPanel pnlMain;

    public Arbiter() { }

    public Arbiter(MainPanel pnlMain) {
        this.pnlMain = pnlMain;
    }

    public PiecePanel requestForRandomTile(PlayerPanel requester) throws NoTilesLeftException {
        PiecePanel piece = this.pnlMain.requestForRandomTile(requester);

        this.pnlMain.validate();

        return piece;
    }

    public void providePlayerWithTile(PiecePanel piece) {
        pnlMain.providePlayerWithTile(piece);
    }

    public void setMainPanel(MainPanel pnlMain) {
        this.pnlMain = pnlMain;
    }

    public boolean allowsProvision() {
        return this.pnlMain.getStatus() == GameState.PLAYER_MUST_PICK_A_TILE;
    }
}
