package com.travel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

public class BookingMain extends JFrame {
    public static void main(String[] args) {
        new BookingMain();
    }

    JLabel City, CityN, Hotel, HotelN, Flight, FlightN, Guide, GuideN, BID, Duration, Price, PTF;
    JTextField BIDTF, DTF;
    JButton TPB, Submit, Back;

    BookingMain() {
        setTitle("Booking");
        setSize(800, 500);
        setLocation(250, 150);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JPanel p1 = new JPanel();
        p1.setBounds(20, 20, 370, 420);
        p1.setLayout(null);
        add(p1);

        ImageIcon i1 = new ImageIcon(getClass().getResource("Images/Booking.jpg"));
        Image i2 = i1.getImage().getScaledInstance(400, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(390, 60, 400, 350);
        add(image);

        City = new JLabel("City Name:");
        City.setBounds(20, 30, 100, 20);
        City.setFont(new Font("Calibri", Font.BOLD, 16));
        City.setForeground(Color.black);
        City.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(City);

        String ID = Booking.ITF.getText();
        try {
            MongoConn c = new MongoConn();
            MongoCollection mongoCollection = c.mongoDatabase.getCollection("Destinations");

            Document queryFilter = new Document("destination_id", ID);
            Document personDocument = (Document) mongoCollection.find(queryFilter).first();

            if (personDocument != null) {

                String name = (personDocument.getString("name"));

                CityN = new JLabel(name);
                CityN.setBounds(140, 30, 200, 20);
                CityN.setFont(new Font("Calibri", Font.BOLD, 16));
                CityN.setForeground(new Color(0, 50, 204));
                CityN.setHorizontalAlignment(SwingConstants.CENTER);
                p1.add(CityN);

            } else {
                JOptionPane.showMessageDialog(null, "User not found.");
            }

        } catch (Exception a) {
            a.printStackTrace();
        }

        Hotel = new JLabel("Hotel Name:");
        Hotel.setBounds(20, 70, 100, 20);
        Hotel.setFont(new Font("Calibri", Font.BOLD, 16));
        Hotel.setForeground(Color.black);
        Hotel.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(Hotel);

        String ID1 = BookingA.ITF.getText();
        try {
            MongoConn c = new MongoConn();
            MongoCollection mongoCollection = c.mongoDatabase.getCollection("Accomodation");

            Document queryFilter = new Document("accom_id", ID1);
            Document personDocument = (Document) mongoCollection.find(queryFilter).first();

            if (personDocument != null) {

                String name = (personDocument.getString("name"));

                HotelN = new JLabel(name);
                HotelN.setBounds(140, 70, 200, 20);
                HotelN.setFont(new Font("Calibri", Font.BOLD, 16));
                HotelN.setForeground(new Color(0, 50, 204));
                HotelN.setHorizontalAlignment(SwingConstants.CENTER);
                p1.add(HotelN);

            } else {
                JOptionPane.showMessageDialog(null, "User not found.");
            }

        } catch (Exception a) {
            a.printStackTrace();
        }

        Flight = new JLabel("Flight Name:");
        Flight.setBounds(20, 110, 100, 20);
        Flight.setFont(new Font("Calibri", Font.BOLD, 16));
        Flight.setForeground(Color.black);
        Flight.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(Flight);

        String ID2 = BookingT.ITF.getText();
        try {
            MongoConn c = new MongoConn();
            MongoCollection mongoCollection = c.mongoDatabase.getCollection("Transportation");

            Document queryFilter = new Document("trans_id", ID2);
            Document personDocument = (Document) mongoCollection.find(queryFilter).first();

            if (personDocument != null) {

                String name = (personDocument.getString("Flight"));

                FlightN = new JLabel(name);
                FlightN.setBounds(140, 110, 200, 20);
                FlightN.setFont(new Font("Calibri", Font.BOLD, 16));
                FlightN.setForeground(new Color(0, 50, 204));
                FlightN.setHorizontalAlignment(SwingConstants.CENTER);
                p1.add(FlightN);

            } else {
                JOptionPane.showMessageDialog(null, "User not found.");
            }

        } catch (Exception a) {
            a.printStackTrace();
        }

        Guide = new JLabel("Guide Name:");
        Guide.setBounds(20, 150, 100, 20);
        Guide.setFont(new Font("Calibri", Font.BOLD, 16));
        Guide.setForeground(Color.black);
        Guide.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(Guide);

        String ID3 = BookingTG.ITF.getText();
        try {
            MongoConn c = new MongoConn();
            MongoCollection mongoCollection = c.mongoDatabase.getCollection("TourGuide");

            Document queryFilter = new Document("tourGuide_id", ID3);
            Document personDocument = (Document) mongoCollection.find(queryFilter).first();

            if (personDocument != null) {

                String name = (personDocument.getString("name"));

                GuideN = new JLabel(name);
                GuideN.setBounds(140, 150, 200, 20);
                GuideN.setFont(new Font("Calibri", Font.BOLD, 16));
                GuideN.setForeground(new Color(0, 50, 204));
                GuideN.setHorizontalAlignment(SwingConstants.CENTER);
                p1.add(GuideN);

            } else {
                JOptionPane.showMessageDialog(null, "User not found.");
            }

        } catch (Exception a) {
            a.printStackTrace();
        }

        BID = new JLabel("Booking ID:");
        BID.setBounds(20, 190, 100, 20);
        BID.setFont(new Font("Calibri", Font.BOLD, 16));
        BID.setForeground(Color.black);
        BID.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(BID);

        BIDTF = new JTextField();
        BIDTF.setBounds(140, 190, 200, 20);
        BIDTF.setBorder(BorderFactory.createEmptyBorder());
        p1.add(BIDTF);

        Duration = new JLabel("Duration (days):");
        Duration.setBounds(20, 230, 100, 20);
        Duration.setFont(new Font("Calibri", Font.BOLD, 14));
        Duration.setForeground(Color.black);
        Duration.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(Duration);

        DTF = new JTextField();
        DTF.setBounds(140, 230, 200, 20);
        DTF.setBorder(BorderFactory.createEmptyBorder());
        p1.add(DTF);

        Price = new JLabel("Price:");
        Price.setBounds(20, 270, 100, 20);
        Price.setFont(new Font("Calibri", Font.BOLD, 16));
        Price.setForeground(Color.black);
        Price.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(Price);

        PTF = new JLabel();
        PTF.setBounds(140, 270, 100, 20);
        PTF.setFont(new Font("Calibri", Font.BOLD, 16));
        PTF.setForeground(new Color(0, 50, 204));
        PTF.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(PTF);

        TPB = new JButton("Calculate Price");
        TPB.setBounds(110, 310, 140, 30);
        TPB.setBorder(BorderFactory.createEmptyBorder());
        TPB.setBackground(new Color(133, 193, 233));
        TPB.setForeground(Color.WHITE);
        p1.add(TPB);

        Submit = new JButton("Book");
        Submit.setBounds(20, 360, 120, 30);
        Submit.setBorder(BorderFactory.createEmptyBorder());
        Submit.setBackground(new Color(133, 193, 233));
        Submit.setForeground(Color.WHITE);
        p1.add(Submit);

        Back = new JButton("Back");
        Back.setBounds(220, 360, 120, 30);
        Back.setBorder(BorderFactory.createEmptyBorder());
        Back.setBackground(new Color(133, 193, 233));
        Back.setForeground(Color.WHITE);
        p1.add(Back);

        // ActionListener
        MyActionListener mal = new MyActionListener();
        Submit.addActionListener(mal);
        Back.addActionListener(mal);
        TPB.addActionListener(mal);

        setVisible(true);
    }

    public class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand() == "Book") {
                if (BIDTF.getText().isEmpty() || DTF.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all the fields!", "Empty Fields",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    String User = Login.namField.getText();
                    String DID = Booking.ITF.getText();
                    String AID = BookingA.ITF.getText();
                    String TID = BookingT.ITF.getText();
                    String TGID = BookingTG.ITF.getText();
                    String BID = BIDTF.getText();
                    String duration = DTF.getText();
                    String price = PTF.getText();

                    Document document = new Document("User", User);
                    document.append("dest_id", DID);
                    document.append("accom_id", AID);
                    document.append("trans_id", TID);
                    document.append("tourGuide_id", TGID);
                    document.append("booking_id", BID);
                    document.append("duration", duration);
                    document.append("total_price", price);

                    try {
                        MongoConn c = new MongoConn();

                        MongoCollection mongoCollection = c.mongoDatabase.getCollection("Booking");

                        Document idfilter = new Document("booking_id", BID);
                        Document personDocument = (Document) mongoCollection.find(idfilter).first();

                        if (personDocument != null) {
                            JOptionPane.showMessageDialog(null,
                                    "This ID already exits. Please choose another one. Thanks");
                            BIDTF.setText("");
                        } else {
                            mongoCollection.insertOne(document);

                            JOptionPane.showMessageDialog(null, "Successfully Booked");
                            dispose();
                        }
                    } catch (Exception a) {
                        a.printStackTrace();
                    }
                }
            } else if (e.getActionCommand() == ("Calculate Price")) {
                int accom_price = 0;
                int flight_rent = 0;
                int car_rent = 0;
                int Guide_fee = 0;
                String ID1 = BookingA.ITF.getText();
                String ID2 = BookingT.ITF.getText();
                String ID3 = BookingTG.ITF.getText();
                try {
                    MongoConn c = new MongoConn();
                    MongoCollection accomCollection = c.mongoDatabase.getCollection("Accomodation");
                    MongoCollection transCollection = c.mongoDatabase.getCollection("Transportation");
                    MongoCollection tourguideCollection = c.mongoDatabase.getCollection("TourGuide");

                    Document queryFilter = new Document("accom_id", ID1);
                    Document personDocument = (Document) accomCollection.find(queryFilter).first();

                    Document queryFilter1 = new Document("trans_id", ID2);
                    Document personDocument1 = (Document) transCollection.find(queryFilter1).first();

                    Document queryFilter2 = new Document("tourGuide_id", ID3);
                    Document personDocument2 = (Document) tourguideCollection.find(queryFilter2).first();

                    if (personDocument != null) {

                        String price1 = (personDocument.getString("price_per_night"));
                        accom_price = Integer.parseInt(price1);

                    }
                    if (personDocument1 != null) {

                        String price2 = (personDocument1.getString("FlightRent"));
                        flight_rent = Integer.parseInt(price2);

                        String price3 = (personDocument1.getString("CarRent"));
                        car_rent = Integer.parseInt(price3);

                    }
                    if (personDocument2 != null) {

                        String price4 = (personDocument2.getString("fee"));
                        Guide_fee = Integer.parseInt(price4);

                    }
                } catch (Exception a) {
                    a.printStackTrace();
                }
                int total = accom_price + car_rent;
                String duration = DTF.getText();
                int dur = Integer.parseInt(duration);

                int Price = (total * dur) + (2 * flight_rent) + Guide_fee;
                String p = String.valueOf(Price);

                PTF.setText(p);

            } else if (e.getActionCommand() == ("Back")) {
                dispose();
                new BookingTG();
            }
        }
    }
}
