package com.travel;

import java.awt.event.*;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;
import java.awt.*;

import javax.swing.*;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

public class AddAccomodation extends JFrame {

    JLabel AID, DID1, DID2, Name, Location, price;
    JTextField IDTF, NTF, LTF, PTF;
    JButton AddButton, backButton;

    AddAccomodation() {
        setTitle("Add Accomodation");
        setSize(800, 430);
        setLocation(250, 150);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JPanel p1 = new JPanel();
        p1.setBounds(20, 20, 370, 350);
        // p1.setBackground(new Color(133, 193, 233));
        p1.setLayout(null);
        add(p1);

        ImageIcon i1 = new ImageIcon(getClass().getResource("Images/Attrac.jpg"));
        Image i2 = i1.getImage().getScaledInstance(400, 260, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(390, 0, 400, 400);
        add(image);

        DID1 = new JLabel("Destination ID");
        DID1.setBounds(20, 30, 200, 40);
        DID1.setFont(new Font("Calibri", Font.BOLD, 32));
        DID1.setForeground(new Color(23, 23, 23));
        DID1.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(DID1);

        String Dest_id = Booking.ITF.getText();
        DID2 = new JLabel(Dest_id);
        DID2.setBounds(220, 30, 100, 40);
        DID2.setFont(new Font("Calibri", Font.BOLD, 32));
        DID2.setForeground(new Color(23, 23, 23));
        DID2.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(DID2);

        AID = new JLabel("Accomodation ID");
        AID.setBounds(20, 90, 100, 30);
        AID.setFont(new Font("Calibri", Font.BOLD, 12));
        AID.setForeground(new Color(0, 50, 204));
        AID.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(AID);

        IDTF = new JTextField();
        IDTF.setBounds(140, 90, 200, 30);
        IDTF.setBorder(BorderFactory.createEmptyBorder());
        p1.add(IDTF);

        Name = new JLabel("Name:");
        Name.setBounds(20, 140, 80, 30);
        Name.setFont(new Font("Calibri", Font.BOLD, 16));
        Name.setForeground(new Color(0, 50, 204));
        Name.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(Name);

        NTF = new JTextField();
        NTF.setBounds(140, 140, 200, 30);
        NTF.setBorder(BorderFactory.createEmptyBorder());
        p1.add(NTF);

        Location = new JLabel("Location:");
        Location.setBounds(20, 190, 80, 30);
        Location.setFont(new Font("Calibri", Font.BOLD, 16));
        Location.setForeground(new Color(0, 50, 204));
        Location.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(Location);

        LTF = new JTextField();
        LTF.setBounds(140, 190, 200, 30);
        LTF.setBorder(BorderFactory.createEmptyBorder());
        p1.add(LTF);

        price = new JLabel("Price per Night:");
        price.setBounds(0, 240, 120, 30);
        price.setFont(new Font("Calibri", Font.BOLD, 14));
        price.setForeground(new Color(0, 50, 204));
        price.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(price);

        PTF = new JTextField();
        PTF.setBounds(140, 240, 200, 30);
        PTF.setBorder(BorderFactory.createEmptyBorder());
        p1.add(PTF);

        AddButton = new JButton("Add");
        AddButton.setBounds(40, 300, 120, 30);
        AddButton.setBorder(BorderFactory.createEmptyBorder());
        AddButton.setBackground(new Color(133, 193, 233));
        AddButton.setForeground(Color.WHITE);
        p1.add(AddButton);

        backButton = new JButton("Back");
        backButton.setBounds(180, 300, 120, 30);
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
        new AddAccomodation();
    }

    public class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand() == "Add") {
                if (IDTF.getText().isEmpty() || NTF.getText().isEmpty() || LTF.getText().isEmpty()
                        || PTF.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all the fields!", "Empty Fields",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    String Dest_ID = Booking.ITF.getText();
                    String ID = IDTF.getText();
                    String name = NTF.getText();
                    String Location = LTF.getText();
                    String price = PTF.getText();

                    try {
                        MongoConn c = new MongoConn();

                        MongoCollection mongoCollection = c.mongoDatabase.getCollection("Accomodation");

                        Document document = new Document("destination_id", Dest_ID);
                        document.append("accom_id", ID);
                        document.append("name", name);
                        document.append("location", Location);
                        document.append("price_per_night", price);

                        Document idfilter = new Document("accom_id", ID);
                        Document personDocument = (Document) mongoCollection.find(idfilter).first();

                        if (personDocument != null) {
                            JOptionPane.showMessageDialog(null,
                                    "This ID already exits. Please choose another one. Thanks");
                            IDTF.setText("");
                        } else {
                            mongoCollection.insertOne(document);

                            JOptionPane.showMessageDialog(null, "Accomodation added Successfully");
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
