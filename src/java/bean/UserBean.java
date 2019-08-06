/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author james
 */
public class UserBean {
    private String Name;
    private String UserID;
    private String UserType;
    private int Id;

       
    /**
     * Custom Constructor
     * Receive Parameters
     * @param name
     * @param userid
     * @param user_type
     * @param id
     */
    public UserBean(String name,String userid,String user_type,int id) {
        this.Name=name;this.UserID=userid;this.UserType = user_type;this.Id = id;
    }

    /**
     *
     */
    public UserBean() {
        this.Name=null;this.UserID=null;this.UserType = null;this.Id = 0;
    }
    
    /**
     * @return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * @return the UserID
     */
    public String getUserID() {
        return UserID;
    }

    /**
     * @param UserID the UserID to set
     */
    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    /**
     * @return the UserType
     */
    public String getUserType() {
        return UserType;
    }

    /**
     * @param UserType the UserType to set
     */
    public void setUserType(String UserType) {
        this.UserType = UserType;
    }

    /**
     * @return the Id
     */
    public int getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(int Id) {
        this.Id = Id;
    }
    
    
}
