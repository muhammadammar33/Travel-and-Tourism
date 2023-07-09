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

// import javax.imageio.ImageIO;
import javax.swing.*;

public class ViewTourGuide extends JFrame {
    public static void main(String[] args) {
        new ViewTourGuide();
    }

    JPanel mainPanel, headingPanel, ButtonPanel, listPanel, gridPanel;
    JLabel mainLabel;
    JButton BackButton, AddButton;

    ViewTourGuide() {
        setTitle("View Tour Guides");
        setBounds(10, 10, 1500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        // this.getContentPane().setBackground(new java.awt.Color(153, 255, 204));

        MongoConn c = new MongoConn();
        MongoCollection<Document> collection = c.mongoDatabase.getCollection("TourGuide");
        ArrayList<Document> documents = new ArrayList<>();
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                documents.add(cursor.next());
            }
        }

        String[] tempForView = new String[6];

        mainPanel = new JPanel(new BorderLayout());
        getContentPane().add(mainPanel);
        headingPanel = new JPanel(new FlowLayout());
        mainPanel.add(headingPanel, BorderLayout.NORTH);
        mainLabel = new JLabel("Tour Guides");
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
        gridPanel = new JPanel(new GridLayout(documents.size() + 1, 6, 0, 10));
        listPanel.add(gridPanel);

        JLabel J1 = new JLabel("ID");
        JLabel J2 = new JLabel("Name");
        JLabel J3 = new JLabel("Language");
        JLabel J4 = new JLabel("Experience");
        JLabel J5 = new JLabel("Fee");
        JLabel J6 = new JLabel("Email");

        mainLabel.setFont(new Font("Open Sans ExtraBold", 0, 24));
        J1.setFont(new Font("Open Sans ExtraBold", 0, 14));
        J2.setFont(new Font("Open Sans ExtraBold", 0, 14));
        J3.setFont(new Font("Open Sans ExtraBold", 0, 14));
        J4.setFont(new Font("Open Sans ExtraBold", 0, 14));
        J5.setFont(new Font("Open Sans ExtraBold", 0, 14));
        J6.setFont(new Font("Open Sans ExtraBold", 0, 14));

        mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
        J1.setHorizontalAlignment(SwingConstants.CENTER);
        J2.setHorizontalAlignment(SwingConstants.CENTER);
        J3.setHorizontalAlignment(SwingConstants.CENTER);
        J4.setHorizontalAlignment(SwingConstants.CENTER);
        J5.setHorizontalAlignment(SwingConstants.CENTER);
        J6.setHorizontalAlignment(SwingConstants.CENTER);

        gridPanel.add(J1);
        gridPanel.add(J2);
        gridPanel.add(J3);
        gridPanel.add(J4);
        gridPanel.add(J5);
        gridPanel.add(J6);

        for (Document document : documents) {
            String ID = document.getString("tourGuide_id");
            String name = document.getString("name");
            String location = document.getString("Language");
            String attractions = document.getString("Experience");
            String fee = document.getString("fee");
            String AvgTemp = document.getString("email");

            // adding in tempForView
            tempForView[0] = ID;
            tempForView[1] = name;
            tempForView[2] = location;
            tempForView[3] = attractions;
            tempForView[4] = fee;
            tempForView[5] = AvgTemp;

            for (int j = 0; j < 6; j++) {
                JLabel J0 = new JLabel(tempForView[j]);
                J0.setFont(new Font("Open Sans", 0, 10));
                J0.setHorizontalAlignment(SwingConstants.CENTER);
                gridPanel.add(J0);
            }
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
                new AddTourGuide();
            }
        }

    }
}
