package com.travel;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import javax.swing.*;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

public class Login extends JFrame {

    static JTextField namField;
    JTextField PassField;
    JButton LoginButton, SignupButton, ForButton;

    Login() {
        setTitle("Login");
        setSize(800, 400);
        setLocation(250, 200);
        setLayout(null);

        getContentPane().setBackground(Color.WHITE);

        JPanel p1 = new JPanel();
        p1.setBounds(0, 0, 400, 400);
        p1.setBackground(Color.WHITE);
        add(p1);

        ImageIcon i1 = new ImageIcon(getClass().getResource("Images/Login.jpg"));
        Image i2 = i1.getImage().getScaledInstance(400, 350, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        p1.add(image);

        JPanel p2 = new JPanel();
        p2.setLayout(null);
        p2.setBounds(420, 20, 340, 320);
        add(p2);

        JLabel UName = new JLabel("UserName:");
        UName.setBounds(20, 30, 100, 25);
        UName.setFont(new Font("Calibri", Font.PLAIN, 20));
        UName.setForeground(new Color(0, 50, 204));
        p2.add(UName);

        namField = new JTextField();
        namField.setBounds(20, 70, 300, 25);
        namField.setBorder(BorderFactory.createEmptyBorder());
        p2.add(namField);

        JLabel UPass = new JLabel("Password:");
        UPass.setBounds(20, 110, 100, 25);
        UPass.setFont(new Font("Calibri", Font.PLAIN, 20));
        UPass.setForeground(new Color(0, 50, 204));
        p2.add(UPass);

        PassField = new JTextField();
        PassField.setBounds(20, 150, 300, 25);
        PassField.setBorder(BorderFactory.createEmptyBorder());
        p2.add(PassField);

        LoginButton = new JButton("Login");
        LoginButton.setBounds(20, 200, 120, 30);
        LoginButton.setBorder(BorderFactory.createEmptyBorder());
        LoginButton.setBackground(new Color(133, 193, 233));
        LoginButton.setForeground(Color.WHITE);
        p2.add(LoginButton);

        SignupButton = new JButton("SignUp");
        SignupButton.setBounds(200, 200, 120, 30);
        SignupButton.setBorder(BorderFactory.createEmptyBorder());
        SignupButton.setBackground(new Color(133, 193, 233));
        SignupButton.setForeground(Color.WHITE);
        p2.add(SignupButton);

        ForButton = new JButton("Forget Password");
        ForButton.setBounds(100, 240, 150, 30);
        ForButton.setBorder(BorderFactory.createEmptyBorder());
        ForButton.setBackground(new Color(133, 193, 233));
        ForButton.setForeground(Color.WHITE);
        p2.add(ForButton);

        // ActionListener
        MyActionListener mal = new MyActionListener();
        LoginButton.addActionListener(mal);
        SignupButton.addActionListener(mal);
        ForButton.addActionListener(mal);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Login();
    }

    public class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand() == "Login") {
                if (namField.getText().isEmpty() || PassField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter the UserName!", "Empty Field",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    String name = namField.getText();
                    String password = PassField.getText();

                    Document filter1 = new Document("name", name);
                    Document filter2 = new Document("password", password);

                    try {
                        MongoConn c = new MongoConn();

                        MongoCollection mongoCollection = c.mongoDatabase.getCollection("User");

                        Document queryFilter = new Document("$and", Arrays.asList(filter1, filter2));
                        Document personDocument = (Document) mongoCollection.find(queryFilter).first();

                        if (personDocument != null) {
                            dispose();
                            new Dashboard();
                        } else {
                            JOptionPane.showMessageDialog(null, "Incorrect name or password");
                        }

                    } catch (Exception a) {
                        a.printStackTrace();
                    }
                }
            } else if (e.getActionCommand() == ("SignUp")) {
                dispose();
                new SignUp();
            } else if (e.getActionCommand() == ("Forget Password")) {
                dispose();
                new ForgetPassword();
            }
        }
    }
}
