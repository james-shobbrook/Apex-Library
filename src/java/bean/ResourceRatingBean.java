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
public class ResourceRatingBean {
    private int ID;
    private ResourceBean Resource;
    private int Rating;
    private UserBean CreatedBy;
    private String Date;

    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @return the Resource
     */
    public ResourceBean getResource() {
        return Resource;
    }

    /**
     * @param Resource the Resource to set
     */
    public void setResource(ResourceBean Resource) {
        this.Resource = Resource;
    }

    /**
     * @return the Rating
     */
    public int getRating() {
        return Rating;
    }

    /**
     * @param Rating the Rating to set
     */
    public void setRating(int Rating) {
        this.Rating = Rating;
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
     * @return the Date
     */
    public String getDate() {
        return Date;
    }

    /**
     * @param Date the Date to set
     */
    public void setDate(String Date) {
        this.Date = Date;
    }
}
