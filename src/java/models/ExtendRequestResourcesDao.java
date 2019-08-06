/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import bean.ExtendRequestResourcesBean;
import bean.IssuedResourceBean;
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
public class ExtendRequestResourcesDao {
    
    Connection con;
    Statement statement;
    boolean flag;
    
    /**
     *
     * @param erb
     * @return
     */
    public String addRequest(ExtendRequestResourcesBean erb){
    Connection con = null;
    Statement statement = null;
    
    try
    {
        con = DBConnection.createConnection();
        statement = con.createStatement();
        
        flag = statement.execute("INSERT INTO `extend_request_resources` (`issued_resource_id`,`requested_by`, `from_date`, "
                + "`to_date`) VALUES ("+erb.getIssuedResources().getId()+","+erb.getRequestedBy().getId()+",'"+erb.getFromDate()+
                "','"+erb.getToDate()+"')");
     statement.close();
     return "Added Successfully";
    }   
    catch (SQLException ex) {
        Logger.getLogger(ResourceDao.class.getName()).log(Level.SEVERE, null, ex);
        return ex.getLocalizedMessage();
    }
    }

    /**
     *
     * @return ExtendRequestResourcesBean list
     */
    public Vector<ExtendRequestResourcesBean> getAllRequest(){
    Connection con = null;
    Statement statement = null;
    ResultSet rs = null;
    Vector<ExtendRequestResourcesBean> list = new Vector<ExtendRequestResourcesBean>();
    try
    {
        con = DBConnection.createConnection();
        statement = con.createStatement();
        
        rs = statement.executeQuery("SELECT *,extend_request_resources.id as extend_id,extend_request_resources.status as extension_status from `extend_request_resources` "
                + "left join issued_resources on extend_request_resources.issued_resource_id = issued_resources.id"
                + " left join users on extend_request_resources.requested_by = users.id order by extend_request_resources.status DESC");
     while(rs.next()){
         ExtendRequestResourcesBean erb = new ExtendRequestResourcesBean();
         erb.setFromDate(rs.getString("from_date"));
         erb.setToDate(rs.getString("to_date"));

         UserBean user = new UserBean();
         user.setId(Integer.parseInt(rs.getString("requested_by")));
         user.setName(rs.getString("name"));
         user.setUserID(rs.getString("userID"));
         user.setUserType(rs.getString("user_type"));
         
         erb.setRequestedBy(user);
         
         IssuedResourceDao irb = new IssuedResourceDao();
         erb.setIssuedResources(irb.getById(Integer.parseInt(rs.getString("issued_resource_id"))));
         erb.setId(Integer.parseInt(rs.getString("extend_id")));
         
         erb.setStatus(rs.getString("extension_status"));
         list.add(erb);
         
     }
          statement.close();
          System.out.println(list.size());
          return list;
    }   
    catch (SQLException ex) {
        System.out.println(ex.getLocalizedMessage());
        return null;
    }
    }

    /**
     *
     * @param userid
     * @return
     */
    public Vector<ExtendRequestResourcesBean> getUserRequest(int userid){
    Connection con = null;
    Statement statement = null;
    ResultSet rs = null;
    Vector<ExtendRequestResourcesBean> list = new Vector<ExtendRequestResourcesBean>();
    try
    {
        con = DBConnection.createConnection();
        statement = con.createStatement();
        
        rs = statement.executeQuery("SELECT *,extend_request_resources.status as extension_status from `extend_request_resources` "
                + "left join issued_resources on extend_request_resources.issued_resource_id = issued_resources.id "
                + "left join users on extend_request_resources.approved_by = users.id where extend_request_resources.requested_by="+userid
                + " order by extend_request_resources.status DESC");
     while(rs.next()){
         ExtendRequestResourcesBean erb = new ExtendRequestResourcesBean();
         erb.setFromDate(rs.getString("from_date"));
         erb.setToDate(rs.getString("to_date"));
         if(rs.getString("approved_by")!=null){
         UserBean user = new UserBean();
         user.setId(Integer.parseInt(rs.getString("approved_by")));
         user.setName(rs.getString("name"));
         user.setUserID(rs.getString("userID"));
         user.setUserType(rs.getString("user_type"));
         erb.setApprovedBy(user);
         }
         
         IssuedResourceDao irb = new IssuedResourceDao();
         erb.setIssuedResources(irb.getById(Integer.parseInt(rs.getString("issued_resource_id"))));
         
         erb.setStatus(rs.getString("extension_status"));
         list.add(erb);
         
     }
          statement.close();
          return list;
    }   
    catch (SQLException ex) {
        System.out.println(ex.getLocalizedMessage());
        return null;
    }
    }

    /**
     *
     * @param erb
     * @return
     */
    public String setStatus(ExtendRequestResourcesBean erb) {
        Connection con = null;
        Statement statement = null;

        try
        {
            con = DBConnection.createConnection();
            statement = con.createStatement();
            if(erb.getStatus().equals("reject")){
                 statement.execute("Update `extend_request_resources` SET status = 'declined' , approved_by = "+erb.getApprovedBy().getId()+" where id ="+erb.getId());
            }
            else if(erb.getStatus().equals("approve"))
            {
                 statement.execute("Update `extend_request_resources` SET status = 'approved', approved_by = "+erb.getApprovedBy().getId()+" where id ="+erb.getId());
                 statement.execute("Update `issued_resources` SET status = 'approved' where id ="+erb.getIssuedResources().getId()+",issued_start_date ='"+erb.getFromDate()+"', issued_end_date='"+erb.getToDate()+"'");
            }
            statement.close();
         return "Updated Successfully";
        }   
        catch (SQLException ex) {
            Logger.getLogger(ResourceDao.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getLocalizedMessage();
        }
    }
}
