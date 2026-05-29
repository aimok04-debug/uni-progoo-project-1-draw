package controlpanel.colorpicker;

import java.awt.*;

/**
 * The colors for the ColorPicker
 */
public enum ColorPickerColors {
    BLACK(new Color(0, 0, 0)),
    DARK_GRAY(new Color(64, 64, 64)),
    WHITE(new Color(255, 255, 255)),
    RED(new Color(231, 76, 60)),
    BLUE(new Color(52, 152, 219)),
    GREEN(new Color(46, 204, 113)),
    YELLOW(new Color(241, 196, 15)),
    ORANGE(new Color(230, 126, 34)),
    PURPLE(new Color(155, 89, 182)),
    BROWN(new Color(121, 85, 72)),
    PINK(new Color(255, 182, 193));

    /**
     * The actual color value
     */
    public final Color value;


    /**
     * The constructor for the enum items
     * @param value The actual color value of an ColorPickerColors item
     */
    ColorPickerColors(Color value) {
        this.value = value;
    }
}
