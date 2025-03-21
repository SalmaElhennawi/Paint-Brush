import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CheckBoxPanel extends JPanel {

    private boolean isDotted = false;
    private boolean isFilled = false;
    private DrawingArea drawingArea;


    public CheckBoxPanel(DrawingArea drawingArea) {
        this.drawingArea=drawingArea;


        JCheckBox dottedCheckBox = new JCheckBox("Dotted");
        JCheckBox filledCheckBox = new JCheckBox("Filled");

        dottedCheckBox.addActionListener(new DottedCheckBoxListener());
        filledCheckBox.addActionListener(new FilledCheckBoxListener());

        add(dottedCheckBox);
        add(filledCheckBox);
    }

    public class DottedCheckBoxListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            drawingArea.setDotted(isDotted, e);
        }
    }

    public class FilledCheckBoxListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            drawingArea.setFilled(isFilled, e);
        }
    }
}
