package com.travel;

import java.awt.event.*;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;
import java.awt.*;

import javax.swing.*;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

public class AddDestination extends JFrame {

    JLabel ID, Name, Location, Attractions, Avg_temp;
    JTextField IDTF, NTF, LTF, ATTF;
    JTextArea ATF;
    JButton AddButton, backButton;

    AddDestination() {
        setTitle("Add Destination");
        setSize(800, 450);
        setLocation(250, 150);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JPanel p1 = new JPanel();
        p1.setBounds(20, 20, 370, 370);
        // p1.setBackground(new Color(133, 193, 233));
        p1.setLayout(null);
        add(p1);

        ImageIcon i1 = new ImageIcon(getClass().getResource("Images/Dest.jpg"));
        Image i2 = i1.getImage().getScaledInstance(320, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(410, 30, 350, 350);
        add(image);

        ID = new JLabel("Destination ID:");
        ID.setBounds(20, 30, 80, 20);
        ID.setFont(new Font("Calibri", Font.BOLD, 12));
        ID.setForeground(new Color(0, 50, 204));
        ID.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(ID);

        IDTF = new JTextField();
        IDTF.setBounds(120, 30, 200, 20);
        IDTF.setBorder(BorderFactory.createEmptyBorder());
        p1.add(IDTF);

        Name = new JLabel("Name:");
        Name.setBounds(20, 70, 80, 20);
        Name.setFont(new Font("Calibri", Font.BOLD, 16));
        Name.setForeground(new Color(0, 50, 204));
        Name.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(Name);

        NTF = new JTextField();
        NTF.setBounds(120, 70, 200, 20);
        NTF.setBorder(BorderFactory.createEmptyBorder());
        p1.add(NTF);

        Location = new JLabel("Location:");
        Location.setBounds(20, 110, 80, 20);
        Location.setFont(new Font("Calibri", Font.BOLD, 16));
        Location.setForeground(new Color(0, 50, 204));
        Location.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(Location);

        LTF = new JTextField();
        LTF.setBounds(120, 110, 200, 20);
        LTF.setBorder(BorderFactory.createEmptyBorder());
        p1.add(LTF);

        Attractions = new JLabel("Attractions:");
        Attractions.setBounds(20, 150, 80, 20);
        Attractions.setFont(new Font("Calibri", Font.BOLD, 14));
        Attractions.setForeground(new Color(0, 50, 204));
        Attractions.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(Attractions);

        ATF = new JTextArea();
        ATF.setBounds(120, 150, 200, 100);
        ATF.setBorder(BorderFactory.createEmptyBorder());
        ATF.setLineWrap(true);
        p1.add(ATF);

        Avg_temp = new JLabel("Average temperature:");
        Avg_temp.setBounds(20, 270, 130, 20);
        Avg_temp.setFont(new Font("Calibri", Font.BOLD, 14));
        Avg_temp.setForeground(new Color(0, 50, 204));
        Avg_temp.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(Avg_temp);

        ATTF = new JTextField();
        ATTF.setBounds(170, 270, 150, 20);
        ATTF.setBorder(BorderFactory.createEmptyBorder());
        p1.add(ATTF);

        AddButton = new JButton("Add");
        AddButton.setBounds(40, 310, 120, 30);
        AddButton.setBorder(BorderFactory.createEmptyBorder());
        AddButton.setBackground(new Color(133, 193, 233));
        AddButton.setForeground(Color.WHITE);
        p1.add(AddButton);

        backButton = new JButton("Back");
        backButton.setBounds(180, 310, 120, 30);
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
        new AddDestination();
    }

    public class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand() == "Add") {
                if (IDTF.getText().isEmpty() || NTF.getText().isEmpty() || LTF.getText().isEmpty()
                        || ATF.getText().isEmpty() || ATTF.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all the fields!", "Empty Fields",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    String ID = IDTF.getText();
                    String name = NTF.getText();
                    String Location = LTF.getText();
                    String Attractions = ATF.getText();
                    String Avg_temp = ATTF.getText();

                    try {
                        MongoConn c = new MongoConn();

                        MongoCollection mongoCollection = c.mongoDatabase.getCollection("Destinations");

                        Document document = new Document("destination_id", ID);
                        document.append("name", name);
                        document.append("location", Location);
                        document.append("attractions", Attractions);
                        document.append("average_temp", Avg_temp);

                        Document idfilter = new Document("destination_id", ID);
                        Document personDocument = (Document) mongoCollection.find(idfilter).first();

                        if (personDocument != null) {
                            JOptionPane.showMessageDialog(null,
                                    "This ID already exits. Please choose another one. Thanks");
                            IDTF.setText("");
                        } else {
                            mongoCollection.insertOne(document);

                            JOptionPane.showMessageDialog(null, "Destination added Successfully");
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
