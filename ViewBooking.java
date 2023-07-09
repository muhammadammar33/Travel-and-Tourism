package com.travel;

import org.bson.Document;
//import org.bson.types.Binary;

import com.mongodb.client.FindIterable;
// import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import java.awt.image.BufferedImage;
// import java.io.ByteArrayInputStream;
// import java.io.IOException;
import java.util.ArrayList;
// import java.util.Arrays;

// import javax.imageio.ImageIO;
import javax.swing.*;

public class ViewBooking extends JFrame {
    public static void main(String[] args) {
        new ViewBooking();
    }

    JPanel mainPanel, headingPanel, ButtonPanel, listPanel, gridPanel;
    JLabel mainLabel;
    JButton BackButton;

    ViewBooking() {
        setTitle("View Bookings");
        setBounds(10, 10, 1500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        // this.getContentPane().setBackground(new java.awt.Color(153, 255, 204));

        MongoConn c = new MongoConn();
        MongoCollection<Document> collection = c.mongoDatabase.getCollection("Booking");
        ArrayList<Document> doc = new ArrayList<>();
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                doc.add(cursor.next());
            }
        }
        String name = Login.namField.getText();

        Document query = new Document("User", name);

        FindIterable<Document> results = collection.find(query);

        String[] tempForView = new String[7];

        mainPanel = new JPanel(new BorderLayout());
        getContentPane().add(mainPanel);
        headingPanel = new JPanel(new FlowLayout());
        mainPanel.add(headingPanel, BorderLayout.NORTH);
        mainLabel = new JLabel("Destinations");
        headingPanel.add(mainLabel);
        ButtonPanel = new JPanel(new FlowLayout());
        mainPanel.add(ButtonPanel, BorderLayout.SOUTH);

        BackButton = new JButton("Back");
        BackButton.setHorizontalAlignment(SwingConstants.CENTER);
        ButtonPanel.add(BackButton);

        listPanel = new JPanel(new FlowLayout());
        mainPanel.add(listPanel, BorderLayout.CENTER);
        gridPanel = new JPanel(new GridLayout(results.into(new ArrayList<>()).size() + 1, 7, 0, 10));
        listPanel.add(gridPanel);

        JLabel J1 = new JLabel("Booking ID");
        JLabel J2 = new JLabel("Destination");
        JLabel J3 = new JLabel("Accomodation");
        JLabel J4 = new JLabel("Flight");
        JLabel J5 = new JLabel("Guide Name");
        JLabel J6 = new JLabel("Duration");
        JLabel J7 = new JLabel("Total Price");

        mainLabel.setFont(new Font("Open Sans ExtraBold", 0, 24));
        J1.setFont(new Font("Open Sans ExtraBold", 0, 14));
        J2.setFont(new Font("Open Sans ExtraBold", 0, 14));
        J3.setFont(new Font("Open Sans ExtraBold", 0, 14));
        J4.setFont(new Font("Open Sans ExtraBold", 0, 14));
        J5.setFont(new Font("Open Sans ExtraBold", 0, 14));
        J6.setFont(new Font("Open Sans ExtraBold", 0, 14));
        J7.setFont(new Font("Open Sans ExtraBold", 0, 14));

        mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
        J1.setHorizontalAlignment(SwingConstants.CENTER);
        J2.setHorizontalAlignment(SwingConstants.CENTER);
        J3.setHorizontalAlignment(SwingConstants.CENTER);
        J4.setHorizontalAlignment(SwingConstants.CENTER);
        J5.setHorizontalAlignment(SwingConstants.CENTER);
        J6.setHorizontalAlignment(SwingConstants.CENTER);
        J7.setHorizontalAlignment(SwingConstants.CENTER);

        gridPanel.add(J1);
        gridPanel.add(J2);
        gridPanel.add(J3);
        gridPanel.add(J4);
        gridPanel.add(J5);
        gridPanel.add(J6);
        gridPanel.add(J7);

        for (Document document : results) {
            String BID = document.getString("booking_id");
            String DID = document.getString("dest_id");
            String AID = document.getString("accom_id");
            String TID = document.getString("trans_id");
            String TGID = document.getString("tourGuide_id");
            String duration = document.getString("duration");
            String price = document.getString("total_price");
            String dest_name = "";
            String accom_name = "";
            String flight = "";
            String tourGuide = "";
            try {
                MongoCollection destCollection = c.mongoDatabase.getCollection("Destinations");
                MongoCollection accomCollection = c.mongoDatabase.getCollection("Accomodation");
                MongoCollection transCollection = c.mongoDatabase.getCollection("Transportation");
                MongoCollection tourguideCollection = c.mongoDatabase.getCollection("TourGuide");

                Document queryFilter = new Document("destination_id", DID);
                Document personDocument = (Document) destCollection.find(queryFilter).first();

                Document queryFilter1 = new Document("accom_id", AID);
                Document personDocument1 = (Document) accomCollection.find(queryFilter1).first();

                Document queryFilter2 = new Document("trans_id", TID);
                Document personDocument2 = (Document) transCollection.find(queryFilter2).first();

                Document queryFilter3 = new Document("tourGuide_id", TGID);
                Document personDocument3 = (Document) tourguideCollection.find(queryFilter3).first();

                if (personDocument != null) {

                    String name1 = (personDocument.getString("name"));
                    dest_name = name1;

                }
                if (personDocument1 != null) {

                    String name2 = (personDocument1.getString("name"));
                    accom_name = name2;

                }
                if (personDocument2 != null) {

                    String f = (personDocument2.getString("Flight"));
                    flight = f;

                }
                if (personDocument3 != null) {

                    String t = (personDocument3.getString("name"));
                    tourGuide = t;

                }
            } catch (Exception a) {
                a.printStackTrace();
            }

            // adding in tempForView
            tempForView[0] = BID;
            tempForView[1] = dest_name;
            tempForView[2] = accom_name;
            tempForView[3] = flight;
            tempForView[4] = tourGuide;
            tempForView[5] = duration;
            tempForView[6] = price;

            for (int j = 0; j < 7; j++) {
                JLabel J0 = new JLabel(tempForView[j]);
                J0.setFont(new Font("Open Sans", 0, 10));
                J0.setHorizontalAlignment(SwingConstants.CENTER);
                gridPanel.add(J0);
            }
        }

        // ActionListener
        MyActionListener mal = new MyActionListener();
        BackButton.addActionListener(mal);
    }

    public class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand() == "Back") {
                dispose();
            }
        }
    }
}
