package com.travel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewDetails extends JFrame {

    JPanel mainPanel, headingPanel, listPanel, gridPanel, ButtonPanel;
    JLabel mainLabel;
    JButton BackButton;

    public static void main(String[] args) {

        new ViewDetails();
    }

    /**
     * Create the frame.
     */
    public ViewDetails() {
        setTitle("View User Details");
        setBounds(10, 10, 1500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        this.getContentPane().setBackground(new java.awt.Color(153, 255, 204));

        // for the grid
        String name = Login.namField.getText();
        Document filter1 = new Document("name", name);
        ArrayList<Document> s1 = new ArrayList<>(Arrays.asList(filter1));

        String[] tempForView = new String[5];

        mainPanel = new JPanel(new BorderLayout());
        getContentPane().add(mainPanel);
        headingPanel = new JPanel(new FlowLayout());
        mainPanel.add(headingPanel, BorderLayout.NORTH);
        mainLabel = new JLabel("Details of User");
        headingPanel.add(mainLabel);
        ButtonPanel = new JPanel(new FlowLayout());
        mainPanel.add(ButtonPanel, BorderLayout.SOUTH);

        BackButton = new JButton("Back");
        BackButton.setHorizontalAlignment(SwingConstants.CENTER);
        ButtonPanel.add(BackButton);

        listPanel = new JPanel(new FlowLayout());
        mainPanel.add(listPanel, BorderLayout.CENTER);
        gridPanel = new JPanel(new GridLayout(s1.size() + 1, 5, 50, 10));
        listPanel.add(gridPanel);

        JLabel J1 = new JLabel("ID");
        JLabel J2 = new JLabel("Name");
        JLabel J3 = new JLabel("Email");
        JLabel J4 = new JLabel("Date Of Birth");
        JLabel J5 = new JLabel("Contact");

        mainLabel.setFont(new Font("Open Sans ExtraBold", 0, 24));
        J1.setFont(new Font("Open Sans ExtraBold", 0, 14));
        J2.setFont(new Font("Open Sans ExtraBold", 0, 14));
        J3.setFont(new Font("Open Sans ExtraBold", 0, 14));
        J4.setFont(new Font("Open Sans ExtraBold", 0, 14));
        J5.setFont(new Font("Open Sans ExtraBold", 0, 14));

        mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
        J1.setHorizontalAlignment(SwingConstants.CENTER);
        J2.setHorizontalAlignment(SwingConstants.CENTER);
        J3.setHorizontalAlignment(SwingConstants.CENTER);
        J4.setHorizontalAlignment(SwingConstants.CENTER);
        J5.setHorizontalAlignment(SwingConstants.CENTER);

        gridPanel.add(J1);
        gridPanel.add(J2);
        gridPanel.add(J3);
        gridPanel.add(J4);
        gridPanel.add(J5);

        // //generating grid
        try {
            MongoConn c = new MongoConn();
            MongoCollection mongoCollection = c.mongoDatabase.getCollection("User");

            Document personDocument = (Document) mongoCollection.find(filter1).first();

            if (personDocument != null) {
                String ID = personDocument.getString("user_id");
                String email = personDocument.getString("email");
                String date = personDocument.getString("date_of_birth");
                String contact = personDocument.getString("contact");

                // adding in tempForView
                tempForView[0] = ID;
                tempForView[1] = name;
                tempForView[2] = email;
                tempForView[3] = date;
                tempForView[4] = contact;

                for (int j = 0; j < 5; j++) {
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
