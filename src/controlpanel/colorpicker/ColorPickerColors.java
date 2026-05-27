package controlpanel.colorpicker;

import java.awt.*;

public enum ColorPickerColors {
    // Klassiker
    BLACK(new Color(0, 0, 0)),
    DARK_GRAY(new Color(64, 64, 64)),
    WHITE(new Color(255, 255, 255)), // Wichtig als "Radiergummi"-Ersatz

    // Kräftige Farben (Modernere Töne als die Java-Standards)
    RED(new Color(231, 76, 60)),     // Ein schönes "Alizarin" Rot
    BLUE(new Color(52, 152, 219)),   // "Peter River" Blau
    GREEN(new Color(46, 204, 113)),  // "Emerald" Grün
    YELLOW(new Color(241, 196, 15)), // "Sun Flower" Gelb
    ORANGE(new Color(230, 126, 34)), // "Carrot" Orange
    PURPLE(new Color(155, 89, 182)), // "Amethyst" Lila

    // Bonus-Farben für Tiefe
    BROWN(new Color(121, 85, 72)),   // Für Naturzeichnungen
    PINK(new Color(255, 182, 193));  // Pastell-Touch

    public final Color value;
    ColorPickerColors(Color value) {
        this.value = value;
    }
}
