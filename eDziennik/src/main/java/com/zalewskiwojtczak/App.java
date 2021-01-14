package com.zalewskiwojtczak;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame {
    private DataConnect connector;
    private JFrame frame;
    private String type = "";
    private JButton button;
    private JButton buttonS;
    private JButton buttonT;
    private JButton buttonP;
    private JButton buttonA;
    private JTextField okField;
    private JTextField loginField;
    private JPasswordField passwordField;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    App app = new App();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public App(){
        frame = prepareFrame();
        frame.setVisible(true);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.setVisible(false);
                String login = loginField.getText();
                String passwd = new String(passwordField.getPassword());
                try {
                    if (type.equals("Uczeń")) {
                        frame.setSize(900, 900);
                        connector = new StudentDataConnect(login, passwd);
                        frame.setContentPane(new StudentPanel(connector));
                        frame.setVisible(true);
                    }
                    else if (type.equals("Nauczyciel")){
                        frame.setSize(900, 900);
                        connector = new TeacherDataConnect(login, passwd);
                        frame.setContentPane(new TeacherPanel(connector));
                        frame.setVisible(true);
                    }
                    else if (type.equals("Opiekun")){
                        frame.setSize(900, 900);
                        connector = new ParentDataConnect(login, passwd);
                        frame.setContentPane(new ParentPanel(connector));
                        frame.setVisible(true);
                    }
                    else if (type.equals("Admin")){
                        frame.setSize(900, 900);
                        connector = new AdminDataConnect(login, passwd);
                        frame.setContentPane(new AdminPanel(connector));
                        frame.setVisible(true);
                    }
                    else {
                        return;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        buttonS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                type = "Uczeń";
                okField.setText(type);
            }
        });
        buttonT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                type = "Nauczyciel";
                okField.setText(type);
            }
        });
        buttonP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                type = "Opiekun";
                okField.setText(type);
            }
        });
        buttonA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                type = "Admin";
                okField.setText(type);
            }
        });
    }

    private JFrame prepareFrame(){
        JFrame result = new JFrame();
        result.setTitle("Dziennik internetowy");
        result.setSize(400, 200);
        result.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel textPane = new JPanel();
        textPane.add(new JLabel("Login:"));
        loginField = new JTextField(30);
        textPane.add(loginField);
        textPane.add(new JLabel("Password:"));
        passwordField = new JPasswordField(30);
        textPane.add(passwordField);
        textPane.setLayout(new BoxLayout(textPane, BoxLayout.PAGE_AXIS));

        JPanel buttonPane = new JPanel();
        buttonS = new JButton("Uczeń");
        buttonT = new JButton("Nauczyciel");
        buttonP = new JButton("Opiekun");
        buttonA = new JButton("Admin");
        buttonPane.add(buttonS);
        buttonPane.add(buttonT);
        buttonPane.add(buttonP);
        buttonPane.add(buttonA);

        JPanel okPane = new JPanel();
        okField = new JTextField(6);
        okField.setEditable(false);
        okField.setText(type);
        button = new JButton("Ok");
        okPane.add(button);
        okPane.add(okField);

        result.add(textPane, BorderLayout.NORTH);
        result.add(buttonPane, BorderLayout.CENTER);
        result.add(okPane, BorderLayout.SOUTH);
        return result;
    }
}



