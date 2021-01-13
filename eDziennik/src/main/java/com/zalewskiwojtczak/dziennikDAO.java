package com.zalewskiwojtczak;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class dziennikDAO {
    private static Connection conn;
    private static String qStudent = "SELECT nrLegitymacji AS a, Imie AS b, Nazwisko AS c, ID_Adresu AS d, ID_Klasy AS e, PESEL AS f, nrTelefonu AS g, Email AS h FROM Uczen;";

    public dziennikDAO() throws Exception{
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/dziennik",
                "admin", "123");
        System.out.println("Successfully connected!");
    }

    public static List<Person> showAll(String type) throws Exception {
        List<Person> list = new ArrayList<>();

        Statement stmt = null;
        ResultSet query = null;

        try {
            stmt = conn.createStatement();
            if (type.equalsIgnoreCase("UCZEN"))
                query = stmt.executeQuery(qStudent);
            else {
                query = stmt.executeQuery("SELECT 0");
                return null;
            }

            while (query.next()) {
                Person tempPerson = new Student(query.getString("a"), query.getString("b"),
                        query.getString("c"), query.getInt("d"), query.getInt("e"),
                        query.getString("f"), query.getInt("g"), query.getString("h"));
                list.add(tempPerson);
            }

            return list;
        }
        finally {
            try{
                query.close();
                stmt.close();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
