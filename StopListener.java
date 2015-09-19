package coda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StopListener implements ActionListener {

    private GameFrame gameFrame;

    public StopListener(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.gameFrame.stopGuessPhase();
    }
}
