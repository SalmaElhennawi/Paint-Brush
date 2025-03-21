import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class DrawingArea extends JPanel {

    private ArrayList<ShapeImpl> shapes = new ArrayList<>();
    private Point startPoint;
    private Point endPoint;
    private String currentShape = "Line";
    private Color currentColor = Color.BLACK ;
    private boolean isDotted = false;
    private boolean isFilled = false;
    private boolean freehand = false;
    private boolean eraser = false;

    private BufferedImage loadedImage;

    public DrawingArea() {

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (startPoint != null && endPoint != null && !freehand && !eraser) {
                    shapes.add(new ShapeImpl(currentShape, currentColor, startPoint, endPoint, isDotted, isFilled, freehand, eraser));
                    startPoint = null;
                    endPoint = null;
                    repaint();
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                endPoint = e.getPoint();
                if (freehand || eraser) {
                    shapes.add(new ShapeImpl(currentShape, eraser ? getBackground() : currentColor, startPoint, endPoint, false, false, false, false));
                    startPoint = endPoint;
                }
                repaint();
            }
        });

    }

    public void setFreehand(boolean freehand) {
        this.freehand = freehand;
    }

    public void setEraser(boolean eraser) {
        this.eraser = eraser;
    }

    public void setCurrentShape(String shape) {
        this.currentShape = shape;
    }

    public void setCurrentColor(Color color) {
        this.currentColor = color;
    }

    public void setDotted(boolean dotted, ActionEvent e) {

        this.isDotted  = ((JCheckBox) e.getSource()).isSelected();
    }

    public void setFilled(boolean filled, ActionEvent e) {
        this.isFilled = ((JCheckBox) e.getSource()).isSelected();
    }

    public void undoLastShape() {
        if (!shapes.isEmpty()) {
            shapes.remove(shapes.size() - 1);
            repaint();
        }
    }

    public void clearShapes() {
        shapes.clear();
        repaint();
    }

    public void saveDrawing() {
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        paint(g2d);
        g2d.dispose();

        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                ImageIO.write(image, "PNG", selectedFile);
                JOptionPane.showMessageDialog(null, "Image saved successfully!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error saving image.");
            }
        }
    }

    public void openDrawing() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }
                String extension = getFileExtension(f);
                if (extension != null) {
                    if (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png") || extension.equals("gif") || extension.equals("bmp")) {
                        return true;
                    } else if (extension.equals("ser")) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public String getDescription() {
                return "Image and Serialized Files (*.jpg, *.jpeg, *.png, *.gif, *.bmp, *.ser)";
            }

            public String getFileExtension(File f) {
                String ext = null;
                String s = f.getName();
                int i = s.lastIndexOf('.');
                if (i > 0 && i < s.length() - 1) {
                    ext = s.substring(i + 1).toLowerCase();
                }
                return ext;
            }
        });

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String extension = getFileExtension(file);
            if (extension != null && (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png") || extension.equals("gif") || extension.equals("bmp"))) {
                try {
                    loadedImage = ImageIO.read(file);
                    shapes.clear();
                    repaint();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else if (extension != null && extension.equals("ser")) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    shapes = (ArrayList<ShapeImpl>) ois.readObject();
                    loadedImage = null;
                    repaint();
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public String getFileExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');
        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (loadedImage != null) {
            g2d.drawImage(loadedImage, 0, 0, this);
        }

        for (ShapeImpl shape : shapes) {
            g2d.setColor(shape.color);
            if (shape.isDotted) {
                float[] dashPattern = {10f, 10f};
                g2d.setStroke(new BasicStroke(1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0f, dashPattern, 0f));
            } else {
                g2d.setStroke(new BasicStroke());
            }
            switch (shape.type) {
                case "Rectangle":
                    if (shape.isFilled) {
                        g2d.fillRect(shape.start.x, shape.start.y, shape.end.x - shape.start.x, shape.end.y - shape.start.y);
                    } else {
                        g2d.drawRect(shape.start.x, shape.start.y, shape.end.x - shape.start.x, shape.end.y - shape.start.y);
                    }
                    break;
                case "Oval":
                    if (shape.isFilled) {
                        g2d.fillOval(shape.start.x, shape.start.y, shape.end.x - shape.start.x, shape.end.y - shape.start.y);
                    } else {
                        g2d.drawOval(shape.start.x, shape.start.y, shape.end.x - shape.start.x, shape.end.y - shape.start.y);
                    }
                    break;
                case "Line":
                    g2d.drawLine(shape.start.x, shape.start.y, shape.end.x, shape.end.y);
                    break;
                case "Freehand":
                    g2d.drawLine(shape.start.x, shape.start.y, shape.end.x, shape.end.y);
                    break;
                case "Eraser":
                    g2d.setColor(getBackground());
                    g2d.setStroke(new BasicStroke(10));
                    g2d.drawLine(shape.start.x, shape.start.y, shape.end.x, shape.end.y);
                    break;
            }
        }

        if (startPoint != null && endPoint != null && !freehand && !eraser) {
            g2d.setColor(currentColor);
            if (isDotted) {
                g2d.setStroke(new BasicStroke(1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0f, new float[]{10f, 10f}, 0f));
            } else {
                g2d.setStroke(new BasicStroke());
            }
            switch (currentShape) {
                case "Rectangle":
                    if (isFilled) {
                        g2d.fillRect(startPoint.x, startPoint.y, endPoint.x - startPoint.x, endPoint.y - startPoint.y);
                    } else {
                        g2d.drawRect(startPoint.x, startPoint.y, endPoint.x - startPoint.x, endPoint.y - startPoint.y);
                    }
                    break;
                case "Oval":
                    if (isFilled) {
                        g2d.fillOval(startPoint.x, startPoint.y, endPoint.x - startPoint.x, endPoint.y - startPoint.y);
                    } else {
                        g2d.drawOval(startPoint.x, startPoint.y, endPoint.x - startPoint.x, endPoint.y - startPoint.y);
                    }
                    break;
                case "Line":
                    g2d.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
                    break;
            }
        }
    }
}