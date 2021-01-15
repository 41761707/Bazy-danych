package com.zalewskiwojtczak;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDataConnect extends DataConnect {
    private final String userLogin;
    private final String userPassword;
    private CallableStatement stmnt;
    private boolean connection = true;
    private ResultSet query;
    private List<Person> people;
    private int id;

    public AdminDataConnect(String loginA, String passwordA, String userlogin, String userpassword) throws Exception {
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/dziennik?noAccessToProcedureBodies=true",
                loginA, passwordA);

        this.userLogin="Pawel";
        this.userPassword="Zalewski";
        CallableStatement cs = (CallableStatement) conn.prepareCall("{CALL user_detail(?,?,?)}");
        cs.setString(1, userLogin);
        cs.setString(2, userPassword);
        cs.registerOutParameter(3, java.sql.Types.VARCHAR);
        cs.executeUpdate();
        String resultado = cs.getString(3);
        System.out.println(resultado);
        if(resultado == null || !resultado.equals("Administrator"))
        {
            connection=false;
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
        CallableStatement cs = (CallableStatement) conn.prepareCall("{CALL admin_id(?,?,?)}");
        cs.setString(1, userLogin);
        cs.setString(2, userPassword);
        cs.registerOutParameter(3, java.sql.Types.INTEGER);
        cs.executeUpdate();
        int resultado = cs.getInt(3);
        return resultado;
    }

    public List<Person> showAll(String type, String firstName, String lastName){
        people = new ArrayList<>();
        if (firstName.length() == 0)
            firstName = "%";
        if (lastName.length() == 0)
            lastName = "%";
        try {
            if (type.equalsIgnoreCase("Uczeń")){
                stmnt = conn.prepareCall("{CALL show_students(?,?)}");
            }
            else if (type.equalsIgnoreCase("Nauczyciel")){
                stmnt = conn.prepareCall("{CALL show_teachers(?,?)}");
            }
            else if (type.equalsIgnoreCase("Opiekun")){
                stmnt = conn.prepareCall("{CALL show_parents(?,?)}");
            }
            else if (type.equalsIgnoreCase("Admin")){
                stmnt = conn.prepareCall("{CALL show_admins(?,?)}");
            }
            stmnt.setString(1, firstName);
            stmnt.setString(2, lastName);
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
