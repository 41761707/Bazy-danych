package com.zalewskiwojtczak;

import java.util.List;

public class TeacherViewNoteTableModel extends myAbstractTableModel {

	public TeacherViewNoteTableModel(List<TeacherViewNote> notes){
        String[] noteColumns =  { "Punkty","Legitymacja","Imię","Nazwisko","Klasa","Komentarz"};
        columnNames = noteColumns;
        objects = notes;
    }

    @Override
    public Object getValueAt(int row, int col) {
        TeacherViewNote tempNote = (TeacherViewNote) objects.get(row);
        switch (col) {
            case 0:
                return tempNote.getPoints();
            case 1:
            	return tempNote.getLegitymacja();
            case 2:
                return tempNote.getStudentName();
            case 3:
                return tempNote.getStudentSurname();
            case 4:
            	return tempNote.getClassName();
            case 5:
                return tempNote.getComment();
            default:
                return null;
        }
    }
}
