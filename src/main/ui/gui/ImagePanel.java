package ui.gui;

import javax.swing.*;
import java.awt.*;

//The following code is referenced to the StackOverflow post:
//https://stackoverflow.com/questions/30271069/setting-an-image-as-background-for-jframe

public class ImagePanel extends JPanel {
    private Image bgImage;

    public ImagePanel(Image image, LayoutManager lm) {
        this.bgImage = image;
        this.setLayout(lm);
    }

    //EFFECTS: repaints background with a background image scaled according to window size
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, getWidth(), getHeight(),this);
    }
}
