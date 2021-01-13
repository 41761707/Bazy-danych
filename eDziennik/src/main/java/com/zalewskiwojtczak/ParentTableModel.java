package com.zalewskiwojtczak;

import java.util.List;

public class ParentTableModel extends AbstractPersonTableModel {

    public ParentTableModel(List<Person> students){
        String[] studentColumns =  { "id", "Imie", "Nazwisko", "Adres", "Pesel", "Telefon", "Email"};
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
                return tempPerson.getPesel();
            case 5:
                return tempPerson.getPhone();
            case 6:
                return tempPerson.getEmail();
            default:
                return null;
        }
    }
}