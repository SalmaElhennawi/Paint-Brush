import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private DrawingArea drawingArea;

    public MainPanel() {
        setLayout(new BorderLayout());

        drawingArea = new DrawingArea();
        drawingArea.setBackground(Color.WHITE);

        JPanel topPanel = new JPanel(new GridLayout(3, 1));
        topPanel.add(new ColorButtons(drawingArea));
        topPanel.add(new ShapeButtons(drawingArea));
        topPanel.add(new CheckBoxPanel(drawingArea));

        JPanel botomPanel = new JPanel(new GridLayout(2, 1));
        botomPanel.add(new ActionButtons(drawingArea));
        botomPanel.add(new BonusButtons(drawingArea));

        add(topPanel, BorderLayout.NORTH);
        add(drawingArea, BorderLayout.CENTER);
        add(botomPanel, BorderLayout.SOUTH);
    }
}

