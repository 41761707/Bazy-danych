package com.zalewskiwojtczak;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ParentPanel extends JPanel {
	private final ParentDataConnect connector;
    private JTable table;
    private JScrollPane scrollPane;
    private String currentTable;
    JTextField lastnameField;
    JTextField firstnameField;
    public ParentPanel(DataConnect dataConnector){
    	this.connector = (ParentDataConnect) dataConnector;
        setLayout(new BorderLayout());
        JPanel panelUp = new JPanel();
        JButton[] buttons={new JButton("Uczniowie"), new JButton("Oceny"), new JButton("Uwagi"),new JButton("Zachowanie")};
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
                performAction("Ocena");
            }
        });
        buttons[2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performAction("Uwaga");
            }
        });
        buttons[3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performAction("Zachowanie");
            }
        });

    }
    private void performAction(String type){
        try {
         	switch(type)
         	{
         		case "Uczeń":
         			List<TeacherViewStudents> students=null;
         			students=connector.showStudents();
         			TeacherViewStudentTableModel studentModel=new TeacherViewStudentTableModel(students);
         			table.setModel(studentModel);
         		break;
         		case "Ocena":
         			List <ParentViewGrade> grades=null;
         			grades=connector.showGrades(firstnameField.getText(),lastnameField.getText());
         			ParentViewGradeModel gradeModel=new ParentViewGradeModel(grades);
         			table.setModel(gradeModel);
         			
         		break;
         		case "Uwaga":
         			List <ParentViewNote> notes=null;
         			notes=connector.showNotes(firstnameField.getText(),lastnameField.getText());
         			ParentViewNoteModel notesModel=new ParentViewNoteModel(notes);
         			table.setModel(notesModel);
         		break;
         		case "Zachowanie":
         			List <ParentViewBehaviour> behaviours=null;
         			behaviours=connector.showBehaviour();
         			ParentViewBehaviourModel behavioursModel=new ParentViewBehaviourModel(behaviours);
         			table.setModel(behavioursModel);
         			break;
         		
         	}

        } catch (Exception exc) {
             JOptionPane.showMessageDialog(ParentPanel.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
         }
     }
}
