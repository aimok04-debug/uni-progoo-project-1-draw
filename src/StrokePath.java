import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Temporary stroke while the mouse is being dragged.
 */
public class StrokePath {
    /**
     * The points of the stroke
     */
    private final List<Point> points = new ArrayList<>();
    /**
     * The color in which the stroke should be drawn in
     */
    private final Color color;
    /**
     * The width of the stroke
     */
    private final int width;

    /**
     * Constructs a new StrokePath object
     * @param color The color the stroke should be drawn in
     * @param width The width of the stroke
     */
    public StrokePath(Color color, int width) {
        this.color = color;
        this.width = width;
    }

    /**
     * Adds a point to the StrokePath
     * @param point The point that should be added to the StrokePath
     */
    public void addPoint(Point point) {
        points.add(point);
    }

    /**
     * Replaces the last point (except when there is only one point)
     * @param point The point that should be added to the StrokePath
     */
    public void replacePoint(Point point) {
        if(points.size() > 1) points.removeLast();
        addPoint(point);
    }

    /**
     * Convert the points of the StrokePath to StrokeSegments
     * @return A list of StrokeSegments
     */
    public List<StrokeSegment> toSegments() {
        List<StrokeSegment> result = new ArrayList<>();
        for (int index = 1; index < points.size(); index++) {
            Point previous = points.get(index - 1);
            Point current = points.get(index);
            result.add(new StrokeSegment(previous.x, previous.y, current.x, current.y, color, width));
        }
        return result;
    }

    /**
     * Convert the points of the StrokePath to GhostStrokeSegments and calculate a TTL for them
     * @return A list of GhostStrokeSegments
     */
    public List<GhostStrokeSegment> toGhostSegments() {
        Long ttl = System.currentTimeMillis() + 2000L;

        List<GhostStrokeSegment> result = new ArrayList<>();
        for (int index = 1; index < points.size(); index++) {
            Point previous = points.get(index - 1);
            Point current = points.get(index);
            result.add(new GhostStrokeSegment(previous.x, previous.y, current.x, current.y, color, width, ttl + index * 5));
        }
        return result;
    }

    /**
     * Convert the points of the StrokePath to RainbowStrokeSegments and calculate a rainbowGroup for them
     * @return A list of RainbowStrokeSegments
     */
    public List<RainbowStrokeSegment> toRainbowSegments() {
        Long time = System.currentTimeMillis();

        List<RainbowStrokeSegment> result = new ArrayList<>();
        for (int index = 1; index < points.size(); index++) {
            int rainbowGroup = (int) (time + (index * 10L)) % 10000;

            Point previous = points.get(index - 1);
            Point current = points.get(index);
            result.add(new RainbowStrokeSegment(previous.x, previous.y, current.x, current.y, color, width, rainbowGroup));
        }
        return result;
    }

    /**
     * Paint the StrokePath on a graphics object
     * @param graphics2d The graphics object the StrokePath should be drawn on
     */
    public void paint(Graphics2D graphics2d) {
        for (StrokeSegment segment : toSegments()) {
            segment.paint(graphics2d);
        }
    }
}

