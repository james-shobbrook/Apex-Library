/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

//DBConnection.java
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author james
 */
public class DBConnection {

    /**
     *
     * @return
     */
    public static Connection createConnection()
    {
        Connection con = null;
//        String url = "jdbc:mysql://localhost:3306/lms";
        String url = "jdbc:mysql://153.92.6.43:3306/u553259432_lms";
        String username = "u553259432_jim";
        String password = "admin";
    try
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        con = DriverManager.getConnection(url, username, password);
        System.out.println("Post establishing a DB connection - "+con);
    }
    catch (Exception e)
    {
        e.printStackTrace();
    }
        return con;
    }
}