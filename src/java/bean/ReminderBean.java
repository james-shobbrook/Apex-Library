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
public class ReminderBean {
    private int Id;
    private UserBean User;
    private String Message;
    private String Type;
    private UserBean CreatedBy;
    private String DateTime;
    private int MessageRead;

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

    /**
     * @return the User
     */
    public UserBean getUser() {
        return User;
    }

    /**
     * @param User the User to set
     */
    public void setUser(UserBean User) {
        this.User = User;
    }

    /**
     * @return the Message
     */
    public String getMessage() {
        return Message;
    }

    /**
     * @param Message the Message to set
     */
    public void setMessage(String Message) {
        this.Message = Message;
    }

    /**
     * @return the Type
     */
    public String getType() {
        return Type;
    }

    /**
     * @param Type the Type to set
     */
    public void setType(String Type) {
        this.Type = Type;
    }

    /**
     * @return the CreatedBy
     */
    public UserBean getCreatedBy() {
        return CreatedBy;
    }

    /**
     * @param CreatedBy the CreatedBy to set
     */
    public void setCreatedBy(UserBean CreatedBy) {
        this.CreatedBy = CreatedBy;
    }

    /**
     * @return the DateTime
     */
    public String getDateTime() {
        return DateTime;
    }

    /**
     * @param DateTime the DateTime to set
     */
    public void setDateTime(String DateTime) {
        this.DateTime = DateTime;
    }

    /**
     * @return the MessageRead
     */
    public int getMessageRead() {
        return MessageRead;
    }

    /**
     * @param MessageRead the MessageRead to set
     */
    public void setMessageRead(int MessageRead) {
        this.MessageRead = MessageRead;
    }
    
    
    
}
