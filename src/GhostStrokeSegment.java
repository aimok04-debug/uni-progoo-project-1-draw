import java.awt.*;

/**
 * A StrokeSegment with a TTL (time to live) value
 */
public class GhostStrokeSegment extends StrokeSegment {
    /**
     * Time to live value (Segment will be removed when time > ttl)
     */
    public final Long ttl;

    /**
     * Construct a new GhostStrokeSegment
     * @param x1 The first x coordinate
     * @param y1 The first y coordinate
     * @param x2 The second x coordinate
     * @param y2 The second y coordinate
     * @param color The color the stroke should be drawn in
     * @param width The width of the stroke
     * @param ttl The time to live value
     */
    public GhostStrokeSegment(int x1, int y1, int x2, int y2, Color color, int width, long ttl) {
        super(x1, y1, x2, y2, color, width);
        this.ttl = ttl;
    }
}
