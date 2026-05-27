import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Temporary stroke while the mouse is being dragged.
 */
public class StrokePath {
    private final List<Point> points = new ArrayList<>();
    private final Color color;
    private final int width;

    public StrokePath(Color color, int width) {
        this.color = color;
        this.width = width;
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public void replacePoint(Point point) {
        if(points.size() > 1) points.removeLast();
        addPoint(point);
    }

    public List<StrokeSegment> toSegments() {
        List<StrokeSegment> result = new ArrayList<>();
        for (int index = 1; index < points.size(); index++) {
            Point previous = points.get(index - 1);
            Point current = points.get(index);
            result.add(new StrokeSegment(previous.x, previous.y, current.x, current.y, color, width));
        }
        return result;
    }

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

    public void paint(Graphics2D graphics2d) {
        for (StrokeSegment segment : toSegments()) {
            segment.paint(graphics2d);
        }
    }
}

