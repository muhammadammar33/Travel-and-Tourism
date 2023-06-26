package com.travel;

import javax.swing.JFrame;

import org.bson.Document;
// import org.bson.types.Binary;
// import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import java.awt.image.BufferedImage;
// import java.io.ByteArrayInputStream;
// import java.io.IOException;
import java.util.ArrayList;

// import javax.imageio.ImageIO;
import javax.swing.*;

public class ViewTransportation extends JFrame {
    public static void main(String[] args) {
        new ViewTransportation();
    }

    JPanel mainPanel, headingPanel, ButtonPanel, listPanel, gridPanel;
    JLabel mainLabel;
    JButton BackButton, AddButton;

    ViewTransportation() {
        setTitle("View Transportation");
        setBounds(10, 10, 1500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        // this.getContentPane().setBackground(new java.awt.Color(153, 255, 204));

        MongoConn c = new MongoConn();
        MongoCollection<Document> collection = c.mongoDatabase.getCollection("Transportation");
        ArrayList<Document> doc = new ArrayList<>();
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                doc.add(cursor.next());
            }
        }

        String ID = Booking.ITF.getText();
        try {
            MongoCollection<Document> destCollection = c.mongoDatabase.getCollection("Destinations");
            MongoCollection<Document> transCollection = c.mongoDatabase.getCollection("Transportation");

            Document queryFilter = new Document("destination_id", ID);
            Document personDocument = (Document) destCollection.find(queryFilter).first();

            if (personDocument != null) {
                String name = (personDocument.getString("name"));

                Document queryFilter1 = new Document("destination", name);
                // Document personDocument1 = (Document)
                // transCollection.find(queryFilter1).first();

                FindIterable<Document> documents = transCollection.find(queryFilter1);

                String[] tempForView = new String[5];

                mainPanel = new JPanel(new BorderLayout());
                getContentPane().add(mainPanel);
                headingPanel = new JPanel(new FlowLayout());
                mainPanel.add(headingPanel, BorderLayout.NORTH);
                mainLabel = new JLabel("Transportations");
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
                gridPanel = new JPanel(new GridLayout(documents.into(new ArrayList<>()).size() + 1, 5, 50, 10));
                listPanel.add(gridPanel);

                JLabel J1 = new JLabel("ID");
                JLabel J2 = new JLabel("Flight");
                JLabel J3 = new JLabel("Destination");
                JLabel J4 = new JLabel("Flight Rent");
                JLabel J5 = new JLabel("Car Rent");
                // JLabel J6 = new JLabel("Image");

                mainLabel.setFont(new Font("Open Sans ExtraBold", 0, 24));
                J1.setFont(new Font("Open Sans ExtraBold", 0, 14));
                J2.setFont(new Font("Open Sans ExtraBold", 0, 14));
                J3.setFont(new Font("Open Sans ExtraBold", 0, 14));
                J4.setFont(new Font("Open Sans ExtraBold", 0, 14));
                J5.setFont(new Font("Open Sans ExtraBold", 0, 14));
                // J6.setFont(new Font("Open Sans ExtraBold", 0, 14));

                mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
                J1.setHorizontalAlignment(SwingConstants.CENTER);
                J2.setHorizontalAlignment(SwingConstants.CENTER);
                J3.setHorizontalAlignment(SwingConstants.CENTER);
                J4.setHorizontalAlignment(SwingConstants.CENTER);
                J5.setHorizontalAlignment(SwingConstants.CENTER);
                // J6.setHorizontalAlignment(SwingConstants.CENTER);

                gridPanel.add(J1);
                gridPanel.add(J2);
                gridPanel.add(J3);
                gridPanel.add(J4);
                gridPanel.add(J5);
                // gridPanel.add(J6);

                for (Document document : documents) {
                    String trans_ID = document.getString("trans_id");
                    String flight = document.getString("Flight");
                    String destination = document.getString("destination");
                    String flightRent = document.getString("FlightRent");
                    String carRent = document.getString("CarRent");
                    // adding in tempForView
                    tempForView[0] = trans_ID;
                    tempForView[1] = flight;
                    tempForView[2] = destination;
                    tempForView[3] = flightRent;
                    tempForView[4] = carRent;

                    for (int j = 0; j < 5; j++) {
                        JLabel J0 = new JLabel(tempForView[j]);
                        J0.setFont(new Font("Open Sans", 0, 10));
                        J0.setHorizontalAlignment(SwingConstants.CENTER);
                        gridPanel.add(J0);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "There is no Flight to That destination!", "No Flight",
                        JOptionPane.ERROR_MESSAGE);
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
                new AddTransportation();
            }
        }
    }
}
