package coda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContinueListener implements ActionListener {

    private GameFrame gameFrame;

    public ContinueListener(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.gameFrame.resume();
    }
}
