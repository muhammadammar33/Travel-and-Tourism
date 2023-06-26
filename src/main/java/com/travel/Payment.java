package com.travel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Payment extends JFrame {
    public static void main(String[] args) {
        new Payment();
    }

    JButton Pay, Back;

    Payment() {
        setTitle("Payment");
        setSize(800, 550);
        setLocation(250, 150);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        ImageIcon i1 = new ImageIcon(getClass().getResource("Images/HBL.png"));
        Image i2 = i1.getImage().getScaledInstance(800, 550, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 800, 550);
        add(image);

        Pay = new JButton("Pay");
        Pay.setBounds(80, 400, 120, 30);
        Pay.setBorder(BorderFactory.createEmptyBorder());
        Pay.setBackground(new Color(133, 193, 233));
        Pay.setForeground(Color.WHITE);
        image.add(Pay);

        Back = new JButton("Back");
        Back.setBounds(600, 400, 120, 30);
        Back.setBorder(BorderFactory.createEmptyBorder());
        Back.setBackground(new Color(133, 193, 233));
        Back.setForeground(Color.WHITE);
        image.add(Back);

        // ActionListener
        MyActionListener mal = new MyActionListener();
        Pay.addActionListener(mal);
        Back.addActionListener(mal);

        setVisible(true);
    }

    public class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand() == "Pay") {
                dispose();
                new HBL();
            } else if (e.getActionCommand() == "Back") {

            }
        }
    }
}
