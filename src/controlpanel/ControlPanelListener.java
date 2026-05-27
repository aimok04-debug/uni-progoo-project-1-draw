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

    // TODO: comment
    void onToolSelected(DrawTools tool);

    // TODO: comment
    void onClear();
}
