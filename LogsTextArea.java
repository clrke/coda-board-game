package coda;

import javax.swing.*;

public class LogsTextArea extends JTextArea {

    public LogsTextArea() {
        this.setEditable(false);

        this.setRows(5);
    }
}
