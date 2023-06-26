package com.travel;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import org.bson.Document;

public class UpdateDetails extends JFrame {

    JLabel idLabel, idLabel2, namLabel, namLabel2, Email, Date, DF, Contact, CF;
    JTextField ETF, DTF, CTF;
    JButton backButton, submit;

    UpdateDetails() {
        setTitle("Update User Details");
        setSize(800, 410);
        setLocation(250, 150);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JPanel p1 = new JPanel();
        p1.setBounds(20, 20, 370, 330);
        p1.setLayout(null);
        add(p1);

        ImageIcon i1 = new ImageIcon(getClass().getResource("Images/update.png"));
        Image i2 = i1.getImage().getScaledInstance(210, 360, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(420, 0, 350, 360);
        add(image);

        String name = Login.namField.getText();
        try {
            MongoConn c = new MongoConn();
            MongoCollection mongoCollection = c.mongoDatabase.getCollection("User");

            Document queryFilter = new Document("name", name);
            Document personDocument = (Document) mongoCollection.find(queryFilter).first();

            if (personDocument != null) {

                String id = (personDocument.getString("user_id"));

                idLabel2 = new JLabel(id);
                idLabel2.setBounds(160, 30, 160, 40);
                idLabel2.setFont(new Font("Calibri", Font.BOLD, 32));
                idLabel2.setForeground(new Color(23, 23, 23));
                idLabel2.setHorizontalAlignment(SwingConstants.CENTER);
                p1.add(idLabel2);

            } else {
                JOptionPane.showMessageDialog(null, "User not found.");
            }

        } catch (Exception a) {
            a.printStackTrace();
        }

        idLabel = new JLabel("User ID:");
        idLabel.setBounds(20, 30, 120, 40);
        idLabel.setFont(new Font("Calibri", Font.BOLD, 32));
        idLabel.setForeground(new Color(23, 23, 23));
        idLabel.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(idLabel);

        namLabel = new JLabel("Name:");
        namLabel.setBounds(20, 90, 120, 40);
        namLabel.setFont(new Font("Calibri", Font.BOLD, 32));
        namLabel.setForeground(new Color(23, 23, 23));
        namLabel.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(namLabel);

        namLabel2 = new JLabel(name);
        namLabel2.setBounds(160, 90, 160, 40);
        namLabel2.setFont(new Font("Calibri", Font.BOLD, 32));
        namLabel2.setForeground(new Color(23, 23, 23));
        namLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(namLabel2);

        Email = new JLabel("Email:");
        Email.setBounds(20, 150, 80, 20);
        Email.setFont(new Font("Calibri", Font.BOLD, 16));
        Email.setForeground(new Color(23, 23, 23));
        Email.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(Email);

        ETF = new JTextField();
        ETF.setBounds(120, 150, 200, 20);
        ETF.setBorder(BorderFactory.createEmptyBorder());
        p1.add(ETF);

        Date = new JLabel("Date Of Birth:");
        Date.setBounds(20, 190, 80, 20);
        Date.setFont(new Font("Calibri", Font.BOLD, 12));
        Date.setForeground(new Color(23, 23, 23));
        Date.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(Date);

        DF = new JLabel("YYYY-MM-DD");
        DF.setBounds(120, 215, 200, 15);
        DF.setForeground(new Color(255, 51, 51));
        DF.setFont(new Font("Calibri", Font.BOLD, 9));
        p1.add(DF);

        DTF = new JTextField();
        DTF.setBounds(120, 190, 200, 20);
        DTF.setBorder(BorderFactory.createEmptyBorder());
        p1.add(DTF);

        Contact = new JLabel("Contact:");
        Contact.setBounds(20, 230, 80, 20);
        Contact.setFont(new Font("Calibri", Font.BOLD, 16));
        Contact.setForeground(new Color(23, 23, 23));
        Contact.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(Contact);

        CTF = new JTextField();
        CTF.setBounds(120, 230, 200, 20);
        CTF.setBorder(BorderFactory.createEmptyBorder());
        p1.add(CTF);

        CF = new JLabel("03*********");
        CF.setBounds(120, 255, 200, 15);
        CF.setForeground(new Color(255, 51, 51));
        CF.setFont(new Font("Calibri", Font.BOLD, 9));
        p1.add(CF);

        submit = new JButton("Submit");
        submit.setBounds(40, 280, 120, 30);
        submit.setBorder(BorderFactory.createEmptyBorder());
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        p1.add(submit);

        backButton = new JButton("Back");
        backButton.setBounds(180, 280, 120, 30);
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        p1.add(backButton);

        // ActionListener
        MyActionListener mal = new MyActionListener();
        submit.addActionListener(mal);
        backButton.addActionListener(mal);

        setVisible(true);
    }

    public static void main(String[] args) {
        new UpdateDetails();
    }

    public class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand() == "Submit") {
                if (ETF.getText().isEmpty() || DTF.getText().isEmpty() || CTF.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all the fields!", "Empty Fields",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!(ETF.getText().contains("@")) && !(ETF.getText().contains(".com"))) {
                    JOptionPane.showMessageDialog(null, "Sign Up unsuccessful!! Please enter valid Email!!");
                    ETF.setText("");
                    return;
                }
                if (!(CTF.getText().matches("[0]{1}[3]{1}[0-9]{9}"))) {
                    JOptionPane.showMessageDialog(null, "Sign Up unsuccessful!! Please enter valid Phone Number!!");
                    CTF.setText("");
                    return;
                } else {
                    String ID = idLabel2.getText();
                    String NAME = namLabel2.getText();
                    String EMAIL = ETF.getText();
                    String DATE = DTF.getText();
                    String Contact = CTF.getText();

                    try {
                        MongoConn c = new MongoConn();
                        MongoCollection mongoCollection = c.mongoDatabase.getCollection("User");

                        Document queryFilter = new Document("name", NAME)
                                .append("user_id", ID);
                        Document updateDocument = new Document("$set", new Document("email", EMAIL)
                                .append("date_of_birth", DATE)
                                .append("contact", Contact));
                        UpdateResult result = mongoCollection.updateOne(queryFilter, updateDocument);

                        if (result.getModifiedCount() > 0) {
                            JOptionPane.showMessageDialog(null, "Updated Successfully !!!!");

                        } else {
                            JOptionPane.showMessageDialog(null, "Error in Updation !!!!");
                        }
                    } catch (Exception a) {
                        a.printStackTrace();
                    }
                }
            } else if (e.getActionCommand() == ("Back")) {
                dispose();
            }
        }

    }
}
