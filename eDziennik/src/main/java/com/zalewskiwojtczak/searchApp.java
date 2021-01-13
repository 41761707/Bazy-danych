package com.zalewskiwojtczak;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class searchApp extends JFrame {

    private JPanel contentPane;
    private JTextField functionTextField;
    private JButton btnSearch;
    private JScrollPane scrollPane;
    private JTable table;

    private dziennikDAO studentsDAO;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    searchApp frame = new searchApp();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public searchApp() {
        try {
            studentsDAO = new dziennikDAO();
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
        }

        setTitle("Search App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 650, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        contentPane.add(panel, BorderLayout.NORTH);

        JLabel lblEnterLastName = new JLabel("Osoba");
        panel.add(lblEnterLastName);

        functionTextField = new JTextField();
        panel.add(functionTextField);
        functionTextField.setColumns(10);

        btnSearch = new JButton("Szukaj");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String type = functionTextField.getText();
                    List<? extends Person> people = null;
                    if ((people = dziennikDAO.showAll(type)) == null){
                        throw new Exception();
                    }
                    TableModelCreator creator = new TableModelCreator();
                    PersonTableModel model = creator.getTableModel(type, people);
                    table.setModel(model);

                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(searchApp.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        panel.add(btnSearch);

        scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        scrollPane.setViewportView(table);
    }

}
