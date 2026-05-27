import java.awt.*;

public class GhostStrokeSegment extends StrokeSegment {
    public final Long ttl;

    public GhostStrokeSegment(int x1, int y1, int x2, int y2, Color color, int width, long ttl) {
        super(x1, y1, x2, y2, color, width);
        this.ttl = ttl;
    }
}
