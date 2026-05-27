package components;

import javax.swing.*;
import java.awt.*;

/**
 * A simple icon for displaying a color
 */
public class RoundColorIcon implements Icon {

    /**
     * The color which the icon should display.
     */
    private final Color color;
    /**
     * The selection state of the component the icon is used in.
     */
    private final Boolean selected;

    /**
     * Constructs a new round color icon
     * @param color The color to display
     * @param selected The selection state of the component the icon is used in
     */
    public RoundColorIcon(Color color, Boolean selected) {
        this.color = color;
        this.selected = selected;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        if(selected) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.BLACK);
            g2.fillRoundRect(x - 4, y - 4, getIconWidth() + 8, getIconHeight() + 8, 26, 26);
            g2.dispose();
        }

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);

        if(selected) {
            g2.fillRoundRect(x, y, getIconWidth(), getIconHeight(), 20, 20);
        }else{
            g2.fillOval(x, y, getIconWidth(), getIconHeight());
        }

        g2.dispose();
    }

    @Override public int getIconWidth() { return 50; }

    @Override public int getIconHeight() { return 50; }
}
