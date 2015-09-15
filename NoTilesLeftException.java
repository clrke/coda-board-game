package coda;

public class NoTilesLeftException extends Exception {

    public NoTilesLeftException() {
        super("No tiles left but a player still requested for tile.");
    }
}
