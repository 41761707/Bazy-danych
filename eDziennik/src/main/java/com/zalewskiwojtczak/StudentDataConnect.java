package com.zalewskiwojtczak;

import java.sql.DriverManager;

public class StudentDataConnect extends DataConnect {
    public StudentDataConnect(String userLogin, String userPassword) throws Exception {
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/dziennik",
                "admin", "123");

        System.out.println("Student successfully connected with login: " + userLogin + " and password: " + userPassword);
    }
}
