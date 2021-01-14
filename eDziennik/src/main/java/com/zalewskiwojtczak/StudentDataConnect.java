package com.zalewskiwojtczak;

import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDataConnect extends DataConnect {
    private String id = "4/2021";
    private CallableStatement stmnt;
    private ResultSet query;

    public StudentDataConnect(String userLogin, String userPassword) throws Exception {
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/dziennik?noAccessToProcedureBodies=true",
                "admin", "123");

        System.out.println("Student successfully connected with login: " + userLogin + " and password: " + userPassword);
    }

    public List<Grade> showGrades(String subject){
        if (subject.length() == 0)
            subject = "%";
        List<Grade> grades = new ArrayList<>();
        try {
            stmnt = conn.prepareCall("{CALL show_marks(?,?)}");
            stmnt.setString(1, id);
            stmnt.setString(2, subject);
            stmnt.executeUpdate();
            query = stmnt.getResultSet();
            while(query.next())
            {
                grades.add(new Grade(query.getInt(1), query.getString(2), query.getString(3), query.getString(4), query.getDate(5), query.getString(6)));
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
        return grades;
    }

    public List<Note> showNotes(){
         List<Note> notes = new ArrayList<>();
        try {
            stmnt = conn.prepareCall("{CALL show_notes(?)}");
            stmnt.setString(1, id);
            stmnt.executeUpdate();
            query = stmnt.getResultSet();
            while(query.next())
            {
                notes.add(new Note(query.getInt(1), query.getString(2), query.getString(3), query.getString(4)));
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
        return notes;
    }

    public int getBehaviour(){
        int resultado = -1;
        try {
            stmnt = conn.prepareCall("{CALL show_points(?)}");
            stmnt.setString(1, id);
            stmnt.executeUpdate();
            query = stmnt.getResultSet();
            query.next();
            resultado = query.getInt(1);
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
        return resultado;
    }
}
