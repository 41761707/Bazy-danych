package com.zalewskiwojtczak;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame {
    private static DataConnect connector;
    private static JFrame frame;
    private static String type = "Student";
    private static JTextField loginField;
    private static JPasswordField passwordField;

    public static void main(String[] args) {
        frame = new JFrame();
        frame.setTitle("Dziennik internetowy");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel textPane = new JPanel();
        textPane.add(new JLabel("Login:"));
        loginField = new JTextField(30);
        textPane.add(loginField);
        textPane.add(new JLabel("Password:"));
        passwordField = new JPasswordField(30);
        textPane.add(passwordField);
        textPane.setLayout(new BoxLayout(textPane, BoxLayout.PAGE_AXIS));

        JPanel buttonPane = new JPanel();
        JButton buttonS = new JButton("Uczeń");
        JButton buttonT = new JButton("Nauczyciel");
        JButton buttonP = new JButton("Opiekun");
        JButton buttonA = new JButton("Admin");
        buttonPane.add(buttonS);
        buttonPane.add(buttonT);
        buttonPane.add(buttonP);
        buttonPane.add(buttonA);

        JPanel okPane = new JPanel();
        JButton button = new JButton("Ok");
        okPane.add(button);

        frame.add(textPane, BorderLayout.NORTH);
        frame.add(buttonPane, BorderLayout.CENTER);
        frame.add(okPane, BorderLayout.SOUTH);
        frame.setVisible(true);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.setVisible(false);
                frame.setSize(500, 500);
                String login = loginField.getText();
                String passwd = new String(passwordField.getPassword());
                try {
                    if (type.equals("Student")) {
                        connector = new StudentDataConnect(login, passwd);
                        frame.setContentPane(new StudentPanel(connector));
                        frame.setVisible(true);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
        /*try {
            if (type.equals("Student")) {
                connector = new StudentDataConnect();
                frame.setContentPane(new StudentPanel(connector));
                frame.setVisible(true);
            }
            else if (type.equals("Nauczyciel")){
                connector = new TeacherDataConnect();
                frame.setContentPane(new TeacherPanel(connector));
                frame.setVisible(true);
            }
            else if (type.equals("Opiekun")){
                connector = new ParentDataConnect();
                frame.setContentPane(new ParentPanel(connector));
                frame.setVisible(true);
            }
            else if (type.equals("Admin")){
                connector = new AdminDataConnect();
                frame.setContentPane(new AdminPanel(connector));
                frame.setVisible(true);
            }
            else {
                return;
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }*/

