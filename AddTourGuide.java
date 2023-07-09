package com.travel;

import java.awt.event.*;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;
import java.awt.*;

import javax.swing.*;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

public class AddTourGuide extends JFrame {

    JLabel ID, Name, Language, Experience, Fee, Email;
    JTextField IDTF, NTF, LTF, FTF, ATTF;
    JTextArea ATF;
    JButton AddButton, backButton;

    AddTourGuide() {
        setTitle("Add Tour Guide");
        setSize(800, 450);
        setLocation(250, 150);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JPanel p1 = new JPanel();
        p1.setBounds(20, 20, 370, 370);
        // p1.setBackground(new Color(133, 193, 233));
        p1.setLayout(null);
        add(p1);

        ImageIcon i1 = new ImageIcon(getClass().getResource("Images/Guide.jpg"));
        Image i2 = i1.getImage().getScaledInstance(320, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(410, 30, 350, 350);
        add(image);

        ID = new JLabel("Tour Guide ID:");
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

        Language = new JLabel("Language:");
        Language.setBounds(20, 110, 80, 20);
        Language.setFont(new Font("Calibri", Font.BOLD, 16));
        Language.setForeground(new Color(0, 50, 204));
        Language.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(Language);

        LTF = new JTextField();
        LTF.setBounds(120, 110, 200, 20);
        LTF.setBorder(BorderFactory.createEmptyBorder());
        p1.add(LTF);

        Experience = new JLabel("Experience:");
        Experience.setBounds(20, 150, 80, 20);
        Experience.setFont(new Font("Calibri", Font.BOLD, 14));
        Experience.setForeground(new Color(0, 50, 204));
        Experience.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(Experience);

        ATF = new JTextArea();
        ATF.setBounds(120, 150, 200, 60);
        ATF.setBorder(BorderFactory.createEmptyBorder());
        ATF.setLineWrap(true);
        p1.add(ATF);

        Fee = new JLabel("Fee:");
        Fee.setBounds(20, 230, 80, 20);
        Fee.setFont(new Font("Calibri", Font.BOLD, 14));
        Fee.setForeground(new Color(0, 50, 204));
        Fee.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(Fee);

        FTF = new JTextField();
        FTF.setBounds(120, 230, 200, 20);
        FTF.setBorder(BorderFactory.createEmptyBorder());
        p1.add(FTF);

        Email = new JLabel("Email:");
        Email.setBounds(20, 270, 80, 20);
        Email.setFont(new Font("Calibri", Font.BOLD, 14));
        Email.setForeground(new Color(0, 50, 204));
        Email.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(Email);

        ATTF = new JTextField();
        ATTF.setBounds(120, 270, 200, 20);
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
        new AddTourGuide();
    }

    public class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand() == "Add") {
                if (IDTF.getText().isEmpty() || NTF.getText().isEmpty() || LTF.getText().isEmpty()
                        || ATF.getText().isEmpty() || ATTF.getText().isEmpty() || FTF.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all the fields!", "Empty Fields",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    String ID = IDTF.getText();
                    String name = NTF.getText();
                    String Language = LTF.getText();
                    String Experience = ATF.getText();
                    String Fee = FTF.getText();
                    String Email = ATTF.getText();

                    try {
                        MongoConn c = new MongoConn();

                        MongoCollection mongoCollection = c.mongoDatabase.getCollection("TourGuide");

                        Document document = new Document("tourGuide_id", ID);
                        document.append("name", name);
                        document.append("Language", Language);
                        document.append("Experience", Experience);
                        document.append("fee", Fee);
                        document.append("email", Email);

                        Document idfilter = new Document("tourGuide_id", ID);
                        Document personDocument = (Document) mongoCollection.find(idfilter).first();

                        if (personDocument != null) {
                            JOptionPane.showMessageDialog(null,
                                    "This ID already exits. Please choose another one. Thanks");
                            IDTF.setText("");
                        } else {
                            mongoCollection.insertOne(document);

                            JOptionPane.showMessageDialog(null, "TourGuide added Successfully");
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
