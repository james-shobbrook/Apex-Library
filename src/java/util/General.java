/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import controller.AdminController;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author james
 */
public class General {

    /**
     *
     */
    public static String ViewURL = "/WEB-INF/view/";
    private static int PenaltyPerDay = 10;

    /**
     *
     * @param number
     * @param decimalPlace
     * @return
     */
    public static float round(float number, int decimalPlace) {
		BigDecimal bd = new BigDecimal(number);
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd.floatValue();
    }
    
    /**
     *
     * @param request
     * @param response
     * @param role
     * @return
     */
    public static boolean Checklogin(HttpServletRequest request, HttpServletResponse response,String role) {
        if(request.getSession().getAttribute("User")==null){
            try {
                System.out.println("called");
                response.sendRedirect("/LMS/login");
                return false;
            } catch (IOException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(!request.getSession(false).getAttribute("User").equals(role)){
            try {
                response.sendRedirect(request.getHeader("Referer"));
                return false;
            } catch (IOException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getLocalizedMessage());
                return false;
            }
        }
        else if(request.getSession().getAttribute("User").equals(role)){
            return true;
        }
        return false;
    }
    
    /**
     *
     * @param startDate
     * @param EndDate
     * @return
     */
    public static int CalculatePenalty(String startDate, String EndDate) {
//        int days = Days.daysBetween(date1, date2).getDays();

        return 0;
    }
}
