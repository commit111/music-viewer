package ui.gui;

import javax.swing.*;
import java.awt.*;

//Creates a new panel that can have a transparent or semi-transparent background (having colored tint is optional)
public class TransparentPanel extends JPanel {
    public TransparentPanel(LayoutManager lm) {
        this.setLayout(lm);
        this.setOpaque(false); //make sure no glitches
        this.setBackground(new Color(0, 0, 0, 0));
    }

    //EFFECTS: paints the component with a transparent/semi-transparent rectangle onto the canvas
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}
