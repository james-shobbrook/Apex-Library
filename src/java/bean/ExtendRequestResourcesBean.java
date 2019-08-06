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
public class ExtendRequestResourcesBean {
    private int id;
    private IssuedResourceBean IssuedResources;
    private UserBean RequestedBy;
    private UserBean ApprovedBy;
    private String FromDate;
    private String ToDate;
    private String Status;

    /**
     * @return the IssuedResources
     */
    public IssuedResourceBean getIssuedResources() {
        return IssuedResources;
    }

    /**
     * @param IssuedResources the IssuedResources to set
     */
    public void setIssuedResources(IssuedResourceBean IssuedResources) {
        this.IssuedResources = IssuedResources;
    }

    /**
     * @return the RequestedBy
     */
    public UserBean getRequestedBy() {
        return RequestedBy;
    }

    /**
     * @param RequestedBy the RequestedBy to set
     */
    public void setRequestedBy(UserBean RequestedBy) {
        this.RequestedBy = RequestedBy;
    }

    /**
     * @return the FromDate
     */
    public String getFromDate() {
        return FromDate;
    }

    /**
     * @param FromDate the FromDate to set
     */
    public void setFromDate(String FromDate) {
        this.FromDate = FromDate;
    }

    /**
     * @return the ToDate
     */
    public String getToDate() {
        return ToDate;
    }

    /**
     * @param ToDate the ToDate to set
     */
    public void setToDate(String ToDate) {
        this.ToDate = ToDate;
    }

    /**
     * @return the Status
     */
    public String getStatus() {
        return Status;
    }

    /**
     * @param Status the Status to set
     */
    public void setStatus(String Status) {
        this.Status = Status;
    }

    /**
     * @return the ApprovedBy
     */
    public UserBean getApprovedBy() {
        return ApprovedBy;
    }

    /**
     * @param ApprovedBy the ApprovedBy to set
     */
    public void setApprovedBy(UserBean ApprovedBy) {
        this.ApprovedBy = ApprovedBy;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    
}
