package ui.gui;

import javax.swing.*;
import java.awt.*;

public class TransparentPanel extends JPanel {
    public TransparentPanel(LayoutManager lm) {
        this.setLayout(lm);
        this.setOpaque(false); //make sure no glitches
        this.setBackground(new Color(0, 0, 0, 0));
    }

    //EFFECTS: repaints background, useful for semi-transparent colored backgrounds
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}
