package com.travel;

import javax.swing.JFrame;

import org.bson.Document;
//import org.bson.types.Binary;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import java.awt.image.BufferedImage;
// import java.io.ByteArrayInputStream;
// import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

// import javax.imageio.ImageIO;
import javax.swing.*;

public class ViewAccomodations extends JFrame {
    public static void main(String[] args) {
        new ViewAccomodations();
    }

    JPanel mainPanel, headingPanel, ButtonPanel, listPanel, gridPanel;
    JLabel mainLabel;
    JButton BackButton, AddButton;

    ViewAccomodations() {
        setTitle("View Accomodations");
        setBounds(10, 10, 1500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        // this.getContentPane().setBackground(new java.awt.Color(153, 255, 204));

        String ID = Booking.ITF.getText();
        Document filter1 = new Document("destination_id", ID);
        ArrayList<Document> s1 = new ArrayList<>(Arrays.asList(filter1));

        String[] tempForView = new String[4];

        mainPanel = new JPanel(new BorderLayout());
        getContentPane().add(mainPanel);
        headingPanel = new JPanel(new FlowLayout());
        mainPanel.add(headingPanel, BorderLayout.NORTH);
        mainLabel = new JLabel("Accomodations");
        headingPanel.add(mainLabel);
        ButtonPanel = new JPanel(new FlowLayout());
        mainPanel.add(ButtonPanel, BorderLayout.SOUTH);

        AddButton = new JButton("Add");
        AddButton.setHorizontalAlignment(SwingConstants.CENTER);
        ButtonPanel.add(AddButton);

        BackButton = new JButton("Back");
        BackButton.setHorizontalAlignment(SwingConstants.CENTER);
        ButtonPanel.add(BackButton);

        listPanel = new JPanel(new FlowLayout());
        mainPanel.add(listPanel, BorderLayout.CENTER);
        gridPanel = new JPanel(new GridLayout(s1.size() + 1, 4, 0, 10));
        listPanel.add(gridPanel);

        JLabel J1 = new JLabel("ID");
        JLabel J2 = new JLabel("Name");
        JLabel J3 = new JLabel("Location");
        JLabel J4 = new JLabel("Price per Night");

        mainLabel.setFont(new Font("Open Sans ExtraBold", 0, 24));
        J1.setFont(new Font("Open Sans ExtraBold", 0, 14));
        J2.setFont(new Font("Open Sans ExtraBold", 0, 14));
        J3.setFont(new Font("Open Sans ExtraBold", 0, 14));
        J4.setFont(new Font("Open Sans ExtraBold", 0, 14));

        mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
        J1.setHorizontalAlignment(SwingConstants.CENTER);
        J2.setHorizontalAlignment(SwingConstants.CENTER);
        J3.setHorizontalAlignment(SwingConstants.CENTER);
        J4.setHorizontalAlignment(SwingConstants.CENTER);

        gridPanel.add(J1);
        gridPanel.add(J2);
        gridPanel.add(J3);
        gridPanel.add(J4);

        // generating grid
        try {
            MongoConn c = new MongoConn();
            MongoCollection mongoCollection = c.mongoDatabase.getCollection("Accomodation");

            // Document queryFilter = new Document(filter1);
            Document personDocument = (Document) mongoCollection.find(filter1).first();

            if (personDocument != null) {
                String Acc_ID = personDocument.getString("accom_id");
                String name = personDocument.getString("name");
                String location = personDocument.getString("location");
                String Price = personDocument.getString("price_per_night");

                // adding in tempForView
                tempForView[0] = Acc_ID;
                tempForView[1] = name;
                tempForView[2] = location;
                tempForView[3] = Price;

                for (int j = 0; j < 4; j++) {
                    JLabel J0 = new JLabel(tempForView[j]);
                    J0.setFont(new Font("Open Sans", 0, 10));
                    J0.setHorizontalAlignment(SwingConstants.CENTER);
                    gridPanel.add(J0);
                }
            }

        } catch (Exception a) {
            a.printStackTrace();
        }

        // ActionListener
        MyActionListener mal = new MyActionListener();
        AddButton.addActionListener(mal);
        BackButton.addActionListener(mal);
    }

    public class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand() == "Back") {
                dispose();
            } else if (e.getActionCommand() == "Add") {
                dispose();
                new AddAccomodation();
            }
        }

    }
}
