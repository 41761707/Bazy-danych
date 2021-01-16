package com.zalewskiwojtczak;

import java.util.List;

public class TeacherViewGradeTableModel extends myAbstractTableModel {

	public TeacherViewGradeTableModel(List<TeacherViewGrade> grades){
        String[] gradeColumns =  { "Ocena","Legitymacja", "Imieucznia", "Nazwiskoucznia", "Klasa" ,"Przedmiot", "Data", "Komentarz"};
        columnNames = gradeColumns;
        objects = grades;
    }

    @Override
    public Object getValueAt(int row, int col) {
        TeacherViewGrade tempGrade = (TeacherViewGrade) objects.get(row);
        switch (col) {
            case 0:
                return tempGrade.getNote();
            case 1:
            	return tempGrade.getLegitymacja();
            case 2:
                return tempGrade.getStudentName();
            case 3:
                return tempGrade.getStudentSurname();
            case 4:
            	return tempGrade.getClassName();
            case 5:
                return tempGrade.getSubject();
            case 6:
                return tempGrade.getDate();
            case 7:
                return tempGrade.getComment();
            default:
                return null;
        }
    }
}
