/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import bean.UserBean;
import bean.UserPenaltyBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DBConnection;

/**
 *
 * @author james
 */
public class UserDao {
    Connection con;
    Statement statement;
    boolean flag;
    
    /**
     *
     * @return
     */
    public Vector<UserBean> getAllActiveUsers(){
        Vector<UserBean> users = new Vector<UserBean>();
        try
        {
            con = DBConnection.createConnection();
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select * from `users` where status = 1 and user_type='user' ");

            while(rs.next()){
                UserBean user = new UserBean();
                user.setName(rs.getString("name"));
                user.setId(Integer.parseInt(rs.getString("id")));
                user.setUserID(rs.getString("userID"));
                users.add(user);
            }

            statement.close();
            return users;
        }   
        catch (SQLException ex) {
            Logger.getLogger(ResourceDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getLocalizedMessage());
            return null;
        }
    } 
}
