/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import bean.IssuedResourceBean;
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
public class UserPenaltyDao {
      
    Connection con;
    Statement statement;
    boolean flag;
    
    /**
     *
     * @param upb
     */
    public void addPenalty(UserPenaltyBean upb){
    Connection con = null;
    Statement statement = null;
    
    try
    {
        con = DBConnection.createConnection();
        statement = con.createStatement();
        
        flag = statement.execute("INSERT INTO `user_penalties`(`issued_resource_id`, `value`) VALUES ("+upb.getIssuedResource().getId()+","+upb.getValue()+")");
        statement.close();
    }   
    catch (SQLException ex) {
        Logger.getLogger(ResourceDao.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println(ex.getLocalizedMessage());
    }
    }

    /**
     *
     * @param resource_id
     * @return
     */
    public UserPenaltyBean getPenaltyByResourceID(int resource_id){
    Connection con = null;
    Statement statement = null;
    
    try
    {
        con = DBConnection.createConnection();
        statement = con.createStatement();
        
        ResultSet rs = statement.executeQuery("select * from `user_penalties` where status = 1 and issued_resource_id ="+resource_id);
        
        if(rs.next()){
            IssuedResourceDao ird = new IssuedResourceDao();

            UserPenaltyBean upb = new UserPenaltyBean();
            upb.setIssuedResource(ird.getById(resource_id));
            upb.setPenaltyType(rs.getString("penalty_type"));
            upb.setValue(rs.getString("value"));
            return upb;
        }
        
        
        statement.close();
        return null;
    }   
    catch (SQLException ex) {
        Logger.getLogger(ResourceDao.class.getName()).log(Level.SEVERE, null, ex);
        
        System.out.println(ex.getLocalizedMessage());
        return null;
    }
    }

    /**
     *
     * @param upb
     */
    public void RemovePenalty(UserPenaltyBean upb){
    Connection con = null;
    Statement statement = null;
    
    try
    {
        con = DBConnection.createConnection();
        statement = con.createStatement();
        
        flag = statement.execute("Update from `user_penalties` SET status = 0 where issued_resource_id = "+upb.getIssuedResource().getId());
        statement.close();
    }   
    catch (SQLException ex) {
        Logger.getLogger(ResourceDao.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println(ex.getLocalizedMessage());
    }
    }

    /**
     *
     * @return
     */
    public Vector<UserPenaltyBean> getAllPenalties() {
        Connection con = null;
    Statement statement = null;
    Vector<UserPenaltyBean> list =new Vector<UserPenaltyBean>();

    
    try
    {
        con = DBConnection.createConnection();
        statement = con.createStatement();
        
        ResultSet rs = statement.executeQuery("select * from `user_penalties` left join issued_resources on user_penalties.issued_resource_id = issued_resources.id where user_penalties.status = 1");
        
        if(rs.next()){
            IssuedResourceDao ird = new IssuedResourceDao();

            UserPenaltyBean upb = new UserPenaltyBean();
            upb.setIssuedResource(ird.getById(Integer.parseInt(rs.getString("issued_resource_id"))));
            upb.setPenaltyType(rs.getString("penalty_type"));
            upb.setValue(rs.getString("value"));
            list.add(upb);
        }
        
        
        statement.close();
        return list;
    }   
    catch (SQLException ex) {
        Logger.getLogger(ResourceDao.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println(ex.getLocalizedMessage());
        return null;
    }
    }

}
