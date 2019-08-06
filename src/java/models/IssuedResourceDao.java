/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import bean.IssuedResourceBean;
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

/**
 *
 * @author james
 */
public class IssuedResourceDao {
    Connection con;
    Statement statement;
    boolean flag;

    /**
     *
     * @param irb
     * @return
     */
    public boolean addRequest(IssuedResourceBean irb){
    Connection con = null;
    Statement statement = null;
    
    try
    {
        con = DBConnection.createConnection();
        statement = con.createStatement();
        
        flag = statement.execute("INSERT INTO `issued_resources` (`resource_id`,`requested_user_id`, `requested_start_date`, "
                + "`requested_end_date`,`status_type`) VALUES ("+irb.getResource().getId()+","+irb.getRequestedBy().getId()+",'"+irb.getRequestedStartDate()+
                "','"+irb.getRequestedEndDate()+"','"+irb.getStatusType()+"')");
     statement.close();
     return flag;
    }   catch (SQLException ex) {
            Logger.getLogger(ResourceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    /**
     *
     * @param id
     * @param role
     * @return
     */
    public Vector<IssuedResourceBean> getUserIssuedRequests(int id,String role){
        Vector<IssuedResourceBean> list = new Vector<IssuedResourceBean>();
       
        ResultSet rs =null;
        try
        {
            con = DBConnection.createConnection();
            statement = con.createStatement();
            if(role.equals("admin"))
            {     rs=statement.executeQuery("SELECT *,resources.name as resource_name,issued_resources.requested_user_id as user_id, "
                    + "users.name as user_name,resources.status as resource_status, resources.id as resource_id,"
                    + "issued_resources.id as issued_id,issued_resources.status as issued_status,users.userID as userDocID,"
                     + " resources.name as book_name from issued_resources "
                    + "left join users on requested_user_id = users.id "
                     + "left join resources on issued_resources.resource_id = resources.id where issued_resources.status='requested' order by issued_resources.id DESC");
            }else if(role.equals("user"))
            {   
                
                rs=statement.executeQuery("SELECT *,resources.name as resource_name,issued_resources.issued_by as user_id ,"
                        + "users.name as user_name,resources.status as resource_status, resources.id as resource_id,"
                        + "issued_resources.id as issued_id,issued_resources.status as issued_status,users.userID as userDocID, "
                     + " resources.name as book_name from issued_resources "
                    + "left join users on requested_user_id = users.id "
                     + "left join resources on issued_resources.resource_id = resources.id "
                    + "where issued_resources.requested_user_id = "+id);
            }
            while(rs.next())
            {
                
                ResourceBean rb = new ResourceBean();
                rb.setName(rs.getString("resource_name"));
                rb.setType(rs.getString("type"));
                rb.setCategory(rs.getString("category"));
                if(rs.getString("user_rating")!=null)
                    rb.setRating(Float.parseFloat(rs.getString("user_rating")));
                if(rs.getString("resource_status")!=null)
                    rb.setStatus(Integer.parseInt(rs.getString("resource_status")));
                if(rs.getString("resource_id")!=null)
                    rb.setId(Integer.parseInt(rs.getString("resource_id")));
                
                IssuedResourceBean irb = new IssuedResourceBean();
                irb.setStatusType(rs.getString("status_type").toString());
                if(rs.getString("user_id")!=null){
                    UserBean usr = new UserBean(rs.getString("user_name"), rs.getString("userDocID"), rs.getString("user_type"),
                    Integer.parseInt((rs.getString("user_id"))));
                rb.setUser(usr);
                if(role.equals("user"))
                     irb.setIssuedBy(usr);
                else
                     irb.setRequestedBy(usr);
                    
                }
                
                irb.setResource(rb);
                
                irb.setId(Integer.parseInt(rs.getString("issued_id")));
                irb.setIssuedStartDate(rs.getString("issued_start_date"));
                irb.setIssuedEndDate(rs.getString("issued_end_date"));
                irb.setRequestedStartDate(rs.getString("requested_start_date"));
                irb.setRequestedEndDate(rs.getString("requested_end_date"));
                irb.setStatus(rs.getString("issued_status"));
                irb.setReturnDate(rs.getString("return_date"));
                list.add(irb);
            }
            
        statement.close();
        }catch(Exception e){
            System.out.println(e.getStackTrace());
            System.out.println("error here");
            System.out.println(e.getLocalizedMessage());
        }
        return list;
    }

    /**
     *
     * @return
     */
    public Vector<IssuedResourceBean> getRequestedIssuedRequests(){
        Vector<IssuedResourceBean> list = new Vector<IssuedResourceBean>();
       
        ResultSet rs =null;
        try
        {
            con = DBConnection.createConnection();
            statement = con.createStatement();

             rs=statement.executeQuery("SELECT *, users.name as user_name,issued_resources.id as issued_id,issued_resources.status as issued_status,"
                     + " resources.name as book_name from issued_resources left join users on requested_user_id = users.id "
                     + "left join resources on issued_resources.resource_id = resources.id where issued_resources.status = 'requested'");
            while(rs.next())
            {
                
                ResourceBean rb = new ResourceBean();
                rb.setName(rs.getString("name"));
                rb.setType(rs.getString("type"));
                rb.setCategory(rs.getString("category"));
                rb.setRating(Float.parseFloat(rs.getString("user_rating")));
                rb.setStatus(Integer.parseInt(rs.getString("status")));
                rb.setId(Integer.parseInt(rs.getString("resource_id")));
                
                UserBean usr = new UserBean(rs.getString("user_name"), rs.getString("userID"), rs.getString("user_type"),
                Integer.parseInt(rs.getString("requested_user_id")));
                rb.setUser(usr);
                
                IssuedResourceBean irb = new IssuedResourceBean();
                irb.setId(Integer.parseInt(rs.getString("issued_id")));
                irb.setRequestedBy(usr);
                irb.setIssuedStartDate(rs.getString("issued_start_date"));
                irb.setIssuedEndDate(rs.getString("issued_end_date"));
                irb.setRequestedStartDate(rs.getString("requested_start_date"));
                irb.setRequestedEndDate(rs.getString("requested_end_date"));
                irb.setStatus(rs.getString("issued_status"));
                irb.setStatusType(rs.getString("status_type"));
                irb.setResource(rb);
                irb.setReturnDate(rs.getString("return_date"));
                list.add(irb);
            }
            
        statement.close();
        }catch(Exception e){
            e.getLocalizedMessage();
        }
        return list;
    }

    /**
     *
     * @param StartDate
     * @param EndDate
     * @param resource_id
     * @return
     */
    public boolean CheckResourceAvailaiblity(String StartDate,String EndDate,int resource_id){
        try {
           con = DBConnection.createConnection();
           statement  = con.createStatement();
            System.out.println("Select * from issued_resources where resource_id = "+resource_id+" or ('"+StartDate+"' between issued_start_date and issued_end_date or '"+EndDate+"' between issued_start_date and issued_end_date)");
           ResultSet rs = statement.executeQuery("Select * from issued_resources where resource_id = "+resource_id+" and ('"+StartDate+"' between issued_start_date and issued_end_date or '"+EndDate+"' between issued_start_date and issued_end_date)");
           if(rs.next()){
               return false;
           }
           return true;
        } catch (Exception e) {
            e.getLocalizedMessage();
            return false;
        }
    }

    /**
     *
     * @param irb
     * @param userid
     * @return
     */
    public boolean UpdateRequestedIssuedRequest(IssuedResourceBean irb,int userid){
   
    try
    {
        con = DBConnection.createConnection();
        statement = con.createStatement();
        String stmt = "issued_by = "+userid;
        if(irb.getStatus().equals("approve")){
            stmt+=" , issued_resources.status = issued_resources.status_type , issued_start_date = '"+irb.getIssuedStartDate()+"' , issued_end_date = '"+irb.getIssuedEndDate()+"'";
        }else{
            stmt+=" , status = 'declined'";
        }
        flag = statement.execute("UPDATE `issued_resources` SET "+stmt+" where id="+irb.getId());
      
        if(irb.getStatus().equals("approve")){
        ResultSet rs = statement.executeQuery("select status from `issued_resources` where id ="+irb.getId());
        
        int st = 0;
        while(rs.next()){
            String stats= rs.getString("status");
            if(stats.equals("On-Site")){
                st = 2;
            }else if(stats.equals("Borrow")){
                st = 0;
            }else{
                st = 1;
            }
        }
        
        flag = statement.execute("UPDATE `resources` SET status = "+st+" where id="+irb.getResource().getId());            
        statement.close();
        }
        
        return true;
    }   catch (SQLException ex) {
            Logger.getLogger(ResourceDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     *
     * @return
     */
    public Vector<IssuedResourceBean> getAllIssuedResources() {
        Vector<IssuedResourceBean> list = new Vector<IssuedResourceBean>();
       
        ResultSet rs =null;
        try
        {
            con = DBConnection.createConnection();
            statement = con.createStatement();
              
             rs=statement.executeQuery("SELECT *,resources.name as resource_name,issued_resources.requested_user_id as user_id ,"
                     + "users.name as user_name,resources.status as resource_status, resources.id as resource_id,"
                     + "issued_resources.id as issued_id,issued_resources.status as issued_status,"
                     + " resources.name as book_name from issued_resources "
                    + "left join users on requested_user_id = users.id "
                     + "left join resources on issued_resources.resource_id = resources.id "
                     + "where resources.status != '1' order by issued_resources.issued_end_date DESC");
            while(rs.next())
            {
                
                ResourceBean rb = new ResourceBean();
                rb.setName(rs.getString("name"));
                rb.setType(rs.getString("type"));
                rb.setCategory(rs.getString("category"));
                rb.setRating(Float.parseFloat(rs.getString("user_rating")));
                rb.setStatus(Integer.parseInt(rs.getString("resource_status")));
                rb.setId(Integer.parseInt(rs.getString("resource_id")));
                
                UserBean usr = new UserBean(rs.getString("user_name"), rs.getString("userID"), rs.getString("user_type"),
                Integer.parseInt(rs.getString("requested_user_id")));
                rb.setUser(usr);
                
                IssuedResourceBean irb = new IssuedResourceBean();
                irb.setId(Integer.parseInt(rs.getString("issued_id")));
                irb.setRequestedBy(usr);
                
                irb.setIssuedStartDate(rs.getString("issued_start_date"));
                irb.setIssuedEndDate(rs.getString("issued_end_date"));
                irb.setRequestedStartDate(rs.getString("requested_start_date"));
                irb.setRequestedEndDate(rs.getString("requested_end_date"));
                irb.setStatus(rs.getString("issued_status"));
                irb.setStatusType(rs.getString("status_type"));
                irb.setResource(rb);
                irb.setReturnDate(rs.getString("return_date"));
                list.add(irb);
            }
            
        statement.close();
        }catch(Exception e){
            
            System.out.println(e.getLocalizedMessage());
        }
        return list;
    }

    /**
     *
     * @param irb
     * @return
     */
    public boolean ReturnRequest(IssuedResourceBean irb) {
    try
    {
        con = DBConnection.createConnection();
        statement = con.createStatement();
        System.out.println("UPDATE `issued_resources` SET return_date ='"+irb.getReturnDate()+"', status_type = '"+irb.getStatusType()+"' where id="+irb.getId());
        flag = statement.execute("UPDATE `issued_resources` SET return_date ='"+irb.getReturnDate()+"', status_type = '"+irb.getStatusType()+"' where id="+irb.getId());
        flag = statement.execute("UPDATE `resources` SET status = 1 where id ="+irb.getResource().getId());
      
     return true;
    }catch(Exception e){
             System.out.println(e.getLocalizedMessage());
             return false;
    }
    }
    
    /**
     *
     * @param issued_resource_id
     * @return
     */
    public IssuedResourceBean getById(int issued_resource_id){
        IssuedResourceBean irb = new IssuedResourceBean();
       
        ResultSet rs =null;
        try
        {
            con = DBConnection.createConnection();
            statement = con.createStatement();

             rs=statement.executeQuery("SELECT *, users.name as user_name,issued_resources.id as issued_id,issued_resources.status as issued_status,"
                     + " resources.name as book_name, resources.status as resource_status from issued_resources left join users on requested_user_id = users.id "
                     + "left join resources on issued_resources.resource_id = resources.id "
                     + "where issued_resources.id = "+issued_resource_id);
            if(rs.next())
            {
                
                ResourceBean rb = new ResourceBean();
                rb.setName(rs.getString("book_name"));
                rb.setType(rs.getString("type"));
                rb.setCategory(rs.getString("category"));
                rb.setRating(Float.parseFloat(rs.getString("user_rating")));
                rb.setStatus(Integer.parseInt(rs.getString("resource_status")));
                rb.setId(Integer.parseInt(rs.getString("resource_id")));
                
                UserBean usr = new UserBean(rs.getString("user_name"), rs.getString("userID"), rs.getString("user_type"),
                Integer.parseInt(rs.getString("requested_user_id")));
                rb.setUser(usr);
                
                irb.setId(Integer.parseInt(rs.getString("issued_id")));
                irb.setRequestedBy(usr);
                irb.setIssuedStartDate(rs.getString("issued_start_date"));
                irb.setIssuedEndDate(rs.getString("issued_end_date"));
                irb.setRequestedStartDate(rs.getString("requested_start_date"));
                irb.setRequestedEndDate(rs.getString("requested_end_date"));
                irb.setStatus(rs.getString("issued_status"));
                irb.setStatusType(rs.getString("status_type"));
                irb.setResource(rb);
                irb.setReturnDate(rs.getString("return_date"));
            }
            
        statement.close();
        return irb;
        }catch(Exception e){
            
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }
}
