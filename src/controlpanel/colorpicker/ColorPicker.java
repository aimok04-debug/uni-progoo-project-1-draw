package controlpanel.colorpicker;

import components.RoundColorButton;
import controlpanel.ControlPanelListener;

import javax.swing.*;
import java.awt.*;

/**
 * A simple  color picker bar
 */
public class ColorPicker extends JPanel {

    /**
     * Constructs a new color picker bar
     * @param listener The control panel listener the color picker should use to propagate color selection changes
     * @param defaultColor The color that should be selected by default
     */
    public ColorPicker(ControlPanelListener listener, ColorPickerColors defaultColor) {
        super(new FlowLayout(FlowLayout.CENTER, 10, 10));

        listener.onColorSelected(defaultColor);

        ButtonGroup buttonGroup = new ButtonGroup();

        for(ColorPickerColors color : ColorPickerColors.values()) {
            RoundColorButton button = new RoundColorButton(color.value, defaultColor == color);
            buttonGroup.add(button);
            add(button);

            button.addActionListener(_ -> {
                listener.onColorSelected(color);
            });
        }
    }

}
