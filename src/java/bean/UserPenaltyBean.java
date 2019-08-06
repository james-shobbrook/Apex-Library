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
public class UserPenaltyBean {
    private IssuedResourceBean IssuedResource;
    private String PenaltyType;
    private String Value;

    /**
     * @return the IssuedResource
     */
    public IssuedResourceBean getIssuedResource() {
        return IssuedResource;
    }

    /**
     * @param IssuedResource the IssuedResource to set
     */
    public void setIssuedResource(IssuedResourceBean IssuedResource) {
        this.IssuedResource = IssuedResource;
    }

    /**
     * @return the PenaltyType
     */
    public String getPenaltyType() {
        return PenaltyType;
    }

    /**
     * @param PenaltyType the PenaltyType to set
     */
    public void setPenaltyType(String PenaltyType) {
        this.PenaltyType = PenaltyType;
    }

    /**
     * @return the Value
     */
    public String getValue() {
        return Value;
    }

    /**
     * @param Value the Value to set
     */
    public void setValue(String Value) {
        this.Value = Value;
    }
    
}
