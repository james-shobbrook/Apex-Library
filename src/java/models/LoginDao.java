/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
    //LoginDao.java
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import bean.LoginBean;
import bean.UserBean;
import util.DBConnection;

/**
 *
 * @author james
 */
public class LoginDao {

    /**
     *
     * @param loginBean
     * @return
     */
    public UserBean authenticateUser(LoginBean loginBean)
    {
    String userName = loginBean.getUserName();
    String password = loginBean.getPassword();
    Connection con = null;
    Statement statement = null;
    ResultSet resultSet = null;
    String userNameDB = "";
    String passwordDB = "";
    String roleDB = null;
    String useridDB;
    int idDB;
    try
    {
    con = DBConnection.createConnection();
    statement = con.createStatement();
        resultSet = statement.executeQuery("select * from users where name = '"+userName+"'");
    while(resultSet.next())
    {
        userNameDB = resultSet.getString("name");
        passwordDB = resultSet.getString("password");
        roleDB = resultSet.getString("user_type");
        useridDB = resultSet.getString("userID");
        idDB = Integer.parseInt(resultSet.getString("id"));
        if(userName.equals(userNameDB) && password.equals(passwordDB))
        {
         return new UserBean(userNameDB,useridDB,roleDB,idDB);   
        }
        //return null;
    }
    return null;
    }
    catch(SQLException e)
    {
        e.printStackTrace();
    }
        return null;
    }
}

