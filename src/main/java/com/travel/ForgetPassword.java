package com.travel;

import javax.swing.*;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import java.awt.*;
import java.awt.event.*;

public class ForgetPassword extends JFrame {

    JLabel UName, UPass, SecurityQ, Answer;
    JTextField NTF, QTF, ATF, PTF;
    JButton SearchButton, RetrieveButton, backButton;

    ForgetPassword() {
        setTitle("Forget Password");
        setSize(800, 400);
        setLocation(250, 200);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JPanel p1 = new JPanel();
        p1.setBounds(20, 20, 400, 320);
        p1.setLayout(null);
        add(p1);

        ImageIcon i1 = new ImageIcon(getClass().getResource("Images/forgotpassword.jpg"));
        Image i2 = i1.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(420, 0, 350, 350);
        add(image);

        UName = new JLabel("UserName:");
        UName.setBounds(20, 30, 80, 30);
        UName.setFont(new Font("Calibri", Font.BOLD, 16));
        UName.setForeground(new Color(0, 50, 204));
        p1.add(UName);

        NTF = new JTextField();
        NTF.setBounds(160, 30, 200, 30);
        NTF.setBorder(BorderFactory.createEmptyBorder());
        p1.add(NTF);

        SearchButton = new JButton("Search");
        SearchButton.setBounds(200, 70, 120, 30);
        SearchButton.setBorder(BorderFactory.createEmptyBorder());
        SearchButton.setBackground(Color.GRAY);
        SearchButton.setForeground(Color.WHITE);
        p1.add(SearchButton);

        SecurityQ = new JLabel("Security Question:");
        SecurityQ.setBounds(20, 110, 130, 30);
        SecurityQ.setFont(new Font("Calibri", Font.BOLD, 16));
        SecurityQ.setForeground(new Color(0, 50, 204));
        p1.add(SecurityQ);

        QTF = new JTextField();
        QTF.setBounds(160, 110, 200, 30);
        QTF.setBorder(BorderFactory.createEmptyBorder());
        p1.add(QTF);

        Answer = new JLabel("Answer:");
        Answer.setBounds(20, 150, 130, 30);
        Answer.setFont(new Font("Calibri", Font.BOLD, 16));
        Answer.setForeground(new Color(0, 50, 204));
        p1.add(Answer);

        ATF = new JTextField();
        ATF.setBounds(160, 150, 200, 30);
        ATF.setBorder(BorderFactory.createEmptyBorder());
        p1.add(ATF);

        RetrieveButton = new JButton("Retrieve");
        RetrieveButton.setBounds(200, 190, 120, 30);
        RetrieveButton.setBorder(BorderFactory.createEmptyBorder());
        RetrieveButton.setBackground(Color.GRAY);
        RetrieveButton.setForeground(Color.WHITE);
        p1.add(RetrieveButton);

        UPass = new JLabel("Password:");
        UPass.setBounds(20, 230, 130, 30);
        UPass.setFont(new Font("Calibri", Font.BOLD, 16));
        UPass.setForeground(new Color(0, 50, 204));
        p1.add(UPass);

        PTF = new JTextField();
        PTF.setBounds(160, 230, 200, 30);
        PTF.setBorder(BorderFactory.createEmptyBorder());
        p1.add(PTF);

        backButton = new JButton("Back");
        backButton.setBounds(200, 270, 120, 30);
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.setBackground(Color.GRAY);
        backButton.setForeground(Color.WHITE);
        p1.add(backButton);

        // ActionListener
        MyActionListener mal = new MyActionListener();
        SearchButton.addActionListener(mal);
        RetrieveButton.addActionListener(mal);
        backButton.addActionListener(mal);

        setVisible(true);
    }

    public static void main(String[] args) {
        new ForgetPassword();
    }

    public class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand() == "Search") {
                if (NTF.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter the UserName!", "Empty Field",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    String name = NTF.getText();

                    // Document filter1 = new Document("name", name);
                    try {
                        MongoConn c = new MongoConn();
                        MongoCollection mongoCollection = c.mongoDatabase.getCollection("User");

                        Document queryFilter = new Document("name", name);
                        Document personDocument = (Document) mongoCollection.find(queryFilter).first();

                        if (personDocument != null) {

                            QTF.setText(personDocument.getString("question"));
                        } else {
                            JOptionPane.showMessageDialog(null, "User not found.");
                        }

                    } catch (Exception a) {
                        a.printStackTrace();
                    }
                }
            } else if (e.getActionCommand() == ("Retrieve")) {
                if (ATF.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter the Answer first!", "Empty Field",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    String name = NTF.getText();
                    String answer = ATF.getText();

                    try {
                        MongoConn c = new MongoConn();
                        MongoCollection mongoCollection = c.mongoDatabase.getCollection("User");

                        Document queryFilter = new Document("name", name).append("answer", answer);
                        Document personDocument = (Document) mongoCollection.find(queryFilter).first();

                        if (personDocument != null) {

                            PTF.setText(personDocument.getString("password"));
                        } else {
                            JOptionPane.showMessageDialog(null, "Wrong Answer !!!!");
                        }
                    } catch (Exception a) {
                        a.printStackTrace();
                    }
                }
            } else if (e.getActionCommand() == ("Back")) {
                dispose();
                new Login();
            }
        }
    }
}