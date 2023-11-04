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

        JButton b = new JButton("Make a new playlist"); //creating instance of JButton
        JButton b2 = new JButton("View playlists");
        JButton b3 = new JButton("Exit app");


        b.setBounds(300, 300, 150, 30); //x-axis, y-axis, width, height

        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tf.setText("Making a playlist...");
                String playlistName = JOptionPane.showInputDialog(frame,"Enter playlist name:");
                frame.add(makeButton(playlistName));
            }
        });

        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tf.setText("Viewing playlists...");
                JOptionPane.showMessageDialog(frame,"There are no playlists to view yet.");
            }
        });

        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(frame,"Do you wish to exit the app?");
                if (choice == 0) {
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    System.exit(0);
                }
            }
        });

        frame.add(b); //adding button in JFrame
        frame.add(b2);
        frame.add(b3);

        frame.add(tf);
        setUpJFrame(frame);
    }

    private void setUpJFrame(JFrame f) {
        f.add(makeBgImage());
        f.setSize(800,800); //400 width and 500 height
        f.setLayout(new FlowLayout()); //using no layout managers
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

    private JButton makeButton(String label) {
        JButton btn = new JButton(label); //creating instance of JButton
        btn.setBounds(10,100, 100,50);
        //frame.setLayout(new FlowLayout());
        return btn;
    }
}
