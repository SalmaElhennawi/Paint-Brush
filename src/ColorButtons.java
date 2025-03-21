import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorButtons extends JPanel {

    private JButton redButton, greenButton, blueButton, blackButton, yellowButton, orangeButton, grayButton, brownButton, purpleButton, pinkButton, whiteButton;
    private DrawingArea drawingArea;

    public ColorButtons(DrawingArea drawingArea) {
        this.drawingArea=drawingArea;

        redButton = new JButton("Red");
        greenButton = new JButton("Green");
        blueButton = new JButton("Blue");
        blackButton = new JButton("Black");
        yellowButton = new JButton("Yellow");
        orangeButton = new JButton("Orange");
        grayButton = new JButton("Gray");
        brownButton = new JButton("Brown");
        purpleButton = new JButton("Purple");
        pinkButton = new JButton("Pink");
        whiteButton = new JButton("White");

        redButton.setBackground(Color.RED);
        greenButton.setBackground(Color.GREEN);
        blueButton.setBackground(Color.BLUE);
        blackButton.setBackground(Color.BLACK);
        yellowButton.setBackground(Color.YELLOW);
        orangeButton.setBackground(Color.ORANGE);
        grayButton.setBackground(Color.GRAY);
        brownButton.setBackground(new Color(139, 69, 19));
        purpleButton.setBackground(new Color(128, 0, 128));
        pinkButton.setBackground(Color.PINK);
        whiteButton.setBackground(Color.WHITE);

        redButton.addActionListener(new ColorButtonsListener(Color.RED));
        greenButton.addActionListener(new ColorButtonsListener(Color.GREEN));
        blueButton.addActionListener(new ColorButtonsListener(Color.BLUE));
        blackButton.addActionListener(new ColorButtonsListener(Color.BLACK));
        yellowButton.addActionListener(new ColorButtonsListener(Color.YELLOW));
        orangeButton.addActionListener(new ColorButtonsListener(Color.ORANGE));
        grayButton.addActionListener(new ColorButtonsListener(Color.GRAY));
        brownButton.addActionListener(new ColorButtonsListener(new Color(139, 69, 19)));
        purpleButton.addActionListener(new ColorButtonsListener(new Color(128, 0, 128)));
        pinkButton.addActionListener(new ColorButtonsListener(Color.PINK));
        whiteButton.addActionListener(new ColorButtonsListener(Color.WHITE));

        add(redButton);
        add(greenButton);
        add(blueButton);
        add(blackButton);
        add(yellowButton);
        add(orangeButton);
        add(grayButton);
        add(brownButton);
        add(purpleButton);
        add(pinkButton);
        add(whiteButton);
    }

    public class ColorButtonsListener implements ActionListener {

        public Color color;

        public ColorButtonsListener(Color color) {
            this.color = color;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            drawingArea.setCurrentColor(color);
        }
    }
}

