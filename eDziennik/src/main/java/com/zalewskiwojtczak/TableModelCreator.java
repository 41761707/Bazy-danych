package com.zalewskiwojtczak;

import java.util.List;

public class TableModelCreator {
    public AbstractPersonTableModel getTableModel(String type, List<Person> people){
        if (type.equalsIgnoreCase("UCZEN")){
            return new StudentTableModel(people);
        }
        else if (type.equalsIgnoreCase("NAUCZYCIEL")){
            return new TeacherTableModel(people);
        }
        else if (type.equalsIgnoreCase("OPIEKUN")){
            return new ParentTableModel(people);
        }
        else
            return null;
    }
}
