package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MusicViewer {
    public static void main(String[] args) {
        JFrame f = new JFrame(); //creating instance of JFrame

        final JTextField tf = new JTextField();
        tf.setBounds(300,250, 150,25);

        ImageIcon rawImgIcon = new ImageIcon("src/main/ui/gui/swirl.png");
        Image resizedImg = rawImgIcon.getImage().getScaledInstance(400, 300, Image.SCALE_FAST);
        ImageIcon imgIcon = new ImageIcon(resizedImg);
        JLabel label = new JLabel(imgIcon);
        label.setBounds(175, 150, 400, 300); // You can use your own values
        f.add(label); //f.getContentPane().add(label);

        JButton b = new JButton("Open MusicViewer"); //creating instance of JButton
        b.setBounds(300, 300, 150, 30); //x-axis, y-axis, width, height

        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tf.setText("Welcome to MusicViewer");
            }
        });

        f.add(b); //adding button in JFrame
        f.add(tf);
        f.setSize(800,800); //400 width and 500 height
        f.setLayout(null); //using no layout managers
        f.setVisible(true); //making the frame visible
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
