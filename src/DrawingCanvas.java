import model.DrawTools;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

/**
 * The drawing surface.
 */
public class DrawingCanvas extends JPanel {

    /**
     * The list of segments on the canvas
     */
    private final List<StrokeSegment> segments = new ArrayList<>();
    /**
     * The list of ghost segments on the canvas
     */
    private final List<GhostStrokeSegment> ghostSegments = new ArrayList<>();
    /**
     * The list of rainbow segments on the canvas
     */
    private final List<RainbowStrokeSegment> rainbowStrokeSegments = new ArrayList<>();
    /**
     * The current StrokePath on the canvas
     */
    private StrokePath currentPath;

    /**
     * The currently used drawing tool
     */
    private DrawTools drawTool = DrawTools.PENCIL;
    /**
     * The color that should be used for the StrokePath
     */
    private Color strokeColor = Color.BLACK;

    /**
     * Creates the canvas, installs mouse handling and starts the timer for ghost and rainbow segments
     */
    public DrawingCanvas() {
        CanvasMouseListener mouseListener = new CanvasMouseListener(this);
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);

        Timer timer = new Timer(20, _ -> {
            long time = System.currentTimeMillis();

            int rainbowOffset = (int) (time % 10000);

            for(RainbowStrokeSegment segment : rainbowStrokeSegments) {
                Color rainbowColor = Color.getHSBColor((segment.rainbowGroup + rainbowOffset) / 1000f, 1.0f, 1.0f);
                segment.setColor(rainbowColor);
            }

            int i;
            for(i = 0; i < ghostSegments.size(); i++) {
                if(time < ghostSegments.get(i).ttl) break;
            }

            for(int h = 0; h < i; h++) {
                ghostSegments.removeFirst();
            }

            repaint();
        });
        timer.start();
    }

    /**
     * Sets the currently used drawing tool
     * @param drawTool The DrawTool that should be used
     */
    public void setDrawTool(DrawTools drawTool) {
        this.drawTool = drawTool;
    }

    /**
     * Sets the currently used stroke color
     * @param strokeColor The color that should be used
     */
    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    /**
     * Clears the canvas
     */
    public void clear() {
        segments.clear();
        rainbowStrokeSegments.clear();
        ghostSegments.clear();
    }

    /**
     * Overrides the preferred size of the JPanel
     * @return The preferred size
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 560);
    }

    /**
     * Begins a stroke on the canvas
     * @param point The point of beginning
     */
    public void beginStroke(Point point) {
        currentPath = new StrokePath(strokeColor, 5);
        currentPath.addPoint(point);
        repaint();
    }

    /**
     * Continues the current active stroke on the canvas
     * @param point The next point
     */
    public void extendStroke(Point point) {
        if (currentPath == null) {
            return;
        }

        if(drawTool == DrawTools.PENCIL || drawTool == DrawTools.GHOST_PENCIL || drawTool == DrawTools.RAINBOW_PENCIL) {
            currentPath.addPoint(point);
        }else if(drawTool == DrawTools.LINE) {
            currentPath.replacePoint(point);
        }

        repaint();
    }

    /**
     * Ends the current active stroke on the canvas and saves the new segment
     * @param point The ending point
     */
    public void endStroke(Point point) {
        if (currentPath == null) {
            return;
        }

        currentPath.addPoint(point);

        if(drawTool == DrawTools.GHOST_PENCIL) {
            ghostSegments.addAll(currentPath.toGhostSegments());
        }else if(drawTool == DrawTools.RAINBOW_PENCIL) {
            rainbowStrokeSegments.addAll(currentPath.toRainbowSegments());
        }else{
            segments.addAll(currentPath.toSegments());
        }
        currentPath = null;
        repaint();
    }

    /**
     * Paints the canvas on a graphics object
     * @param graphics The graphics object the canvas should be painted on
     */
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2d = (Graphics2D) graphics.create();
        for (StrokeSegment segment : segments) {
            segment.paint(graphics2d);
        }
        for (StrokeSegment segment : ghostSegments) {
            segment.paint(graphics2d);
        }
        for (StrokeSegment segment : rainbowStrokeSegments) {
            segment.paint(graphics2d);
        }

        if (currentPath != null) {
            currentPath.paint(graphics2d);
        }

        graphics2d.dispose();
    }
}

