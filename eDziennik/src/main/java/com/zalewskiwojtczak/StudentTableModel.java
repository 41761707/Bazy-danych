package com.zalewskiwojtczak;

import java.util.List;

public class StudentTableModel extends AbstractPersonTableModel {

    public StudentTableModel(List<Person> students){
        String[] studentColumns =  { "id", "Imie", "Nazwisko", "Adres", "Klasa", "Pesel", "Telefon", "Email"};
        columnNames = studentColumns;
        people = students;
    }
}
