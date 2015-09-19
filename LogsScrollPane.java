import javax.swing.*;

public class LogsScrollPane extends JScrollPane {

    private LogsTextArea txtLogs;

    public LogsScrollPane(LogsTextArea txtLogs) {
        super(txtLogs);
        this.txtLogs = txtLogs;
    }

    public void log(String message) {
        this.txtLogs.append(message + "\n");
    }
}
