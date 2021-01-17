package com.zalewskiwojtczak;

import java.util.List;

public class ParentViewGradeModel extends myAbstractTableModel{

	public ParentViewGradeModel(List<ParentViewGrade> grades){
        String[] gradeColumns =  { "Imie dziecka","Nazwisko dziecka","Ocena","Imie Nauczyciela", "Nazwisko Nauczyciela","Przedmiot", "Data", "Komentarz"};
        columnNames = gradeColumns;
        objects = grades;
    }

    @Override
    public Object getValueAt(int row, int col) {
        ParentViewGrade tempGrade = (ParentViewGrade) objects.get(row);
        switch (col) {
        	case 0:
        		return tempGrade.getStudentName();
        	case 1:
        		return tempGrade.getStudentSurname();
            case 2:
                return tempGrade.getNote();
            case 3:
                return tempGrade.getTeacherName();
            case 4:
                return tempGrade.getTeacherSurname();
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
