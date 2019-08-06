/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import bean.ReminderBean;
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
public class ReminderDao {
    Connection con;
    Statement statement;
    boolean flag;
    
    /**
     *
     * @param reminder
     * @return
     */
    public String sendReminder(ReminderBean reminder){
        try
    {
        con = DBConnection.createConnection();
        statement = con.createStatement();
        
        flag = statement.execute("INSERT INTO `reminders` (`user_id`,`type`, `msg`, "
                + "`created_by`) VALUES ("+reminder.getUser().getId()+", '"+reminder.getType()+"','"+reminder.getMessage()+"',"+reminder.getCreatedBy().getId()+")");
     statement.close();
        return "Sent Successfully!";
    }   catch (SQLException ex) {
            return ex.getLocalizedMessage();
            
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public Vector<ReminderBean> getAllUnread(int id) {
         Vector<ReminderBean> list = new Vector<ReminderBean>();
       
        ResultSet rs =null;
        try
        {
            con = DBConnection.createConnection();
            statement = con.createStatement();

             rs=statement.executeQuery("select * from reminders "
                     + "left join users on users.id = created_by "
                     + "where msg_read = 0 and reminders.user_id = "+id+" order by date_time DESC");
            while(rs.next())
            {
                ReminderBean reminder = new ReminderBean();
                
                UserBean user = new UserBean();
                user.setId(Integer.parseInt(rs.getString("created_by")));
                user.setName(rs.getString("name"));
                user.setUserType(rs.getString("user_type"));
                user.setUserID(rs.getString("userID"));
                
                reminder.setCreatedBy(user);
                
                reminder.setMessage(rs.getString("msg"));
                reminder.setType(rs.getString("type"));
                reminder.setDateTime(rs.getString("date_time"));
                list.add(reminder);
            }
        return list;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public Vector<ReminderBean> getAllReminders(int id) {
                 Vector<ReminderBean> list = new Vector<ReminderBean>();
       
        ResultSet rs =null;
        try
        {
            con = DBConnection.createConnection();
            statement = con.createStatement();

             rs=statement.executeQuery("select * from reminders "
                     + "left join users on users.id = created_by "
                     + "where type = 'reminder' and reminders.user_id = "+id+ " order by date_time DESC");
            while(rs.next())
            {
                ReminderBean reminder = new ReminderBean();
                
                UserBean user = new UserBean();
                user.setId(Integer.parseInt(rs.getString("created_by")));
                user.setName(rs.getString("name"));
                user.setUserType(rs.getString("user_type"));
                user.setUserID(rs.getString("userID"));
                
                reminder.setCreatedBy(user);
                
                reminder.setMessage(rs.getString("msg"));
                reminder.setType(rs.getString("type"));
                reminder.setDateTime(rs.getString("date_time"));
                list.add(reminder);
            }
        return list;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public Vector<ReminderBean> getAllNewsletters(int id) {
        Vector<ReminderBean> list = new Vector<ReminderBean>();
       
        ResultSet rs =null;
        try
        {
            con = DBConnection.createConnection();
            statement = con.createStatement();

             rs=statement.executeQuery("select * from reminders "
                     + "left join users on users.id = created_by "
                     + "where type='newsletter' and reminders.user_id = "+id);
            while(rs.next())
            {
                ReminderBean reminder = new ReminderBean();
                
                UserBean user = new UserBean();
                user.setId(Integer.parseInt(rs.getString("created_by")));
                user.setName(rs.getString("name"));
                user.setUserType(rs.getString("user_type"));
                user.setUserID(rs.getString("userID"));
                
                reminder.setCreatedBy(user);
                
                reminder.setMessage(rs.getString("msg"));
                reminder.setType(rs.getString("type"));
                reminder.setDateTime(rs.getString("date_time"));
                list.add(reminder);
            }
        return list;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    /**
     *
     * @return
     */
    public Vector<ReminderBean> getAllNewslettersList() {
        Vector<ReminderBean> list = new Vector<ReminderBean>();
       
        ResultSet rs =null;
        try
        {
            con = DBConnection.createConnection();
            statement = con.createStatement();

             rs=statement.executeQuery("select * from reminders "
                     + "left join users on users.id = reminders.user_id "
                     + "where type='newsletter'");
            while(rs.next())
            {
                ReminderBean reminder = new ReminderBean();
                
                UserBean user = new UserBean();
                user.setId(Integer.parseInt(rs.getString("user_id")));
                user.setName(rs.getString("name"));
                user.setUserType(rs.getString("user_type"));
                user.setUserID(rs.getString("userID"));
                
                reminder.setUser(user);
                
                reminder.setMessage(rs.getString("msg"));
                reminder.setType(rs.getString("type"));
                reminder.setDateTime(rs.getString("date_time"));
                list.add(reminder);
            }
        return list;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    
}
