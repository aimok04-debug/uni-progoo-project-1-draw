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

    private final List<StrokeSegment> segments = new ArrayList<>();
    private final List<GhostStrokeSegment> ghostSegments = new ArrayList<>();
    private final List<RainbowStrokeSegment> rainbowStrokeSegments = new ArrayList<>();
    private StrokePath currentPath;

    private DrawTools drawTool = DrawTools.PENCIL;
    private Color strokeColor = Color.BLACK;

    /**
     * Creates the canvas and installs mouse handling.
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

    public void setDrawTool(DrawTools drawTool) {
        this.drawTool = drawTool;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    public void clear() {
        segments.clear();
        rainbowStrokeSegments.clear();
        ghostSegments.clear();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 560);
    }

    public void beginStroke(Point point) {
        currentPath = new StrokePath(strokeColor, 5);
        currentPath.addPoint(point);
        repaint();
    }

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

