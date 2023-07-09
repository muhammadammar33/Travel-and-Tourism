package com.travel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class HBL extends JFrame {
    public static void main(String[] args) {
        new HBL();
    }

    HBL() {
        setTitle("HBL");
        setSize(800, 550);
        setLocation(250, 150);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JEditorPane pane = new JEditorPane();
        pane.setEditable(false);

        try {
            pane.setPage("https://www.hbl.com/");
        } catch (Exception e) {
            pane.setContentType("text/html");
            pane.setText("<html>Could not load,Error 404</html>");
        }

        JScrollPane sp = new JScrollPane(pane);
        getContentPane().add(sp);

        JButton Back = new JButton("Back");
        Back.setBounds(600, 40, 120, 30);
        Back.setBorder(BorderFactory.createEmptyBorder());
        Back.setBackground(new Color(133, 193, 233));
        Back.setForeground(Color.WHITE);
        pane.add(Back);

        // ActionListener
        MyActionListener mal = new MyActionListener();
        Back.addActionListener(mal);

        setVisible(true);
    }

    public class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand() == "Back") {
                dispose();
                new Payment();
            }
        }
    }
}
