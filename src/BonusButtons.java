import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BonusButtons extends JPanel {


    private JButton openButton, saveButton;
    private DrawingArea drawingArea;

    public BonusButtons(DrawingArea drawingArea) {
        this.drawingArea=drawingArea;

        setLayout(new FlowLayout());

        saveButton = new JButton("Save");
        openButton = new JButton("Open");

        saveButton.addActionListener(new ActionButtonsListener("Save"));
        openButton.addActionListener(new ActionButtonsListener("Open"));

        add(saveButton);
        add(openButton);
    }

    public class ActionButtonsListener implements ActionListener {

        public String actionCommand;

        public ActionButtonsListener(String actionCommand) {
            this.actionCommand = actionCommand;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (actionCommand) {
                case "Save":
                    drawingArea.saveDrawing();
                    break;
                case "Open":
                    drawingArea.openDrawing();
                    break;
            }
        }
    }
}
