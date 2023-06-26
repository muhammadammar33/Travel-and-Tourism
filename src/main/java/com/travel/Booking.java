package com.travel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.*;

public class Booking extends JFrame {

    JLabel Title, Id;
    static JTextField ITF;
    JButton Submit, ViewDest, Back;

    Booking() {
        setTitle("Booking");
        setSize(800, 310);
        setLocation(250, 150);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JPanel p1 = new JPanel();
        p1.setBounds(20, 20, 740, 230);
        p1.setLayout(null);
        add(p1);

        Title = new JLabel("First Choose Your Destination");
        Title.setFont(new Font("Calibri", Font.BOLD, 48));
        Title.setBounds(80, 0, 600, 80);
        Title.setForeground(Color.MAGENTA);
        p1.add(Title);

        Id = new JLabel("Enter the Id of Destination: ");
        Id.setFont(new Font("Calibri", Font.BOLD, 24));
        Id.setBounds(120, 100, 300, 40);
        Id.setForeground(Color.blue);
        p1.add(Id);

        ITF = new JTextField();
        ITF.setBounds(420, 100, 200, 40);
        ITF.setBorder(BorderFactory.createEmptyBorder());
        p1.add(ITF);

        Submit = new JButton("Submit");
        Submit.setBounds(120, 180, 120, 30);
        Submit.setBorder(BorderFactory.createEmptyBorder());
        Submit.setBackground(Color.MAGENTA);
        Submit.setForeground(Color.WHITE);
        p1.add(Submit);

        ViewDest = new JButton("View Destinations");
        ViewDest.setBounds(295, 180, 150, 30);
        ViewDest.setBorder(BorderFactory.createEmptyBorder());
        ViewDest.setBackground(Color.MAGENTA);
        ViewDest.setForeground(Color.WHITE);
        p1.add(ViewDest);

        Back = new JButton("Back");
        Back.setBounds(500, 180, 120, 30);
        Back.setBorder(BorderFactory.createEmptyBorder());
        Back.setBackground(Color.MAGENTA);
        Back.setForeground(Color.WHITE);
        p1.add(Back);

        // ActionListener
        MyActionListener mal = new MyActionListener();
        Submit.addActionListener(mal);
        ViewDest.addActionListener(mal);
        Back.addActionListener(mal);

        setVisible(true);
    }

    public class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand() == "Submit") {
                dispose();
                new BookingA();
            } else if (e.getActionCommand() == "Back") {
                dispose();
            } else if (e.getActionCommand() == "View Destinations") {
                new ViewDestinations();
            }
        }

    }

    public static void main(String[] args) {
        new Booking();
    }
}
