package coda;

import javax.swing.*;
import java.util.Date;

public class LogsTextArea extends JTextArea {

    public LogsTextArea() {
        this.setEditable(false);

        this.setRows(5);
    }

    @Override
    public void append(String message) {
        super.append(String.format("[%s] %s", new Date(), message));
    }
}
