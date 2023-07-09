package com.travel;

import java.awt.event.*;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;
import java.awt.*;

import javax.swing.*;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

public class AddTransportation extends JFrame {

    JLabel ID, Name, Location, Dest, FlightRent, CarRent;
    JTextField IDTF, NTF, ATTF, ATF;
    JButton AddButton, backButton;

    AddTransportation() {
        setTitle("Add Transportation");
        setSize(800, 380);
        setLocation(250, 150);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JPanel p1 = new JPanel();
        p1.setBounds(20, 20, 370, 300);
        // p1.setBackground(new Color(133, 193, 233));
        p1.setLayout(null);
        add(p1);

        ImageIcon i1 = new ImageIcon(getClass().getResource("Images/Trans.jpg"));
        Image i2 = i1.getImage().getScaledInstance(320, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(410, 20, 350, 300);
        add(image);

        ID = new JLabel("Transportation ID:");
        ID.setBounds(20, 30, 100, 20);
        ID.setFont(new Font("Calibri", Font.BOLD, 12));
        ID.setForeground(new Color(0, 50, 204));
        ID.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(ID);

        IDTF = new JTextField();
        IDTF.setBounds(140, 30, 180, 20);
        IDTF.setBorder(BorderFactory.createEmptyBorder());
        p1.add(IDTF);

        Name = new JLabel("Flight:");
        Name.setBounds(20, 70, 80, 20);
        Name.setFont(new Font("Calibri", Font.BOLD, 16));
        Name.setForeground(new Color(0, 50, 204));
        Name.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(Name);

        NTF = new JTextField();
        NTF.setBounds(120, 70, 200, 20);
        NTF.setBorder(BorderFactory.createEmptyBorder());
        p1.add(NTF);

        Location = new JLabel("Destination:");
        Location.setBounds(20, 110, 100, 20);
        Location.setFont(new Font("Calibri", Font.BOLD, 16));
        Location.setForeground(new Color(0, 50, 204));
        Location.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(Location);

        String ID = Booking.ITF.getText();
        try {
            MongoConn c = new MongoConn();
            MongoCollection mongoCollection = c.mongoDatabase.getCollection("Destinations");

            Document queryFilter = new Document("destination_id", ID);
            Document personDocument = (Document) mongoCollection.find(queryFilter).first();

            if (personDocument != null) {

                String name = (personDocument.getString("name"));

                Dest = new JLabel(name);
                Dest.setBounds(140, 110, 180, 20);
                Dest.setFont(new Font("Calibri", Font.BOLD, 16));
                Dest.setForeground(new Color(0, 50, 204));
                Dest.setHorizontalAlignment(SwingConstants.CENTER);
                p1.add(Dest);

            } else {
                JOptionPane.showMessageDialog(null, "User not found.");
            }

        } catch (Exception a) {
            a.printStackTrace();
        }

        FlightRent = new JLabel("Flight Rent:");
        FlightRent.setBounds(20, 150, 80, 20);
        FlightRent.setFont(new Font("Calibri", Font.BOLD, 14));
        FlightRent.setForeground(new Color(0, 50, 204));
        FlightRent.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(FlightRent);

        ATF = new JTextField();
        ATF.setBounds(120, 150, 200, 20);
        ATF.setBorder(BorderFactory.createEmptyBorder());
        p1.add(ATF);

        CarRent = new JLabel("Car Rent:");
        CarRent.setBounds(20, 190, 80, 20);
        CarRent.setFont(new Font("Calibri", Font.BOLD, 14));
        CarRent.setForeground(new Color(0, 50, 204));
        CarRent.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(CarRent);

        ATTF = new JTextField();
        ATTF.setBounds(120, 190, 200, 20);
        ATTF.setBorder(BorderFactory.createEmptyBorder());
        p1.add(ATTF);

        AddButton = new JButton("Add");
        AddButton.setBounds(40, 240, 120, 30);
        AddButton.setBorder(BorderFactory.createEmptyBorder());
        AddButton.setBackground(new Color(133, 193, 233));
        AddButton.setForeground(Color.WHITE);
        p1.add(AddButton);

        backButton = new JButton("Back");
        backButton.setBounds(180, 240, 120, 30);
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.setBackground(new Color(133, 193, 233));
        backButton.setForeground(Color.WHITE);
        p1.add(backButton);

        // ActionListener
        MyActionListener mal = new MyActionListener();
        AddButton.addActionListener(mal);
        backButton.addActionListener(mal);

        setVisible(true);
    }

    public static void main(String[] args) {
        new AddTransportation();
    }

    public class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand() == "Add") {
                if (IDTF.getText().isEmpty() || NTF.getText().isEmpty()
                        || ATF.getText().isEmpty() || ATTF.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all the fields!", "Empty Fields",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    String ID = IDTF.getText();
                    String name = NTF.getText();
                    String destination = Dest.getText();
                    String FlightRent = ATF.getText();
                    String CarRent = ATTF.getText();

                    try {
                        MongoConn c = new MongoConn();

                        MongoCollection mongoCollection = c.mongoDatabase.getCollection("Transportation");

                        Document document = new Document("trans_id", ID);
                        document.append("Flight", name);
                        document.append("destination", destination);
                        document.append("FlightRent", FlightRent);
                        document.append("CarRent", CarRent);

                        Document idfilter = new Document("trans_id", ID);
                        Document personDocument = (Document) mongoCollection.find(idfilter).first();

                        if (personDocument != null) {
                            JOptionPane.showMessageDialog(null,
                                    "This ID already exits. Please choose another one. Thanks");
                            IDTF.setText("");
                        } else {
                            mongoCollection.insertOne(document);

                            JOptionPane.showMessageDialog(null, "Transportation added Successfully");
                            dispose();
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
