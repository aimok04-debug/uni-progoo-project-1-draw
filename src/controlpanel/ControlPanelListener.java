package controlpanel;

import controlpanel.colorpicker.ColorPickerColors;
import model.DrawTools;

import java.awt.*;

/**
 * The listener for the control panel
 */
public interface ControlPanelListener {
    /**
     * Will be called when the user selects a color
     * @param color The color the user selected
     */
    void onColorSelected(ColorPickerColors color);

    /**
     * Will be called when the user selects a draw tool
     * @param tool The tool the user selected
     */
    void onToolSelected(DrawTools tool);

    /**
     * Will be called when the user clicks clear canvas button
     */
    void onClear();
}
