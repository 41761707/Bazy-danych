package com.zalewskiwojtczak;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminDataConnect extends DataConnect {
    private final String qStudent = "SELECT nrLegitymacji AS a, Imie AS b, Nazwisko AS c, ID_Adresu AS d, ID_Klasy AS e, PESEL AS f, nrTelefonu AS g, Email AS h FROM Uczen;";
    private final String qTeacher = "SELECT ID_Nauczyciela AS a, Imie AS b, Nazwisko AS c, ID_Adresu AS d, nrGabinetu AS e, PESEL AS f, nrTelefonu AS g, Email AS h FROM Nauczyciel;";
    private final String qParent = "SELECT ID_Opiekuna AS a, Imie AS b, Nazwisko AS c, ID_Adresu AS d, 0 AS e, PESEL AS f, nrTelefonu AS g, Email AS h FROM Opiekun;";
    private PreparedStatement stmnt;
    private ResultSet query;
    private List<Person> people;
    private int id;

    public AdminDataConnect(String userLogin, String userPassword) throws Exception {
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/dziennik",
                "admin", "123");

        System.out.println("Admin successfully connected with login: " + userLogin + " and password: " + userPassword);
    }

    public List<Person> showAll(String type){
        people = new ArrayList<>();
        try {
            if (type.equalsIgnoreCase("Uczeń")){
                stmnt = conn.prepareStatement(qStudent);
            }
            else if (type.equalsIgnoreCase("Nauczyciel")){
                stmnt = conn.prepareStatement(qTeacher);
            }
            else if (type.equalsIgnoreCase("Opiekun")){
                stmnt = conn.prepareStatement(qParent);
            }
            query = stmnt.executeQuery();
            while (query.next()) {
                Person tempPerson = new Person(query.getString(1), query.getString(2),
                        query.getString(3), query.getInt(4), query.getInt(5),
                        query.getString(6), query.getInt(7), query.getString(8));
                people.add(tempPerson);
            }
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            try{
                query.close();
                stmnt.close();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return people;
    }
}
