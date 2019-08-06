/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import bean.ResourceBean;
import bean.UserBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DBConnection;
import util.General;

/**
 *
 * @author james
 */
public class ResourceDao {

    /**
     *
     * @return
     */
    public Vector<ResourceBean> getAllResourcesBean(){
        Vector<ResourceBean> rsrc = new Vector<ResourceBean>();
        Connection con = null;
        Statement statement = null;
        ResultSet rs =null;
        try
        {
            con = DBConnection.createConnection();
            statement = con.createStatement();

             rs=statement.executeQuery("SELECT *, users.name as user_name,"
                     + " resources.name as book_name from resources left join users on created_by = users.id ");
            while(rs.next())
            {
                ResourceBean rb = new ResourceBean();
                rb.setName(rs.getString("book_name"));
                rb.setType(rs.getString("type"));
                rb.setCategory(rs.getString("category"));
                rb.setRating(Float.parseFloat(rs.getString("user_rating")));
                rb.setStatus(Integer.parseInt(rs.getString("status")));
                rb.setId(Integer.parseInt(rs.getString("id")));
                UserBean usr = new UserBean(rs.getString("user_name"), rs.getString("userID"), rs.getString("user_type"),
                Integer.parseInt(rs.getString("created_by")));
                rb.setUser(usr);
                rsrc.add(rb);
            }
        }   catch (SQLException ex) {
            Logger.getLogger(ResourceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rsrc;
    } 

    /**
     *
     * @param rsrc
     * @return boolean value
     */
    public boolean addNewResource(ResourceBean rsrc){
    
    Connection con = null;
    Statement statement = null;
    
    try
    {
        con = DBConnection.createConnection();
        statement = con.createStatement();
        
        return statement.execute("INSERT INTO `resources`(`name`, `type`, `category`,`created_by`) VALUES('"+rsrc.getName()+"','"
                +rsrc.getType()+"','"+rsrc.getCategory()+"',"
                +rsrc.getUser().getId()+")");
     
    }   catch (SQLException ex) {
            Logger.getLogger(ResourceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }
       
    /**
     *
     * @param id
     * @return
     */
    public ResourceBean getByID(int id){
        Connection con = null;
        Statement statement = null;
        ResultSet rs =null;
        ResourceBean rb = null;
        try
        {
            con = DBConnection.createConnection();
            statement = con.createStatement();

             rs=statement.executeQuery("SELECT *, users.name as user_name,"
                     + " resources.name as book_name from resources left join users on created_by = users.id where resources.id="+id);
            while(rs.next())
            {
                //AGAin -__-
                rb = new ResourceBean();
                rb.setName(rs.getString("book_name"));
                rb.setType(rs.getString("type"));
                rb.setCategory(rs.getString("category"));
                rb.setRating(Float.parseFloat(rs.getString("user_rating")));
                rb.setStatus(Integer.parseInt(rs.getString("status")));
                rb.setId(Integer.parseInt(rs.getString("id")));
                UserBean usr = new UserBean(rs.getString("user_name"), rs.getString("userID"), rs.getString("user_type"),
                Integer.parseInt(rs.getString("created_by")));
                rb.setUser(usr);
            }
        }   catch (SQLException ex) {
            Logger.getLogger(ResourceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rb;
    }
    
    /**
     *
     * @param rating
     * @param resource_id
     */
    public void addRating(float rating,int resource_id){
        Connection con = null;
    Statement statement = null;
    rating = General.round(rating, 2);
    
    try
    {
        con = DBConnection.createConnection();
        statement = con.createStatement();
        
         statement.execute("UPDATE `resources` SET user_rating = "+rating+" where id="+resource_id);
     
    }   
    catch (SQLException ex) {
            Logger.getLogger(ResourceDao.class.getName()).log(Level.SEVERE, null, ex);
    }
        

    }
}
