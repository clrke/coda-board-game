package coda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberListener implements ActionListener {

    private GameFrame gameFrame;

    public NumberListener(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ChoiceButton btnNumber = (ChoiceButton)actionEvent.getSource();

        String guess = btnNumber.getText();

        this.gameFrame.performPlayerGuess(guess);
    }
}
