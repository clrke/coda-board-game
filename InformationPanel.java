import javax.swing.*;
import java.awt.*;

public class InformationPanel extends JPanel{

    private LogsScrollPane txtLogs;
    private ChoicesPanel pnlOptions;

    public InformationPanel() {
        super();

        this.setLayout(new BorderLayout());

        this.txtLogs = new LogsScrollPane(new LogsTextArea());
        this.pnlOptions = new ChoicesPanel();

        this.add(this.txtLogs, BorderLayout.CENTER);
        this.add(this.pnlOptions, BorderLayout.SOUTH);
    }

    public void log(String message) {
        this.txtLogs.log(message);
    }

    public void setButtons(ChoiceButton... buttons) {
        this.pnlOptions.setButtons(buttons);
    }
}
