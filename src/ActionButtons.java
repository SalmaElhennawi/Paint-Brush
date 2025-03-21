import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionButtons extends JPanel {


    private JButton freehandButton, eraserButton, clearAllButton, undoButton;
    private DrawingArea drawingArea;

    public ActionButtons(DrawingArea drawingArea) {
        this.drawingArea=drawingArea;

        setLayout(new FlowLayout());

        freehandButton = new JButton("Freehand");
        eraserButton = new JButton("Eraser");
        clearAllButton = new JButton("Clear All");
        undoButton = new JButton("Undo");

        freehandButton.addActionListener(new ActionButtonsListener("Freehand"));
        eraserButton.addActionListener(new ActionButtonsListener("Eraser"));
        clearAllButton.addActionListener(new ActionButtonsListener("Clear"));
        undoButton.addActionListener(new ActionButtonsListener("Undo"));

        add(freehandButton);
        add(eraserButton);
        add(clearAllButton);
        add(undoButton);

    }

    public class ActionButtonsListener implements ActionListener {

        public String actionCommand;

        public ActionButtonsListener(String actionCommand) {
            this.actionCommand = actionCommand;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (actionCommand) {
                case "Freehand":
                    drawingArea.setFreehand(true);
                    drawingArea.setEraser(false);
                    break;
                case "Eraser":
                    drawingArea.setEraser(true);
                    drawingArea.setFreehand(false);
                    break;
                case "Clear":
                    drawingArea.clearShapes();
                    break;
                case "Undo":
                    drawingArea.undoLastShape();
                    break;
            }
        }
    }
}
