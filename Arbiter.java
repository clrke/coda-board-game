package coda;

public class Arbiter {

    private BoardPanel board;

    public Arbiter() { }

    public Arbiter(BoardPanel board) {
        this.board = board;
    }

    public PiecePanel requestForRandomTile() throws NoTilesLeftException {
        PiecePanel piece = this.board.popTile();

        this.board.validate();

        return piece;
    }

    public void providePlayerWithTile(PiecePanel piece) {
        board.providePlayerWithTile(piece);
    }

    public void setBoardPanel(BoardPanel boardPanel) {
        this.board = boardPanel;
    }
}
