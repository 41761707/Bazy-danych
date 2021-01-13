package com.zalewskiwojtczak;

import java.util.List;

public class TeacherTableModel extends AbstractPersonTableModel {

    public TeacherTableModel(List<Person> students){
        String[] studentColumns =  { "id", "Imie", "Nazwisko", "Adres", "Gabinet", "Pesel", "Telefon", "Email"};
        columnNames = studentColumns;
        people = students;
    }
}