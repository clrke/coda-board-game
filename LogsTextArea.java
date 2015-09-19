import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.util.Date;

public class LogsTextArea extends JTextArea {

    public LogsTextArea() {
        this.setEditable(false);

        this.setRows(5);
    }

    @Override
    public void append(String message) {
        super.append(String.format("[%s] %s", new Date(), message));

        this.setCaretPosition(this.getText().length());
    }
}
