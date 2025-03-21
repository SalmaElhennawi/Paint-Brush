import javax.swing.JFrame;

public class PaintingApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Paint Brush");
        MainPanel mainPanel = new MainPanel();
        frame.setContentPane(mainPanel);
        frame.setSize(1100, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}