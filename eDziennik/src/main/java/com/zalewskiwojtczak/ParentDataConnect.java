package com.zalewskiwojtczak;

import java.sql.DriverManager;

public class ParentDataConnect extends DataConnect {
    public ParentDataConnect(String userLogin, String userPassword) throws Exception {
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/dziennik",
                "admin", "123");

        System.out.println("Parent successfully connected with login: " + userLogin + " and password: " + userPassword);
    }
}