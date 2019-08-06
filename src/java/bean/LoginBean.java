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
public class LoginBean {
    private String userName;
    private String password;

    /**
     *
     * @return
     */
    public String getUserName() {
        return userName;
   }

    /**
     *
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
   }

    /**
     *
     * @return
     */
    public String getPassword() {
    return password;
   }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
     this.password = password;
   }
}
