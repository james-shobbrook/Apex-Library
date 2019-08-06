/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import bean.ResourceRatingBean;
import bean.UserPenaltyBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DBConnection;
import util.General;

/**
 *
 * @author james
 */
public class ResourceRatingDao {
          
    Connection con;
    Statement statement;
    boolean flag;
    
    /**
     *
     * @param rating
     */
    public void addRating(ResourceRatingBean rating){
        Connection con = null;
        Statement statement = null;

        try
        {
            con = DBConnection.createConnection();
            statement = con.createStatement();

            statement.execute("INSERT INTO `resource_ratings` (`resource_id`, `rating`, `user_id`) VALUES ("+rating.getResource().getId()+" , "+General.round(rating.getRating(),2)+","+rating.getCreatedBy().getId()+")");
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
    public float getResourceRating(int resource_id){
        Connection con = null;
        Statement statement = null;
        try
        {
            con = DBConnection.createConnection();
            statement = con.createStatement();

            ResultSet rs = statement.executeQuery("SELECT AVG(rating) as rating FROM `resource_ratings` where  resource_id = "+resource_id+" GROUP BY resource_id");
            if(rs.next()){
                return Float.parseFloat(rs.getString("rating"));
            }
            statement.close();
            return 0;
        }   
        catch (SQLException ex) {
            Logger.getLogger(ResourceDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getLocalizedMessage());
            return 0;
        }
    }

}
