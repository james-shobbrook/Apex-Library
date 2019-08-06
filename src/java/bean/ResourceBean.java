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
public class ResourceBean {
    private int Id;
    private String Name;
    private String Type;
    private String Category;
    private float Rating;
    private UserBean user;
    private int Status; // 0 for Borrowed 1 for Availaible and 2 for On-Site
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
     * @return the Category
     */
    public String getCategory() {
        return Category;
    }

    /**
     * @param Category the Category to set
     */
    public void setCategory(String Category) {
        this.Category = Category;
    }

    /**
     * @return the Rating
     */
    public float getRating() {
        return Rating;
    }

    /**
     * @param Rating the Rating to set
     */
    public void setRating(float Rating) {
        this.Rating = Rating;
    }

    /**
     * @return the user
     */
    public UserBean getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(UserBean user) {
        this.user = user;
    }

    /**
     * @return the Status
     * 0 for Borrowed
     * 1 for Availaible
     * 2 for On-Site
     */
    public int getStatus() {
        return Status;
    }

    /**
     * @param Status the Status to set
     * 0 for Borrowed
     * 1 for Availaible
     * 2 for On-Site
     */
    public void setStatus(int Status) {
        this.Status = Status;
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
