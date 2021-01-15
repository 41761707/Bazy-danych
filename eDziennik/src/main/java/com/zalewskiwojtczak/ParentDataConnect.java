package com.zalewskiwojtczak;

import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ParentDataConnect extends DataConnect {
    private int id;
    private CallableStatement stmnt;
    private ResultSet query;
    private final String userLogin;
    private final String userPassword;
    private boolean connection = true;

    public ParentDataConnect(String userLogin, String userPassword) throws Exception {
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/dziennik?noAccessToProcedureBodies=true",
                "admin", "123");

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
}