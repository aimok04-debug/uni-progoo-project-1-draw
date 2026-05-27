import java.awt.*;

public class RainbowStrokeSegment extends StrokeSegment {
    public final int rainbowGroup;

    public RainbowStrokeSegment(int x1, int y1, int x2, int y2, Color color, int width, int rainbowGroup) {
        super(x1, y1, x2, y2, color, width);
        this.rainbowGroup = rainbowGroup;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
