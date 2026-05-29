import java.awt.*;

/**
 * A StrokeSegment with a rainbowGroup value
 */
public class RainbowStrokeSegment extends StrokeSegment {

    /**
     * Used to calculate which rainbow color should the stroke be drawn in
     */
    public final int rainbowGroup;


    /**
     * Constructs a new RainbowStrokeSegment object
     * @param x1 The first x coordinate
     * @param y1 The first y coordinate
     * @param x2 The second x coordinate
     * @param y2 The second y coordinate
     * @param color The color the stroke should be drawn in (irrelevant)
     * @param width The width the stroke should be drawn in
     * @param rainbowGroup The rainbow group the stroke is in (used to calculate the rainbow colors)
     */
    public RainbowStrokeSegment(int x1, int y1, int x2, int y2, Color color, int width, int rainbowGroup) {
        super(x1, y1, x2, y2, color, width);
        this.rainbowGroup = rainbowGroup;
    }
}
