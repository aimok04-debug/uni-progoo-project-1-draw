package controlpanel.drawtoolpicker;

import components.RoundColorButton;
import controlpanel.ControlPanel;
import controlpanel.ControlPanelListener;
import controlpanel.colorpicker.ColorPickerColors;
import model.DrawTools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DrawToolPicker {

    private List<Component> components = new ArrayList<>();

    public DrawToolPicker(ControlPanelListener listener) {
        ButtonGroup buttonGroup = new ButtonGroup();
        for(DrawTools tool : DrawTools.values()) {
            components.add(Box.createRigidArea(new Dimension(10, 50)));

            JToggleButton toolButton = new JToggleButton(tool.name());
            buttonGroup.add(toolButton);
            components.add(toolButton);

            toolButton.addActionListener(_ -> {
                listener.onToolSelected(tool);
            });
        }
    }

    public void addTo(JPanel panel) {
        for(Component component : components) {
            panel.add(component);
        }
    }

}
