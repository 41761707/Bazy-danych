package com.zalewskiwojtczak;

import java.util.List;

public class NoteTableModel extends myAbstractTableModel {

    public NoteTableModel(List<Note> grades){
        String[] noteColumns =  { "Punkty ujemne", "Imie nauczyciela", "Nazwisko nauczyciela", "Komentarz"};
        columnNames = noteColumns;
        objects = grades;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Note tempNote = (Note) objects.get(row);

        switch (col) {
            case 0:
                return tempNote.getMinusPoints();
            case 1:
                return tempNote.getTeacherName();
            case 2:
                return tempNote.getTeacherSurname();
            case 3:
                return tempNote.getComment();
            default:
                return null;
        }
    }
}
