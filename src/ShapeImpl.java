import java.awt.*;
import java.io.Serializable;

public class ShapeImpl implements Serializable {

    String type;
    Color color;
    Point start, end;
    boolean isDotted, isFilled, freehand, eraser;

    public ShapeImpl(String type, Color color, Point start, Point end, boolean isDotted, boolean isFilled, boolean freehand, boolean eraser) {

        this.type = type;
        this.color = color;
        this.start = start;
        this.end = end;
        this.isDotted = isDotted;
        this.isFilled = isFilled;
        this.freehand = freehand;
        this.eraser = eraser;
    }
}

