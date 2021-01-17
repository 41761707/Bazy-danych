package com.zalewskiwojtczak;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDataConnect extends DataConnect {
    private int id;
    private CallableStatement stmnt;
    private ResultSet query;
    private final String userLogin;
    private final String userPassword;
    private boolean connection = true;

    public TeacherDataConnect(String loginT, String passwordT, String userLogin, String userPassword) throws Exception {
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/dziennik2?noAccessToProcedureBodies=true",
                loginT, passwordT);

        this.userLogin=userLogin;
        this.userPassword=userPassword;
        stmnt = (CallableStatement) conn.prepareCall("{CALL user_detail(?,?,?)}");
        stmnt.setString(1, userLogin);
        stmnt.setString(2, userPassword);
        stmnt.registerOutParameter(3, java.sql.Types.VARCHAR);
        stmnt.executeUpdate();
        String resultado = stmnt.getString(3);
        System.out.println(resultado);
        if(resultado == null || !resultado.equals("Nauczyciel"))
        {
            connection = false;
        }
        id = setId();
    }

    @Override
    public boolean failed() {
        if(connection)
        {
            return false;
        }
        return true;
    }

    private int setId() throws SQLException {
        CallableStatement cs = (CallableStatement) conn.prepareCall("{CALL teacher_id(?,?,?)}");
        cs.setString(1, userLogin);
        cs.setString(2, userPassword);
        cs.registerOutParameter(3, java.sql.Types.VARCHAR);
        cs.executeUpdate();
        int resultado = cs.getInt(3);
        System.out.println(resultado);
        return resultado;
    }

    public List<Grade> showMarks(String firstName, String lastName)
    {
        List<Grade> grades = new ArrayList<>();
        if (firstName.length() == 0)
            firstName = "%";
        if (lastName.length() == 0)
            lastName = "%";
        try
        {
            stmnt = conn.prepareCall("{CALL marks_teacher_view(?,?,?)}");
            stmnt.setString(1, firstName);
            stmnt.setString(2, lastName);
            stmnt.setInt(3,id);
            stmnt.executeUpdate();
            query = stmnt.getResultSet();
            while(query.next())
            {
                Grade tempGrade = new Grade(query.getFloat(1),
                        query.getString(2),query.getString(3),query.getString(4),query.getString(5),query.getString(6),
                        query.getDate(7),query.getString(8));
                grades.add(tempGrade);
            }
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Niepoprawne dane", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try{
                query.close();
                stmnt.close();
            } catch (Exception ex){

            }
        }

        return grades;
    }
    public List<Note> showNotes(String firstName, String lastName)
    {
        List<Note> notes = new ArrayList<>();
        if (firstName.length() == 0)
            firstName = "%";
        if (lastName.length() == 0)
            lastName = "%";
        try
        {
            stmnt = conn.prepareCall("{CALL notes_teacher_view(?,?,?)}");
            stmnt.setString(1, firstName);
            stmnt.setString(2, lastName);
            stmnt.setInt(3,id);
            stmnt.executeUpdate();
            query = stmnt.getResultSet();
            while(query.next())
            {
                Note tempNote = new Note(query.getInt(1),
                        query.getString(2),query.getString(3),query.getString(4),query.getString(5),query.getString(6));
                notes.add(tempNote);
            }
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Niepoprawne dane", "Error", JOptionPane.ERROR_MESSAGE);
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
    public List<Person> showStudents(String firstName, String lastName)
    {
        List<Person> students = new ArrayList<>();
        if (firstName.length() == 0)
            firstName = "%";
        if (lastName.length() == 0)
            lastName = "%";
        try
        {
            stmnt = conn.prepareCall("{CALL students_teacher_view(?,?,?)}");
            stmnt.setString(1, firstName);
            stmnt.setString(2, lastName);
            stmnt.setInt(3,id);
            stmnt.executeUpdate();
            query = stmnt.getResultSet();
            while(query.next())
            {
                Person tempStudent=new Person(query.getString(1),
                        query.getString(2),query.getString(3),query.getString(4));
                students.add(tempStudent);
            }
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Niepoprawne dane", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try{
                query.close();
                stmnt.close();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }

        return students;
    }
    public void addMark(String[] fields)
    {
        try
        {
            Date date=Date.valueOf(fields[2]);
            stmnt=conn.prepareCall("{CALL dodaj_ocene(?,?,?,?,?,?)}");
            CallableStatement cs=conn.prepareCall("{CALL find_subjectID(?)}");
            CallableStatement legitymacjaCheck=conn.prepareCall("{CALL legitymacja_check(?)}");
            legitymacjaCheck.setString(1, fields[0]);
            legitymacjaCheck.executeUpdate();
            ResultSet test=legitymacjaCheck.getResultSet();
            test.next();
            int check=test.getInt(1);
            if(check>0)
            {
                cs.setString(1, fields[1]);
                cs.executeUpdate();
                query=cs.getResultSet();
                query.next();
                int subjectID=query.getInt(1);
                stmnt.setString(1, fields[0]);
                stmnt.setInt(2, id);
                stmnt.setInt(3, subjectID);
                stmnt.setDate(4,date);
                stmnt.setFloat(5,Float.parseFloat(fields[3]));
                stmnt.setString(6, fields[4]);
                stmnt.execute();
                cs.close();
            }
            else
            {
                throw new SQLException();
            }

        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Niepoprawne dane", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try{
                stmnt.close();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
    public void addNote(String[] fields)
    {
        try
        {
            stmnt=conn.prepareCall("{CALL dodaj_uwage(?,?,?,?)}");
            CallableStatement legitymacjaCheck=conn.prepareCall("{CALL legitymacja_check(?)}");
            legitymacjaCheck.setString(1, fields[0]);
            legitymacjaCheck.executeUpdate();
            ResultSet test=legitymacjaCheck.getResultSet();
            test.next();
            int check=test.getInt(1);
            if(check>0)
            {
                stmnt.setString(1, fields[0]);
                stmnt.setInt(2, id);
                stmnt.setInt(3, Integer.parseInt(fields[1]));
                stmnt.setString(4,fields[2]);
                stmnt.execute();
            }

        } catch (Exception ex){
            ex.printStackTrace();
            //JOptionPane.showMessageDialog(null, "Niepoprawne dane", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try{
                stmnt.close();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}