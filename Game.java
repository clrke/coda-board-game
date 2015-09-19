import java.awt.*;

public class Game {

    public static Color[] pieceColors;
    public static String[] pieceValues;

    public static void initialize() {
        Game.pieceColors = new Color[] {
            Color.black,
            Color.white
        };

        Game.pieceValues = new String[] {
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "-"
        };

        GameFrame gameFrame = new GameFrame();

        gameFrame.setVisible(true);
    }

    public static void main(String[] args) {
        Game.initialize();
    }
}
