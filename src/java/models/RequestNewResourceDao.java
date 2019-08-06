/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import bean.RequestNewResourceBean;
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
public class RequestNewResourceDao {

    /**
     *
     * @return
     */
    public Vector<RequestNewResourceBean> getAllResourcesBean(){
        Vector<RequestNewResourceBean> rsrc = new Vector<RequestNewResourceBean>();
        Connection con = null;
        Statement statement = null;
        ResultSet rs =null;
        try
        {
            con = DBConnection.createConnection();
            statement = con.createStatement();

             rs=statement.executeQuery("SELECT *, users.name as user_name,"
                     + " requests_new_resources.resource_name as book_name,requests_new_resources.status as resource_status from requests_new_resources "
                     + "left join users on created_by = users.id  order by requests_new_resources.status ASC");
            while(rs.next())
            {
                RequestNewResourceBean rb = new RequestNewResourceBean();
                rb.setName(rs.getString("book_name"));
                rb.setType(rs.getString("type"));
                rb.setCategory(rs.getString("category"));
                rb.setStatus(rs.getString("resource_status"));
                rb.setId(Integer.parseInt(rs.getString("id")));
                UserBean usr = new UserBean(rs.getString("user_name"), rs.getString("userID"), rs.getString("user_type"),
                Integer.parseInt(rs.getString("created_by")));
                rb.setCreatedBy(usr);
                rsrc.add(rb);
            }
        }   catch (SQLException ex) {
            Logger.getLogger(RequestNewResourceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rsrc;
    } 

    /**
     *
     * @param rsrc
     * @return boolean value
     */
    public boolean addNewResource(RequestNewResourceBean rsrc){
    
    Connection con = null;
    Statement statement = null;
    
    try
    {
        con = DBConnection.createConnection();
        statement = con.createStatement();
        
        return statement.execute("INSERT INTO `requests_new_resources`(`resource_name`, `type`, `category`,`created_by`) VALUES('"+rsrc.getName()+"','"
                +rsrc.getType()+"','"+rsrc.getCategory()+"',"
                +rsrc.getCreatedBy().getId()+")");
     
    }   catch (SQLException ex) {
            Logger.getLogger(RequestNewResourceDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getLocalizedMessage());
            return false;
        }

    }
       
    /**
     *
     * @param id
     * @return
     */
    public RequestNewResourceBean getByID(int id){
        Connection con = null;
        Statement statement = null;
        ResultSet rs =null;
        RequestNewResourceBean rb = null;
        try
        {
            con = DBConnection.createConnection();
            statement = con.createStatement();

             rs=statement.executeQuery("SELECT *, users.name as user_name,"
                     + " requests_new_resources.resource_name as book_name,requests_new_resources.status as resource_status from requests_new_resources "
                     + "left join users on created_by = users.id where requests_new_resources.id="+id);
            while(rs.next())
            {
                rb = new RequestNewResourceBean();
                rb.setName(rs.getString("book_name"));
                rb.setType(rs.getString("type"));
                rb.setCategory(rs.getString("category"));
                rb.setStatus(rs.getString("resource_status"));
                rb.setId(Integer.parseInt(rs.getString("id")));
                UserBean usr = new UserBean(rs.getString("user_name"), rs.getString("userID"), rs.getString("user_type"),
                Integer.parseInt(rs.getString("created_by")));
                rb.setCreatedBy(usr);
            }
        }   catch (SQLException ex) {
            Logger.getLogger(RequestNewResourceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rb;
    }
    
    /**
     *
     * @param rb
     */
    public void Purchased(RequestNewResourceBean rb){
        Connection con = null;
    Statement statement = null;
    
    try
    {
        con = DBConnection.createConnection();
        statement = con.createStatement();
        
         statement.execute("UPDATE `requests_new_resources` SET status = '"+rb.getStatus()+"' where id="+rb.getId());
     
    }   
    catch (SQLException ex) {
            Logger.getLogger(RequestNewResourceDao.class.getName()).log(Level.SEVERE, null, ex);
    }
        

    }
}
