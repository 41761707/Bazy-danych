package com.zalewskiwojtczak;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TeacherPanel extends JPanel {
    private final TeacherDataConnect connector;
    private JTable table;
    private String type;
    private JTextField lastnameField;
    private JTextField firstnameField;

    public TeacherPanel(DataConnect dataConnector){
        this.connector = (TeacherDataConnect) dataConnector;
        setLayout(new BorderLayout());

        JPanel panelUp = new JPanel();
        JButton[] buttons = {new JButton("Uczniowie"), new JButton("Oceny"), new JButton("Uwagi")};
        panelUp.add(new JLabel("Imię"));
        firstnameField = new JTextField(10);
        panelUp.add(firstnameField);
        panelUp.add(new JLabel("Nazwisko"));
        lastnameField = new JTextField(10);
        panelUp.add(lastnameField);
        panelUp.add(buttons[0]);
        panelUp.add(buttons[1]);
        panelUp.add(buttons[2]);
        add(panelUp, BorderLayout.NORTH);

        JPanel panelDown = new JPanel();
        JButton addMark = new JButton("Dodaj Ocene");
        JButton addNote = new JButton("Dodaj Uwage");
        panelDown.add(addMark);
        panelDown.add(addNote);
        add(panelDown, BorderLayout.SOUTH);

        JScrollPane scrollPane = new JScrollPane();
        add(scrollPane, BorderLayout.CENTER);
        table = new JTable();
        scrollPane.add(table);
        scrollPane.setViewportView(table);

        buttons[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                type = "Uczeń";
                performAction("Uczeń");
            }
        });
        buttons[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                type = "Ocena";
                performAction("Ocena");
            }
        });
        buttons[2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                type = "Uwaga";
                performAction("Uwaga");
            }
        });

        addMark.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    JPanel addPanel = new JPanel();
                    addPanel.setLayout(new GridLayout(0, 1));
                    JTextField[] fields = { new JTextField(11), new JTextField(30), new JTextField(10), new JTextField(4),
                            new JTextField(30)};
                    JLabel[] labels = { new JLabel("Legitymacja Ucznia"), new JLabel("Przedmiot"), new JLabel("Data"), new JLabel("Ocena"), new JLabel("Komentarz")};
                    for (int i = 0; i < labels.length; i++){
                        addPanel.add(labels[i]);
                        addPanel.add(fields[i]);
                    }
                    //JOptionPane.showConfirmDialog(null, addPanel, "Dodaj ocene", JOptionPane.OK_CANCEL_OPTION);
                    int reply=JOptionPane.showConfirmDialog(null, addPanel, "Dodaj użytkownika", JOptionPane.OK_CANCEL_OPTION);
                    if (reply == JOptionPane.YES_OPTION)
                    {
                        String[] replies= {"","","","",""};
                        for(int i=0;i<fields.length;i++)
                        {
                            replies[i]=fields[i].getText();
                        }
                        connector.addMark(replies);
                    }

                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(TeacherPanel.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        addNote.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    JPanel addPanel = new JPanel();
                    addPanel.setLayout(new GridLayout(0, 1));
                    JTextField[] fields = { new JTextField(11), new JTextField(2), new JTextField(30)};
                    JLabel[] labels = { new JLabel("Legitymacja Ucznia"), new JLabel("Punkty"), new JLabel("Komentarz")};
                    for (int i = 0; i < fields.length; i++){
                        addPanel.add(labels[i]);
                        addPanel.add(fields[i]);
                    }
                    int reply=JOptionPane.showConfirmDialog(null, addPanel, "Dodaj użytkownika", JOptionPane.OK_CANCEL_OPTION);
                    if (reply == JOptionPane.YES_OPTION)
                    {
                        String[] replies= {"","","","",""};
                        for(int i=0;i<fields.length;i++)
                        {
                            replies[i]=fields[i].getText();
                        }
                        connector.addNote(replies);
                    }

                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(TeacherPanel.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    private void performAction(String type){
        try {
            switch(type)
            {
                case "Uczeń":
                    List<Person> students = null;
                    students = connector.showStudents(firstnameField.getText(),lastnameField.getText());
                    StudentTableModelTeacher studentModel = new StudentTableModelTeacher(students);
                    table.setModel(studentModel);
                    break;
                case "Ocena":
                    List<Grade> grades = null;
                    grades = connector.showMarks(firstnameField.getText(),lastnameField.getText());
                    GradeTableModelTeacher gradesmodel = new GradeTableModelTeacher(grades);
                    table.setModel(gradesmodel);
                    break;
                case "Uwaga":
                    List<Note> notes = null;
                    notes=connector.showNotes(firstnameField.getText(),lastnameField.getText());
                    NoteTableModelTeacher notesModel = new NoteTableModelTeacher(notes);
                    table.setModel(notesModel);
                    break;
            }

        } catch (Exception exc) {
            JOptionPane.showMessageDialog(TeacherPanel.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}