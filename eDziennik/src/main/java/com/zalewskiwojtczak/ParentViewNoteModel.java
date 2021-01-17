package com.zalewskiwojtczak;

import java.util.List;

public class ParentViewNoteModel extends myAbstractTableModel{
	public ParentViewNoteModel(List<ParentViewNote> grades){
        String[] gradeColumns =  {"Imie dziecka","Nazwisko dziecka","Punkty","Imie Nauczyciela","Nazwisko Nauczyciela","Komentarz"};
        columnNames = gradeColumns;
        objects = grades;
    }

    @Override
    public Object getValueAt(int row, int col) {
        ParentViewNote tempGrade = (ParentViewNote) objects.get(row);
        switch (col) {
        	case 0:
        		return tempGrade.getStudentName();
        	case 1:
        		return tempGrade.getStudentSurname();
            case 2:
                return tempGrade.getPoints();
            case 3:
                return tempGrade.getTeacherName();
            case 4:
                return tempGrade.getTeacherSurname();
            case 5:
                return tempGrade.getComment();
            default:
                return null;
        }
    }

}
