package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class MusicViewer {
    JFrame frame;

    public static void main(String[] args) {
        new MusicViewer();
    }

    public MusicViewer() {
        frame = new JFrame(); //creating instance of JFrame

        final JTextField tf = new JTextField();
        tf.setBounds(300,250, 150,25);
        tf.setText("Welcome to MusicViewer");

        JButton b = new JButton("Open MusicViewer"); //creating instance of JButton
        b.setBounds(300, 300, 150, 30); //x-axis, y-axis, width, height

        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tf.setText("Make a playlist");
                String playlistName = JOptionPane.showInputDialog(frame,"Enter playlist name");
            }
        });

        frame.add(b); //adding button in JFrame
        frame.add(tf);
        setUpJFrame(frame);
    }

    private void setUpJFrame(JFrame f) {
        f.add(makeBgImage());
        f.setSize(800,800); //400 width and 500 height
        f.setLayout(null); //using no layout managers
        f.setVisible(true); //making the frame visible
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JLabel makeBgImage() {
        ImageIcon rawImgIcon = new ImageIcon("src/main/ui/gui/abstract.png");
        Image resizedImg = rawImgIcon.getImage().getScaledInstance(900, 900, Image.SCALE_FAST);
        ImageIcon imgIcon = new ImageIcon(resizedImg);
        JLabel label = new JLabel(imgIcon);
        label.setBounds(0, 0, 900, 900); // You can use your own values
        return label;
    }
}
