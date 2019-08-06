/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.sql.Date;

/**
 *
 * @author james
 */
public class IssuedResourceBean {
    private int Id;
    private int RequestedUserID;
    private UserBean RequestedBy;
    private ResourceBean Resource;
    private String RequestedStartDate;
    private String RequestedEndDate;
    private String IssuedStartDate;
    private String IssuedEndDate;
    private String ReturnDate;
    private String StatusType;
    private String Status;
    private UserBean IssuedBy;

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
     * @return the RequestedUserID
     */
    public int getRequestedUserID() {
        return RequestedUserID;
    }

    /**
     * @param RequestedUserID the RequestedUserID to set
     */
    public void setRequestedUserID(int RequestedUserID) {
        this.RequestedUserID = RequestedUserID;
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
     * @return the RequestedStartDate
     */
    public String getRequestedStartDate() {
        return RequestedStartDate;
    }

    /**
     * @param RequestedStartDate the RequestedStartDate to set
     */
    public void setRequestedStartDate(String RequestedStartDate) {
        this.RequestedStartDate = RequestedStartDate;
    }

    /**
     * @return the RequestedEndDate
     */
    public String getRequestedEndDate() {
        return RequestedEndDate;
    }

    /**
     * @param RequestedEndDate the RequestedEndDate to set
     */
    public void setRequestedEndDate(String RequestedEndDate) {
        this.RequestedEndDate = RequestedEndDate;
    }

    /**
     * @return the IssuedStartDate
     */
    public String getIssuedStartDate() {
        return IssuedStartDate;
    }

    /**
     * @param IssuedStartDate the IssuedStartDate to set
     */
    public void setIssuedStartDate(String IssuedStartDate) {
        this.IssuedStartDate = IssuedStartDate;
    }

    /**
     * @return the IssuedEndDate
     */
    public String getIssuedEndDate() {
        return IssuedEndDate;
    }

    /**
     * @param IssuedEndDate the IssuedEndDate to set
     */
    public void setIssuedEndDate(String IssuedEndDate) {
        this.IssuedEndDate = IssuedEndDate;
    }

    /**
     * @return the ReturnDate
     */
    public String getReturnDate() {
        return ReturnDate;
    }

    /**
     * @param ReturnDate the ReturnDate to set
     */
    public void setReturnDate(String ReturnDate) {
        this.ReturnDate = ReturnDate;
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
     * @return the IssuedBy
     */
    public UserBean getIssuedBy() {
        return IssuedBy;
    }

    /**
     * @param IssuedBy the IssuedBy to set
     */
    public void setIssuedBy(UserBean IssuedBy) {
        this.IssuedBy = IssuedBy;
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
     * @return the StatusType
     */
    public String getStatusType() {
        return StatusType;
    }

    /**
     * @param StatusType the StatusType to set
     */
    public void setStatusType(String StatusType) {
        this.StatusType = StatusType;
    }
    
}
