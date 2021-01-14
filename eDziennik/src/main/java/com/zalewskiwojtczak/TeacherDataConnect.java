package com.zalewskiwojtczak;

import java.sql.DriverManager;

public class TeacherDataConnect extends DataConnect {
    public TeacherDataConnect(String userLogin, String userPassword) throws Exception {
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/dziennik",
                "admin", "123");

        System.out.println("Teacher successfully connected with login: " + userLogin + " and password: " + userPassword);
    }
}