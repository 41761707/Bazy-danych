package com.zalewskiwojtczak;

import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class ParentDataConnect extends DataConnect {
    private int id;
    private CallableStatement stmnt;
    private ResultSet query;
    private final String userLogin;
    private final String userPassword;
    private boolean connection = true;
    private List<ParentViewGrade> grades;
    private List<TeacherViewStudents> students;
    private List<ParentViewNote> notes;
    private List<ParentViewBehaviour> behaviours;
    public ParentDataConnect(String userLogin, String userPassword) throws Exception {
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/dziennik2?noAccessToProcedureBodies=true",
                "opiekun", "opiekun");

        this.userLogin=userLogin;
        this.userPassword=userPassword;
        stmnt = (CallableStatement) conn.prepareCall("{CALL user_detail(?,?,?)}");
        stmnt.setString(1, userLogin);
        stmnt.setString(2, userPassword);
        stmnt.registerOutParameter(3, java.sql.Types.VARCHAR);
        stmnt.executeUpdate();
        String resultado = stmnt.getString(3);
        System.out.println(resultado);
        if(resultado == null || !resultado.equals("Opiekun"))
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
        CallableStatement cs = (CallableStatement) conn.prepareCall("{CALL parent_id(?,?,?)}");
        cs.setString(1, userLogin);
        cs.setString(2, userPassword);
        cs.registerOutParameter(3, java.sql.Types.INTEGER);
        cs.executeUpdate();
        int resultado = cs.getInt(3);
        return resultado;
    }

	public List<ParentViewGrade> showGrades(String firstName, String lastName) {
		grades = new ArrayList<ParentViewGrade>();
        if (firstName.length() == 0)
            firstName = "%";
        if (lastName.length() == 0)
            lastName = "%";
        try
        {
	        stmnt = conn.prepareCall("{CALL parent_grades(?,?,?)}");
	        stmnt.setString(1, firstName);
	        stmnt.setString(2, lastName);
	        stmnt.setInt(3,id);
	        stmnt.executeUpdate();
	        query = stmnt.getResultSet();
	        while(query.next())
	        {
	        	ParentViewGrade tempGrade=new ParentViewGrade(query.getString(1),query.getString(2),
	        			query.getInt(3),query.getString(4),query.getString(5),query.getString(6),
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
	public List<TeacherViewStudents> showStudents()
    {
    	students = new ArrayList<TeacherViewStudents>();
        try
        {
	        stmnt = conn.prepareCall("{CALL parent_students(?)}");
	        stmnt.setInt(1,id);
	        stmnt.executeUpdate();
	        query = stmnt.getResultSet();
	        while(query.next())
	        {
	        	TeacherViewStudents tempStudent=new TeacherViewStudents(query.getString(1),
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
	public List<ParentViewNote> showNotes(String firstName, String lastName) {
		notes = new ArrayList<ParentViewNote>();
        if (firstName.length() == 0)
            firstName = "%";
        if (lastName.length() == 0)
            lastName = "%";
        try
        {
	        stmnt = conn.prepareCall("{CALL parent_notes(?,?,?)}");
	        stmnt.setString(1, firstName);
	        stmnt.setString(2, lastName);
	        stmnt.setInt(3,id);
	        stmnt.executeUpdate();
	        query = stmnt.getResultSet();
	        while(query.next())
	        {
	        	ParentViewNote tempGrade=new ParentViewNote(query.getString(1),query.getString(2),
	        			query.getInt(3),query.getString(4),query.getString(5),query.getString(6));
	        	notes.add(tempGrade);
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
        		
        return notes;
    }
	public List<ParentViewBehaviour> showBehaviour() {
		behaviours=new ArrayList<ParentViewBehaviour>();
        try
        {
	        stmnt = conn.prepareCall("{CALL parent_behaviour(?)}");
	        stmnt.setInt(1,id);
	        stmnt.executeUpdate();
	        query = stmnt.getResultSet();
	        while(query.next())
	        {
	        	ParentViewBehaviour tempGrade=new ParentViewBehaviour(query.getString(1),query.getString(2),
	        			query.getInt(3));
	        	behaviours.add(tempGrade);
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
        return behaviours;
    }
}