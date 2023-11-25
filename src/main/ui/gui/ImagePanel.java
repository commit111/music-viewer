package ui.gui;

import javax.swing.*;
import java.awt.*;

// The following code is referenced to the StackOverflow post:
// https://stackoverflow.com/questions/30271069/setting-an-image-as-background-for-jframe

// Represents a JPanel that can hold a background image.
public class ImagePanel extends JPanel {
    private final Image bgImage;

    // Constructor
    // EFFECTS: creates a new image panel with a specified layout manager
    public ImagePanel(Image image, LayoutManager lm) {
        this.bgImage = image;
        this.setLayout(lm);
    }

    // EFFECTS: paints the component with a rectangular background image scaled according to window size
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
    }
}
