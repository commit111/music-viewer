package ui.gui;

import javax.swing.*;
import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MusicViewer {
    private JFrame frame;
    private JPanel menuPanel;
    private JPanel playlistsPanel;
    private Image bgImage;

    public static void main(String[] args) throws IOException {
        MusicViewer mv = new MusicViewer();
    }

    //Constructor
    public MusicViewer() throws IOException {
        frame = new JFrame(); //creating instance of JFrame
        bgImage = makeBgImage("src/main/ui/gui/abstract.png");
        playlistsPanel = new ImagePanel(bgImage, new FlowLayout());
        menuPanel = new ImagePanel(bgImage, new FlowLayout());

        //menuPanel.setBackground(Color.YELLOW);
        //playlistsPanel.setBackground(Color.GREEN);

        menuPanel.setOpaque(false);
        playlistsPanel.setOpaque(false);

        frame.add(menuPanel);
        frame.add(playlistsPanel);

        setUpMainMenu(menuPanel);
        setUpJFrameSettings(frame);
    }

    //MODIFIES: panel
    //EFFECTS: adds a text field and main menu buttons to the panel
    private void setUpMainMenu(JPanel panel) {
        final JTextField tf = new JTextField();
        tf.setBounds(300,250, 150,25); //x-axis, y-axis, width, height
        tf.setText("Welcome to MusicViewer");
        tf.setEditable(false);

        JButton b = new JButton("Make a new playlist"); //creating instance of JButton
        JButton b2 = new JButton("Load playlists");
        JButton b3 = new JButton("Save playlists");
        JButton b0 = new JButton("Exit app");

        setMakePlaylistBtn(b, tf);
        setLoadPlaylistBtn(b2, tf);
        setSavePlaylistBtn(b3, tf);
        setExitAppBtn(b0, tf);

        panel.add(b); //adding button in JFrame
        panel.add(b2);
        panel.add(b3);
        panel.add(b0);
        panel.add(tf);
    }

    //MODIFIES: b, tf
    //EFFECTS:  makes the button produce a 'make playlist' action
    private void setMakePlaylistBtn(JButton b, JTextField tf) {
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tf.setText("Making a playlist...");
                String playlistName = JOptionPane.showInputDialog(frame,"Enter playlist name:");
                JButton pb = new JButton(playlistName);
                playlistsPanel.add(pb);
                setPlaylistBtn(pb);
                b.revalidate(); //makes sure button is on screen, not only on mouse hover
                tf.setText("Welcome to MusicViewer");
            }
        });
    }

    //MODIFIES: b, tf
    //EFFECTS:  makes the button produce a 'view playlist' action
    private void setLoadPlaylistBtn(JButton b, JTextField tf) {
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tf.setText("Loading playlists...");
                int choice = JOptionPane.showConfirmDialog(frame,"Do you want to load playlists from file?",
                        "Select an option", JOptionPane.OK_CANCEL_OPTION);
                if (choice == 0) {
                    //load playlists
                    JOptionPane.showMessageDialog(frame,"There are no playlists to load from file.");
                }
                tf.setText("Welcome to MusicViewer");
            }
        });
    }

    //MODIFIES: b, tf
    //EFFECTS:  makes the button produce a 'view playlist' action
    private void setSavePlaylistBtn(JButton b, JTextField tf) {
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tf.setText("Saving playlists...");
                int choice = JOptionPane.showConfirmDialog(frame,"Do you want to save all playlists?",
                        "Select an option", JOptionPane.OK_CANCEL_OPTION);
                if (choice == 0) {
                    //save playlists
                    JOptionPane.showMessageDialog(frame,"Playlists successfully saved to file!");
                }
                tf.setText("Welcome to MusicViewer");
            }
        });
    }

    //MODIFIES: b, tf
    //EFFECTS:  makes the button produce an 'exit app' action
    private void setExitAppBtn(JButton b, JTextField tf) {
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tf.setText("Exiting the app...");
                int choice = JOptionPane.showConfirmDialog(frame,"Do you wish to exit the app?");
                if (choice == 0) {
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    System.exit(0);
                } else {
                    tf.setText("Welcome to MusicViewer");
                }
            }
        });
    }

    //MODIFIES: f
    //EFFECTS: sets up the settings for the JFrame
    private void setUpJFrameSettings(JFrame f) throws IOException {
        f.setSize(800,800); //800 width and 800 height
        f.setLayout(new GridLayout(1,2)); //using layout manager
        f.setVisible(true); //making the frame visible
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //EFFECTS: returns a properly scaled bg image from a given file path
    private BufferedImage makeBgImage(String filePath) throws IOException {
        BufferedImage before = ImageIO.read(new File(filePath));
        BufferedImage after = scaleImage(before);
        return after;
    }

    // The code for scaleBufferedImage() can be referenced to a StackOverflow post:
    // https://stackoverflow.com/questions/4216123/how-to-scale-a-bufferedimage

    //EFFECTS: scales the buffered image
    private BufferedImage scaleImage(BufferedImage before) {
        double sx = 0.9;
        double sy = 0.9;
        int beforeWidth = before.getWidth();
        int beforeHeight = before.getHeight();
        BufferedImage after = new BufferedImage(beforeWidth, beforeHeight,BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = new AffineTransform();
        at.scale(sx,sy);
        AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        after = scaleOp.filter(before, after);
        return after;
    }

    //MODIFIES: b
    //EFFECTS: adds a playlist menu to the button
    private void setPlaylistBtn(JButton b) {
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setUpPlaylistMenu(b);
            }
        });
    }

    //EFFECTS: sets up the playlist menu
    private void setUpPlaylistMenu(JButton b) {
        JPanel playlistMenuPanel = new JPanel();
        JRadioButton r1 = new JRadioButton("View songs", true);
        JRadioButton r2 = new JRadioButton("Add a song");

        playlistMenuPanel.add(r1);
        playlistMenuPanel.add(r2);

        //use a button group tp keep radio buttons mutually exclusive
        ButtonGroup group = new ButtonGroup();
        group.add(r1);
        group.add(r2);

        int choice = JOptionPane.showConfirmDialog(frame, playlistMenuPanel,
                "Select an option", JOptionPane.OK_CANCEL_OPTION);
        if (choice == 0) {
            if (r1.isSelected()) {
                setUpSongsViewMenu(b);
            } else if (r2.isSelected()) {
                JOptionPane.showMessageDialog(frame, "We'll be able to add a song later :)");
            }
        }
    }

    //EFFECTS: sets up the songs view menu
    private void setUpSongsViewMenu(JButton b) {
        JPanel svPanel = new JPanel();
        JRadioButton r1 = new JRadioButton("Hello by Me", true);
        JRadioButton r2 = new JRadioButton("Goodbye by You");

        svPanel.add(r1);
        svPanel.add(r2);

        //use a button group tp keep radio buttons mutually exclusive
        ButtonGroup group = new ButtonGroup();
        group.add(r1);
        group.add(r2);

        int choice = JOptionPane.showConfirmDialog(frame, svPanel,
                "Select a song to view", JOptionPane.OK_CANCEL_OPTION);
        if (choice == 0) {
            if (r1.isSelected()) {
                JOptionPane.showMessageDialog(frame, "helloo my sweetheart");
            } else if (r2.isSelected()) {
                JOptionPane.showMessageDialog(frame, "gooodbye my lover");
            }
        }
    }


}




//    //--------
//    //OLD CODE FOR BG IMAGE USING JLabel, TO USE, CALL frame.add(makeBgImage())
//    //EFFECTS: makes a label with a background image
//    private JLabel makeBgImage() {
//        ImageIcon rawImgIcon = new ImageIcon("src/main/ui/gui/abstract.png");
//        Image resizedImg = rawImgIcon.getImage().getScaledInstance(900, 900, Image.SCALE_FAST);
//        ImageIcon imgIcon = new ImageIcon(resizedImg);
//        JLabel label = new JLabel(imgIcon);
//        label.setBounds(0, 0, 900, 900); // You can use your own values
//        return label;
//    }
//    //--------



