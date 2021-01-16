package com.zalewskiwojtczak;

import java.util.List;

public class TeacherViewStudentTableModel extends myAbstractTableModel {

	public TeacherViewStudentTableModel(List<TeacherViewStudents> students){
        String[] studentColumns =  {"Legitymacja","Imię","Nazwisko","Klasa"};
        columnNames = studentColumns;
        objects = students;
    }

    @Override
    public Object getValueAt(int row, int col) {
        TeacherViewStudents tempStudent = (TeacherViewStudents) objects.get(row);
        switch (col) {
            case 0:
            	return tempStudent.getLegitymacja();
            case 1:
                return tempStudent.getStudentName();
            case 2:
                return tempStudent.getStudentSurname();
            case 3:
            	return tempStudent.getClassName();
            default:
                return null;
        }
    }
}
