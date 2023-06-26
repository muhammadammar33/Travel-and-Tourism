package com.travel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dashboard extends JFrame {

    JLabel dash, title, t1, t2;
    JButton ViewDetails, UpdateDetails, Booking, ViewBooking, Back;

    Dashboard() {
        setTitle("Dashboard");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JPanel p1 = new JPanel();
        p1.setBounds(0, 0, 1280, 70);
        p1.setBackground(new Color(153, 0, 76));
        p1.setLayout(null);
        add(p1);

        ImageIcon i1 = new ImageIcon(getClass().getResource("Images/Dashboard.png"));
        Image i2 = i1.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 70, 70);
        p1.add(image);

        dash = new JLabel("DASHBOARD");
        dash.setFont(new Font("Calibri", Font.BOLD, 48));
        dash.setBounds(80, 0, 260, 80);
        dash.setForeground(Color.white);
        p1.add(dash);

        JPanel p2 = new JPanel();
        p2.setBounds(0, 70, 340, 710);
        p2.setBackground(new Color(153, 0, 76));
        p2.setLayout(null);
        add(p2);

        ImageIcon i4 = new ImageIcon(getClass().getResource("Images/home.jpg"));
        Image i5 = i4.getImage().getScaledInstance(940, 720, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel image1 = new JLabel(i6);
        image1.setBounds(340, 70, 940, 720);
        add(image1);

        title = new JLabel("Welcome " + Login.namField.getText());
        title.setBounds(0, 50, 940, 70);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Calibri", Font.BOLD, 48));
        title.setForeground(new Color(0, 102, 51));
        image1.add(title);

        t1 = new JLabel("To");
        t1.setBounds(0, 120, 940, 70);
        t1.setHorizontalAlignment(SwingConstants.CENTER);
        t1.setFont(new Font("Calibri", Font.BOLD, 48));
        t1.setForeground(Color.RED);
        image1.add(t1);

        t2 = new JLabel("         Travel               and               Tourism");
        t2.setBounds(0, 600, 940, 70);
        t2.setHorizontalAlignment(SwingConstants.CENTER);
        t2.setFont(new Font("Calibri", Font.BOLD, 48));
        t2.setForeground(new Color(0, 102, 51));
        image1.add(t2);

        ViewDetails = new JButton("View User Details");
        ViewDetails.setBounds(0, 0, 340, 50);
        ViewDetails.setBorder(BorderFactory.createEmptyBorder());
        ViewDetails.setBackground(new Color(153, 0, 76));
        ViewDetails.setFont(new Font("Calibri", Font.BOLD, 32));
        ViewDetails.setForeground(Color.WHITE);
        p2.add(ViewDetails);

        UpdateDetails = new JButton("Update User Details");
        UpdateDetails.setBounds(0, 50, 340, 50);
        UpdateDetails.setBorder(BorderFactory.createEmptyBorder());
        UpdateDetails.setBackground(new Color(153, 0, 76));
        UpdateDetails.setFont(new Font("Calibri", Font.BOLD, 32));
        UpdateDetails.setForeground(Color.WHITE);
        p2.add(UpdateDetails);

        Booking = new JButton("Booking");
        Booking.setBounds(0, 100, 340, 50);
        Booking.setBorder(BorderFactory.createEmptyBorder());
        Booking.setBackground(new Color(153, 0, 76));
        Booking.setFont(new Font("Calibri", Font.BOLD, 32));
        Booking.setForeground(Color.WHITE);
        p2.add(Booking);

        ViewBooking = new JButton("View Booking");
        ViewBooking.setBounds(0, 150, 340, 50);
        ViewBooking.setBorder(BorderFactory.createEmptyBorder());
        ViewBooking.setBackground(new Color(153, 0, 76));
        ViewBooking.setFont(new Font("Calibri", Font.BOLD, 32));
        ViewBooking.setForeground(Color.WHITE);
        p2.add(ViewBooking);

        Back = new JButton("Log Out");
        Back.setBounds(0, 650, 340, 50);
        Back.setBorder(BorderFactory.createEmptyBorder());
        Back.setBackground(new Color(153, 0, 76));
        Back.setFont(new Font("Calibri", Font.BOLD, 32));
        Back.setForeground(Color.WHITE);
        p2.add(Back);

        // ActionListener
        MyActionListener mal = new MyActionListener();
        ViewDetails.addActionListener(mal);
        UpdateDetails.addActionListener(mal);
        Booking.addActionListener(mal);
        ViewBooking.addActionListener(mal);
        Back.addActionListener(mal);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Dashboard();
    }

    public class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand() == "View User Details") {
                new ViewDetails();
            } else if (e.getActionCommand() == "Update User Details") {
                new UpdateDetails();
            } else if (e.getActionCommand() == "Booking") {
                new Booking();
            } else if (e.getActionCommand() == ("View Booking")) {
                new ViewBooking();
            } else if (e.getActionCommand() == ("Log Out")) {
                dispose();
                new Login();
            }
        }
    }
}
