import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DirectionListener implements ActionListener {

    private GameFrame gameFrame;

    public DirectionListener(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ChoiceButton btnDirection = (ChoiceButton)actionEvent.getSource();

        Direction direction = Direction.valueOf(btnDirection.getText().toUpperCase());

        this.gameFrame.putDashTileAt(direction);
    }
}
