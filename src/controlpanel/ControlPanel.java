package controlpanel;

import components.RoundColorButton;
import controlpanel.colorpicker.ColorPicker;
import controlpanel.colorpicker.ColorPickerColors;
import controlpanel.drawtoolpicker.DrawToolPicker;

import javax.swing.*;
import java.awt.*;

/**
 * The control panel of the canvas app
 */
public class ControlPanel extends JPanel {

    /**
     * Constructs a new control panel
     * @param listener The listener to propagate changes
     */
    public ControlPanel(ControlPanelListener listener) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        setBackground(Color.LIGHT_GRAY);

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(_ -> {
            listener.onClear();
        });

        new DrawToolPicker(listener).addTo(this);
        add(Box.createHorizontalGlue());
        add(clearButton);
        add(Box.createRigidArea(new Dimension(10, 50)));
    }

}
