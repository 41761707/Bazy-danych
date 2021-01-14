package com.zalewskiwojtczak;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPanel extends JPanel {
    private final AdminDataConnect connector1;
    private final JTable table;
    private final JScrollPane scrollPane;
    private String type;

    public AdminPanel(DataConnect connector){
        this.connector1 = (AdminDataConnect) connector;

        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        add(panel, BorderLayout.NORTH);

        JButton button1 = new JButton("Uczniowie");
        JButton button2 = new JButton("Nauczyciele");
        JButton button3 = new JButton("Opiekunowie");

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);

        scrollPane = new JScrollPane();
        add(scrollPane, BorderLayout.CENTER);
        table = new JTable();
        scrollPane.add(table);
        scrollPane.setViewportView(table);
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Person> people = null;
                    people = connector1.showAll("Uczeń");
                    StudentTableModel model = new StudentTableModel(people);
                    table.setModel(model);

                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(AdminPanel.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Person> people = null;
                    people = connector1.showAll("Nauczyciel");
                    TeacherTableModel model = new TeacherTableModel(people);
                    table.setModel(model);

                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(AdminPanel.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Person> people = null;
                    people = connector1.showAll("Opiekun");
                    ParentTableModel model = new ParentTableModel(people);
                    table.setModel(model);

                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(AdminPanel.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
