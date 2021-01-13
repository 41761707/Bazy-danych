package com.zalewskiwojtczak;

import java.util.List;

public class StudentTableModel extends PersonTableModel {

    public StudentTableModel(List<? extends Person> students){
        String[] studentColumns =  { "id", "Imie", "Nazwisko", "Adres", "Klasa", "Pesel", "Telefon", "Email"};
        columnNames = studentColumns;
        people = students;
    }

    @Override
    public Object getValueAt(int row, int col) {
        tempPerson = people.get(row);

        switch (col) {
            case 0:
                return tempPerson.getId();
            case 1:
                return tempPerson.getFirstName();
            case 2:
                return tempPerson.getLastName();
            case 3:
                return tempPerson.getAddress();
            case 4:
                return tempPerson.getStudentsClass();
            case 5:
                return tempPerson.getPesel();
            case 6:
                return tempPerson.getPhone();
            case 7:
                return tempPerson.getEmail();
            default:
                return null;
        }
    }
}
