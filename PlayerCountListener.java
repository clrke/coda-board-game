package coda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerCountListener implements ActionListener {

    private GameFrame gameFrame;

    public PlayerCountListener(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ChoiceButton btnPlayerCount = (ChoiceButton)actionEvent.getSource();

        int playersCount = Integer.parseInt(btnPlayerCount.getText());

        this.gameFrame.setNumberOfPlayers(playersCount);
    }
}
