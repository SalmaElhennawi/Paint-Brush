import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShapeButtons extends JPanel {

    private JButton rectangleButton, ovalButton, lineButton;
    private DrawingArea drawingArea;

    public ShapeButtons(DrawingArea drawingArea) {
        this.drawingArea=drawingArea;

        rectangleButton = new JButton("Rectangle");
        ovalButton = new JButton("Oval");
        lineButton = new JButton("Line");


        rectangleButton.addActionListener(new ShapeButtonsListener("Rectangle"));
        ovalButton.addActionListener(new ShapeButtonsListener("Oval"));
        lineButton.addActionListener(new ShapeButtonsListener("Line"));


        add(rectangleButton);
        add(ovalButton);
        add(lineButton);

    }

    public class ShapeButtonsListener implements ActionListener {

        private final String shape;

        public ShapeButtonsListener(String shape) {
            this.shape = shape;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            drawingArea.setCurrentShape(shape);

        }
    }
}

