package com.travel;

import javax.swing.ImageIcon;
import javax.swing.*;
import java.awt.*;

public class Into extends JFrame implements Runnable {

    Thread thread;

    Into() {
        setVisible(true);

        ImageIcon i1 = new ImageIcon(getClass().getResource("Images/Into.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1000, 500, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(1000, 500));
        JLabel image = new JLabel(i3);
        JLabel text = new JLabel("Travel and Tourism");
        text.setForeground(Color.ORANGE);
        text.setFont(new Font("Arial", Font.BOLD, 48));
        text.setHorizontalAlignment(SwingConstants.CENTER);
        text.setBounds(-200, 150, i3.getIconWidth(), i3.getIconHeight());

        image.add(text);

        p.add(image);
        // p.add(text);

        add(p);

        thread = new Thread(this);
        thread.start();

        // System.out.println("Hi");
    }

    public void run() {
        try {
            Thread.sleep(10000);
            setVisible(false);
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }

    public static void main(String[] args) {
        Into frame = new Into();

        for (int i = 1; i <= 500; i++) {
            frame.setSize(i * 2, i);
            frame.setLocation(620 - i, 380 - (i / 2));

            try {
                Thread.sleep(4);
            } catch (Exception e) {
                System.out.println("Exception");
            }
        }
        frame.dispose();
        new Login();
    }
}
