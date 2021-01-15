package com.zalewskiwojtczak;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPanel extends JPanel {
    private final AdminDataConnect connector;
    private final JTable table;
    private final JScrollPane scrollPane;
    JTextField lastnameField;
    JTextField firstnameField;

    public AdminPanel(DataConnect dataConnector){
        this.connector = (AdminDataConnect) dataConnector;

        setLayout(new BorderLayout());

        JPanel panelUp = new JPanel();
        JButton[] buttons = {new JButton("Uczniowie"), new JButton("Nauczyciele"), new JButton("Opiekunowie"), new JButton("Administratorzy")};
        panelUp.add(new JLabel("Imię"));
        firstnameField = new JTextField(10);
        panelUp.add(firstnameField);
        panelUp.add(new JLabel("Nazwisko"));
        lastnameField = new JTextField(10);
        panelUp.add(lastnameField);
        panelUp.add(buttons[0]);
        panelUp.add(buttons[1]);
        panelUp.add(buttons[2]);
        panelUp.add(buttons[3]);
        add(panelUp, BorderLayout.NORTH);

        JPanel panelDown = new JPanel();
        JButton addUser = new JButton("Dodaj użytkownika");
        JButton backup = new JButton("Backup");
        panelDown.add(backup);
        panelDown.add(addUser);
        add(panelDown, BorderLayout.SOUTH);

        scrollPane = new JScrollPane();
        add(scrollPane, BorderLayout.CENTER);
        table = new JTable();
        scrollPane.add(table);
        scrollPane.setViewportView(table);

        buttons[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performAction("Uczeń");
            }
        });
        buttons[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performAction("Nauczyciel");
            }
        });
        buttons[2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performAction("Opiekun");
            }
        });
        buttons[3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performAction("Admin");
            }
        });

        addUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    JPanel addPanel = new JPanel();
                    addPanel.setLayout(new GridLayout(0, 1));
                    JTextField[] fields = { new JTextField(10), new JTextField(10), new JTextField(10), new JTextField(10),
                            new JTextField(10), new JTextField(10), new JTextField(10), new JTextField(10), new JTextField(10)};
                    JLabel[] labels = { new JLabel("rodzaj użytkownika (U/N/O/A)"), new JLabel("id"), new JLabel("imie"), new JLabel("nazwisko"),
                            new JLabel("adres"), new JLabel("klasa/gabinet/-"), new JLabel("pesel"), new JLabel("numer telefonu"), new JLabel("email")};
                    for (int i = 0; i < 9; i++){
                        addPanel.add(labels[i]);
                        addPanel.add(fields[i]);
                    }
                    JOptionPane.showConfirmDialog(null, addPanel, "Dodaj użytkownika", JOptionPane.OK_CANCEL_OPTION);

                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(AdminPanel.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1){
                    int option = JOptionPane.showConfirmDialog(AdminPanel.this, "Czy chcesz edytować/usunąć użytkownika?",
                            table.getValueAt(table.getSelectedRow(), 1) + " " + table.getValueAt(table.getSelectedRow(), 2), JOptionPane.OK_CANCEL_OPTION);
                    if (option == JOptionPane.OK_OPTION){
                        JPanel addPanel = new JPanel();
                        addPanel.setLayout(new GridLayout(0, 1));
                        JTextField[] fields = { new JTextField(10),
                                new JTextField((String) table.getValueAt(table.getSelectedRow(), 0)),
                                new JTextField(String.valueOf(table.getValueAt(table.getSelectedRow(), 1))),
                                new JTextField(String.valueOf(table.getValueAt(table.getSelectedRow(), 2))),
                                new JTextField(String.valueOf(table.getValueAt(table.getSelectedRow(), 3))),
                                new JTextField(String.valueOf(table.getValueAt(table.getSelectedRow(), 4))),
                                new JTextField(String.valueOf(table.getValueAt(table.getSelectedRow(), 5))),
                                new JTextField(String.valueOf(table.getValueAt(table.getSelectedRow(), 6))),
                                new JTextField(String.valueOf(table.getValueAt(table.getSelectedRow(), 7)))};
                        JLabel[] labels = { new JLabel("rodzaj użytkownika (U/N/O/A)"), new JLabel("id"), new JLabel("imie"), new JLabel("nazwisko"),
                                new JLabel("adres"), new JLabel("klasa/gabinet/-"), new JLabel("pesel"), new JLabel("numer telefonu"), new JLabel("email")};
                        for (int i = 0; i < 9; i++){
                            addPanel.add(labels[i]);
                            addPanel.add(fields[i]);
                        }
                        addPanel.add(Box.createVerticalStrut(5));
                        JButton remove = new JButton("Usuń użytkownika");
                        addPanel.add(remove);
                        JOptionPane.showConfirmDialog(null, addPanel, "Edytuj ucznia", JOptionPane.OK_CANCEL_OPTION);
                    }
                }
            }
        });
    }

    private void performAction(String type){
        try {
            List<Person> people = null;
            people = connector.showAll(type, firstnameField.getText(), lastnameField.getText());
            TableModelCreator creator = new TableModelCreator();
            myAbstractTableModel model = creator.getTableModel(type, people);
            table.setModel(model);

        } catch (Exception exc) {
            JOptionPane.showMessageDialog(AdminPanel.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
