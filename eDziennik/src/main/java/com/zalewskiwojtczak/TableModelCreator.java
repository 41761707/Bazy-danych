package com.zalewskiwojtczak;

import java.util.List;

public class TableModelCreator {
    public PersonTableModel getTableModel(String type, List<? extends Person> people){
        if (type.equalsIgnoreCase("Uczen")){
            return new StudentTableModel(people);
        }
        else
            return null;
    }
}
