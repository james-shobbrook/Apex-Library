/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.LoginBean;
import bean.ReminderBean;
import bean.UserBean;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import models.LoginDao;
import models.ReminderDao;
import util.General;
/**
 *
 * @author james
 */
@WebServlet(name="LoginController", 
        loadOnStartup = 1,
        urlPatterns={"/login", "/logout"})

public class LoginController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        System.out.println("entered");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet LoginController</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet LoginController at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();
       
        if (userPath.equals("/logout")) {
            this.Logout(request, response);
            userPath = "/login";
        } else if (userPath.equals("/login")) {   
            if(request.getSession().getAttribute("User")!=null){
                userPath="/"+request.getSession().getAttribute("User")+"/dashboard";
            }else
                userPath = "/login";
        }
        // use RequestDispatcher to forward request internally
        String url = General.ViewURL + userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
            return;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
//        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        
        LoginBean loginBean = new LoginBean();
        loginBean.setUserName(userName);
        loginBean.setPassword(password);

        
        LoginDao loginDao = new LoginDao();
        try
        {
            UserBean userValidate = loginDao.authenticateUser(loginBean);
            System.out.println(userValidate == null ? "false" : "true");
            if(userValidate == null || userValidate.getUserType() == null){
                System.out.println("Error message = "+userValidate);
                request.setAttribute("errMessage", "Invalid Credentials!");
                request.getRequestDispatcher(General.ViewURL+"login.jsp").forward(request, response);
                return;
            }
            if(userValidate.getUserType().equals("admin")) // For Admin
            {
                System.out.println("Admin's Home");
                HttpSession session = request.getSession(); //Creating a session
                session.setAttribute("User",userValidate.getUserType()); //setting session attribute
                session.setAttribute("UserName",userValidate.getName()); //setting session attribute
                session.setAttribute("UserID",userValidate.getId());
                session.setAttribute("UserDocID",userValidate.getUserID());
                response.sendRedirect("admin/dashboard");
                return;
//                request.getRequestDispatcher(General.ViewURL+"admin/dashboard.jsp").forward(request, response);
            }
            else if(userValidate.getUserType().equals("user")) //For Student or User
            {
                System.out.println("User's Home");
                 HttpSession session = request.getSession();
                session.setAttribute("User",userValidate.getUserType()); //setting session attribute
                session.setAttribute("UserName",userValidate.getName()); //setting session attribute
                session.setAttribute("UserID",userValidate.getId());
                session.setAttribute("UserDocID",userValidate.getUserID());
                ReminderDao rmnd = new ReminderDao();
                Vector<ReminderBean> reminder = rmnd.getAllUnread(Integer.parseInt(request.getSession(false).getAttribute("UserID").toString()));
                session.setAttribute("notifications",reminder);

                response.sendRedirect("user/dashboard");
                
                return;
            }
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage());
        }
        processRequest(request, response);
    }

    private void Logout(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(false); //Fetch session object
        if(session!=null) //If session is not null
        {
            session.invalidate(); //removes all session attributes bound to the session
            request.setAttribute("errMessage", "You have logged out successfully");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(General.ViewURL+"login.jsp");
            try {
                requestDispatcher.forward(request, response);
            } catch (ServletException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Logged out");
        }
    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Login Controller";
    }// </editor-fold>

}
