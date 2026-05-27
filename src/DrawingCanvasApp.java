import controlpanel.ControlPanel;
import controlpanel.ControlPanelListener;
import controlpanel.colorpicker.ColorPicker;
import controlpanel.colorpicker.ColorPickerColors;
import model.DrawTools;

import java.awt.*;

import javax.naming.ldap.Control;
import javax.swing.*;

/**
 * A basic Swing drawing application with only a freehand drawing canvas.
 */
public class DrawingCanvasApp extends JFrame {

    private final DrawingCanvas canvas;

    /**
     * Creates the application window.
     */
    public DrawingCanvasApp() {
        super("Drawing Canvas App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas = new DrawingCanvas();

        ControlPanelListener listener = new ControlPanelListener() {
            @Override
            public void onColorSelected(ColorPickerColors color) {
                canvas.setStrokeColor(color.value);
            }

            @Override
            public void onToolSelected(DrawTools tool) {
                canvas.setDrawTool(tool);
            }

            @Override
            public void onClear() {
                canvas.clear();
            }
        };

        setLayout(new BorderLayout());
        add(canvas, BorderLayout.CENTER);
        add(new ColorPicker(listener, ColorPickerColors.BLACK), BorderLayout.SOUTH);
        add(new ControlPanel(listener), BorderLayout.NORTH);

        setMinimumSize(new Dimension(900, 650));
        setSize(900, 650);
    }

    /**
     * Program entry point.
     *
     * @param args command-line arguments, not used
     */
    public static void main(String[] args) {
        DrawingCanvasApp app = new DrawingCanvasApp();
        app.setVisible(true);
    }
}