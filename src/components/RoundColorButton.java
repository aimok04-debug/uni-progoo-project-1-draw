package components;

import javax.swing.*;
import java.awt.*;

/**
 * A JToggleButton which displays a color
 */
public class RoundColorButton extends JToggleButton {

    /**
     * Constructs a new round color button
     * @param color The color to display
     * @param selected The default selection state of the JToggleButton
     */
    public RoundColorButton(Color color, Boolean selected) {
        super();

        setSelected(selected);

        setIcon(new RoundColorIcon(color, false));
        setSelectedIcon(new RoundColorIcon(color, true));

        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
    }

}
