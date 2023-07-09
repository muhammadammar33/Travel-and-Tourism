package com.travel;

import java.awt.event.*;
import java.util.Arrays;
import java.awt.*;

import javax.swing.*;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

public class SignUp extends JFrame {

    JLabel ID, UName, Email, UPass, Spass, CPass, Scpass, Date, DF, Contact, CF, SecurityQ, Answer;
    JTextField IDTF, NTF, ETF, DTF, CTF, ATF;
    JPasswordField PF, CPF;
    Choice Security;
    JButton SignUpButton, backButton, see1, see2;

    SignUp() {
        setTitle("SignUp");
        setSize(800, 550);
        setLocation(250, 150);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JPanel p1 = new JPanel();
        p1.setBounds(20, 20, 370, 470);
        // p1.setBackground(new Color(133, 193, 233));
        p1.setLayout(null);
        add(p1);

        // JPanel p2 = new JPanel();
        // p2.setLayout(null);
        // p2.setBounds(400, 0, 400, 400);
        // p2.setBackground(Color.WHITE);
        // add(p2);

        ImageIcon i1 = new ImageIcon(getClass().getResource("Images/Sign.jpg"));
        Image i2 = i1.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(420, 100, 350, 350);
        add(image);

        ID = new JLabel("User ID:");
        ID.setBounds(20, 30, 80, 20);
        ID.setFont(new Font("Calibri", Font.BOLD, 16));
        ID.setForeground(new Color(0, 50, 204));
        ID.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(ID);

        IDTF = new JTextField();
        IDTF.setBounds(120, 30, 200, 20);
        IDTF.setBorder(BorderFactory.createEmptyBorder());
        p1.add(IDTF);

        UName = new JLabel("User Name:");
        UName.setBounds(20, 70, 80, 20);
        UName.setFont(new Font("Calibri", Font.BOLD, 16));
        UName.setForeground(new Color(0, 50, 204));
        UName.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(UName);

        NTF = new JTextField();
        NTF.setBounds(120, 70, 200, 20);
        NTF.setBorder(BorderFactory.createEmptyBorder());
        p1.add(NTF);

        Email = new JLabel("Email:");
        Email.setBounds(20, 110, 80, 20);
        Email.setFont(new Font("Calibri", Font.BOLD, 16));
        Email.setForeground(new Color(0, 50, 204));
        Email.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(Email);

        ETF = new JTextField();
        ETF.setBounds(120, 110, 200, 20);
        ETF.setBorder(BorderFactory.createEmptyBorder());
        p1.add(ETF);

        UPass = new JLabel("Password:");
        UPass.setBounds(20, 150, 80, 20);
        UPass.setFont(new Font("Calibri", Font.BOLD, 16));
        UPass.setForeground(new Color(0, 50, 204));
        UPass.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(UPass);

        PF = new JPasswordField();
        PF.setBounds(120, 150, 200, 20);
        PF.setBorder(BorderFactory.createEmptyBorder());
        p1.add(PF);

        see1 = new JButton("Ǿ");
        see1.setBounds(320, 150, 40, 20);
        see1.setBorder(BorderFactory.createEmptyBorder());
        see1.setBackground(new Color(133, 193, 233));
        see1.setForeground(Color.WHITE);
        p1.add(see1);

        Spass = new JLabel();
        Spass.setBounds(120, 175, 200, 15);
        Spass.setForeground(new Color(255, 51, 51));
        Spass.setFont(new Font("Calibri", Font.BOLD, 11));
        p1.add(Spass);

        CPass = new JLabel("Confirm Password:");
        CPass.setBounds(20, 200, 100, 20);
        CPass.setFont(new Font("Calibri", Font.BOLD, 12));
        CPass.setForeground(new Color(0, 50, 204));
        CPass.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(CPass);

        CPF = new JPasswordField();
        CPF.setBounds(120, 200, 200, 20);
        CPF.setBorder(BorderFactory.createEmptyBorder());
        p1.add(CPF);

        see2 = new JButton("Ø");
        see2.setBounds(320, 200, 40, 20);
        see2.setBorder(BorderFactory.createEmptyBorder());
        see2.setBackground(new Color(133, 193, 233));
        see2.setForeground(Color.WHITE);
        p1.add(see2);

        Scpass = new JLabel();
        Scpass.setBounds(120, 225, 200, 15);
        Scpass.setForeground(new Color(255, 51, 51));
        Scpass.setFont(new Font("Calibri", Font.BOLD, 11));
        p1.add(Scpass);

        Date = new JLabel("Date Of Birth:");
        Date.setBounds(20, 250, 80, 20);
        Date.setFont(new Font("Calibri", Font.BOLD, 12));
        Date.setForeground(new Color(0, 50, 204));
        Date.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(Date);

        DF = new JLabel("YYYY-MM-DD");
        DF.setBounds(120, 275, 200, 15);
        DF.setForeground(new Color(255, 51, 51));
        DF.setFont(new Font("Calibri", Font.BOLD, 9));
        p1.add(DF);

        DTF = new JTextField();
        DTF.setBounds(120, 250, 200, 20);
        DTF.setBorder(BorderFactory.createEmptyBorder());
        p1.add(DTF);

        Contact = new JLabel("Contact:");
        Contact.setBounds(20, 290, 80, 20);
        Contact.setFont(new Font("Calibri", Font.BOLD, 16));
        Contact.setForeground(new Color(0, 50, 204));
        Contact.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(Contact);

        CTF = new JTextField();
        CTF.setBounds(120, 290, 200, 20);
        CTF.setBorder(BorderFactory.createEmptyBorder());
        p1.add(CTF);

        CF = new JLabel("03*********");
        CF.setBounds(120, 315, 200, 15);
        CF.setForeground(new Color(255, 51, 51));
        CF.setFont(new Font("Calibri", Font.BOLD, 9));
        p1.add(CF);

        SecurityQ = new JLabel("Secuity Question:");
        SecurityQ.setBounds(20, 330, 120, 20);
        SecurityQ.setFont(new Font("Calibri", Font.BOLD, 16));
        SecurityQ.setForeground(new Color(0, 50, 204));
        SecurityQ.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(SecurityQ);

        Security = new Choice();
        Security.add("Choose one");
        Security.add("Your Childhood name.");
        Security.add("Your Pet name.");
        Security.add("Your High class grades.");
        Security.setBounds(160, 330, 160, 20);
        p1.add(Security);

        Answer = new JLabel("Answer:");
        Answer.setBounds(20, 370, 80, 20);
        Answer.setFont(new Font("Calibri", Font.BOLD, 16));
        Answer.setForeground(new Color(0, 50, 204));
        Answer.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(Answer);

        ATF = new JTextField();
        ATF.setBounds(120, 370, 200, 20);
        ATF.setBorder(BorderFactory.createEmptyBorder());
        p1.add(ATF);

        SignUpButton = new JButton("SignUp");
        SignUpButton.setBounds(40, 410, 120, 30);
        SignUpButton.setBorder(BorderFactory.createEmptyBorder());
        SignUpButton.setBackground(new Color(133, 193, 233));
        SignUpButton.setForeground(Color.WHITE);
        p1.add(SignUpButton);

        backButton = new JButton("Back");
        backButton.setBounds(180, 410, 120, 30);
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.setBackground(new Color(133, 193, 233));
        backButton.setForeground(Color.WHITE);
        p1.add(backButton);

        // ActionListener
        MyActionListener mal = new MyActionListener();
        SignUpButton.addActionListener(mal);
        backButton.addActionListener(mal);
        see1.addActionListener(mal);
        see2.addActionListener(mal);

        setVisible(true);
    }

    public static void main(String[] args) {
        new SignUp();
    }

    public class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand() == "SignUp") {
                if (IDTF.getText().isEmpty() || NTF.getText().isEmpty() || ETF.getText().isEmpty()
                        || PF.getText().isEmpty() || CPF.getText().isEmpty()
                        || DTF.getText().isEmpty() || CTF.getText().isEmpty() || ATF.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all the fields!", "Empty Fields",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!(ETF.getText().contains("@")) && !(ETF.getText().contains(".com"))) {
                    JOptionPane.showMessageDialog(null, "Sign Up unsuccessful!! Please enter valid Email!!");
                    ETF.setText("");
                    return;
                }
                char[] input1 = PF.getPassword();
                char[] input2 = CPF.getPassword();
                if (!(PF.getText().length() >= 8) && !(Arrays.equals(input1, input2))) {
                    JOptionPane.showMessageDialog(null,
                            "Sign Up unsuccessful!! Please enter Same Password in both fields with atleast 8 characters!!");
                    PF.setText("");
                    CPF.setText("");
                    return;
                }
                if (!(CTF.getText().matches("[0]{1}[3]{1}[0-9]{9}"))) {
                    JOptionPane.showMessageDialog(null, "Sign Up unsuccessful!! Please enter valid Phone Number!!");
                    CTF.setText("");
                    return;
                } else {
                    String ID = IDTF.getText();
                    String name = NTF.getText();
                    String email = ETF.getText();
                    String password = PF.getText();
                    String date = DTF.getText();
                    String contact = CTF.getText();
                    String question = Security.getSelectedItem();
                    String answer = ATF.getText();

                    Document document = new Document("user_id", ID);
                    document.append("name", name);
                    document.append("email", email);
                    document.append("password", password);
                    document.append("date_of_birth", date);
                    document.append("contact", contact);
                    document.append("question", question);
                    document.append("answer", answer);

                    try {
                        MongoConn c = new MongoConn();

                        MongoCollection mongoCollection = c.mongoDatabase.getCollection("User");

                        Document idfilter = new Document("user_id", ID);
                        Document personDocument = (Document) mongoCollection.find(idfilter).first();

                        if (personDocument != null) {
                            JOptionPane.showMessageDialog(null,
                                    "This ID already exits. Please choose another one. Thanks");
                            IDTF.setText("");
                        } else {
                            mongoCollection.insertOne(document);

                            JOptionPane.showMessageDialog(null, "Account Created Successfully");
                            dispose();
                            new Login();
                        }
                    } catch (Exception a) {
                        a.printStackTrace();
                    }
                }
            } else if (e.getActionCommand() == ("Ǿ")) {
                Spass.setText(PF.getText());
            } else if (e.getActionCommand() == ("Ø")) {
                Scpass.setText(CPF.getText());
            } else if (e.getActionCommand() == ("Back")) {
                dispose();
                new Login();
            }
        }
    }
}
