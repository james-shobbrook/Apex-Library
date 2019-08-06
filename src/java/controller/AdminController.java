/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import models.IssuedResourceDao;
import models.*;
import util.General;




/**
 *
 * @author james
 */
@WebServlet(name="LoginController", 
        loadOnStartup = 1,
        urlPatterns={"/admin/dashboard","/admin/addNewResource","/admin/checkAvailability",
        "/admin/sendReminder"})
public class AdminController extends HttpServlet {

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
            
        String userPath = request.getServletPath();
        System.out.println("Enterd in Admin"+ userPath);
        // if category page is requested
//        HttpSession session = request.getSession(false);

        // if session doesn't exist, forward user to welcome page
//        if (session == null) {
//            try {
//                response.sendRedirect("login");
//            }catch(Exception e){
//                e.getLocalizedMessage();
//            }
//        }
//        else if (userPath.equals("/admin/dashboard")) {
//          
            if(userPath.equals("/admin/addNewResource")){
                userPath = "/admin/newResource"  ;         
            }else
            userPath = "/admin/dashboard";
//        }
        String url = General.ViewURL + userPath + ".jsp";
         try {
            request.getRequestDispatcher(url).forward(request, response);
            return;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        processRequest(request, response);
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
            if(!General.Checklogin(request,response,"admin"))
            {
                return;
            }
        String userPath = request.getServletPath();
        if(userPath.equals("/admin/addNewResource")){
            userPath = "/admin/newResource";
        }
        else if(userPath.equals("/admin/AllResources")){
            userPath = "/admin/allResources";
            ResourceDao rd = new ResourceDao();
            Vector<ResourceBean> rsrc = rd.getAllResourcesBean();
            request.setAttribute("ResourceBean", rsrc);
        }
        else if(userPath.equals("/admin/sendReminder")){
            userPath = "/admin/allResources";
            SendReminder(request, response);
            
        }
        else if(userPath.equals("/admin/issuedRequests")){
            userPath = "/admin/issuedRequests";
            ResourceDao rd = new ResourceDao();
            IssuedResourceDao ird = new IssuedResourceDao();
            int id =Integer.parseInt(request.getSession(false).getAttribute("UserID").toString());
            Vector<IssuedResourceBean> list = ird.getUserIssuedRequests(id,request.getSession(false).getAttribute("User").toString());
            request.setAttribute("IssuedResourceBean", list);            

        }
        else if(userPath.equals("/admin/issuedResources")){
            userPath = "/admin/allIssued";
            ResourceDao rd = new ResourceDao();
            IssuedResourceDao ird = new IssuedResourceDao();
            Vector<IssuedResourceBean> list = ird.getAllIssuedResources();
            request.setAttribute("IssuedResourceBean", list);            

        }
        else if(userPath.equals("/admin/checkAvailability")){
            
            ResourceDao rd = new ResourceDao();
            IssuedResourceDao ird = new IssuedResourceDao();
             int resource_id = Integer.parseInt(request.getParameter("resource_id"));
            int issued_id = Integer.parseInt(request.getParameter("issued_id"));
            String start_date = request.getParameter("from_date");
            String end_date = request.getParameter("to_date");
            boolean s = ird.CheckResourceAvailaiblity(start_date, end_date,resource_id);
            String msg ="";
            if(s)
                msg="Good to go!";
            else
                msg="Selected Resource is not availainle in selected days";
            response.getWriter().write(msg);
            return;


        }
        else if(userPath.equals("/admin/requestedExtension")){
            
            userPath = "/admin/RequestExtension";
           RequestExtension(request,response);            
        }
        else if(userPath.equals("/admin/AllPenalities")){            
            userPath = "/admin/AllPenalities";
            AllPenalties(request,response);            
        }
        else if(userPath.equals("/admin/requestedNewResource")){
            RequestNewResourceDao rnd = new RequestNewResourceDao();
            if(request.getParameter("id")!=null){
             RequestNewResourceBean rnb = new RequestNewResourceBean();
                rnb.setId(Integer.parseInt(request.getParameter("id")));
                rnb.setStatus(request.getParameter("status"));
                rnd.Purchased(rnb);
           
            if(request.getParameter("status").equals("purchased")){
                rnb=rnd.getByID(rnb.getId());
                ResourceBean rsrc = new ResourceBean();
                rsrc.setName(rnb.getName());
                rsrc.setType(rnb.getType());
                rsrc.setCategory(rnb.getCategory());
                UserBean user;
                user = new UserBean(request.getSession(false).getAttribute("UserName").toString(),
                        request.getSession(false).getAttribute("UserDocID").toString(), request.getSession(false).getAttribute("User").toString(), 
                        Integer.parseInt(request.getSession(false).getAttribute("UserID").toString()));
                rsrc.setUser(user);
                ResourceDao rsrcDao = new ResourceDao();
                boolean inserted = rsrcDao.addNewResource(rsrc);
            }
                request.setAttribute("Message", "Updated SuccessFully!");
            }
            else
            {
                Vector<RequestNewResourceBean> list = rnd.getAllResourcesBean();
                request.setAttribute("RequestNewResourceBean", list);
                
            }
                userPath = "/admin/newRequestResource";
            
        }
        else if(userPath.equals("/admin/SendNewsLetters")){
            ReminderDao rd = new ReminderDao();
            UserDao ud = new UserDao();
            Vector<UserBean> Users = ud.getAllActiveUsers();
            Vector<ReminderBean> list = rd.getAllNewslettersList();
            request.setAttribute("Users",Users);
            request.setAttribute("NewsLetters",list);
                
                userPath = "/admin/SendnewsLetters";
            
        }
        String url = General.ViewURL + userPath + ".jsp";
         try {
            request.getRequestDispatcher(url).forward(request, response);
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

//        checking login
        
        if(!General.Checklogin(request,response,"admin"))
        {
            return;
        }
        
        String userPath = request.getServletPath().toString();
        if(userPath.equals("/admin/addNewResource")){
            ResourceBean rsrc = new ResourceBean();
            rsrc.setName(request.getParameter("name"));
            rsrc.setType(request.getParameter("type"));
            rsrc.setCategory(request.getParameter("category"));
            UserBean user;
            user = new UserBean(request.getSession(false).getAttribute("UserName").toString(),
                    request.getSession(false).getAttribute("UserDocID").toString(), request.getSession(false).getAttribute("User").toString(), 
                    Integer.parseInt(request.getSession(false).getAttribute("UserID").toString()));
            rsrc.setUser(user);
            ResourceDao rsrcDao = new ResourceDao();
            boolean inserted = rsrcDao.addNewResource(rsrc);
            if(inserted==false)
                request.setAttribute("message", "Added Successfully!");
            else
                    request.setAttribute("message", "Ops Something went wrong!!");
            
            request.setAttribute("rsrc", rsrc);
            
            request.getRequestDispatcher(General.ViewURL+"/admin/newResource.jsp").forward(request, response);

            return; 
        }
        else if(userPath.equals("/admin/UpdateissuedResource")){
            boolean flag =false;
            String msg=null;
      
            IssuedResourceDao ird = new IssuedResourceDao();
            String status = request.getParameter("status").toString();
            int resource_id = Integer.parseInt(request.getParameter("resource_id"));
            int issued_id = Integer.parseInt(request.getParameter("issued_id"));
            if(status.equals("approve")){
                String start_date = request.getParameter("from_date");
                String end_date = request.getParameter("to_date");
                IssuedResourceBean irb = new IssuedResourceBean();
                irb.setIssuedStartDate(start_date);
                irb.setIssuedEndDate(end_date);
                ResourceBean rb = new ResourceBean();
                rb.setId(resource_id);
                irb.setStatus("approve");
                irb.setResource(rb);
                irb.setId(issued_id);
                int issued_by = Integer.parseInt(request.getSession(false).getAttribute("UserID").toString());
                flag =false;
                flag = ird.UpdateRequestedIssuedRequest(irb, issued_by);
            }else{
                IssuedResourceBean irb = new IssuedResourceBean();
               
                ResourceBean rb = new ResourceBean();
                rb.setId(resource_id);
                irb.setResource(rb);
                irb.setStatus("declined");
                irb.setId(issued_id);
                int issued_by = Integer.parseInt(request.getSession(false).getAttribute("UserID").toString());
                flag = ird.UpdateRequestedIssuedRequest(irb, issued_by);
            }
                    
            response.sendRedirect(request.getHeader("Referer"));
//            request.setAttribute("IssuedResourceBean", list)
        }
        else if(userPath.equals("/admin/SendNewsLetters")){
               ReminderDao rd = new ReminderDao();
               String s[] =request.getParameterValues("user_id");
                if(request.getParameter("newsletter_text")!=null){               
                    String newsletterText = request.getParameter("newsletter_text");
                    
                    for (String usrId : request.getParameterValues("user_id")) {

                    ReminderBean rmndr = new ReminderBean();
                    UserBean user = new UserBean();
                    user.setId(Integer.parseInt(request.getSession(false).getAttribute("UserID").toString()));
                    rmndr.setCreatedBy(user);
                    rmndr.setMessage(newsletterText);

                    UserBean sentTo = new UserBean();
                    sentTo.setId(Integer.parseInt(usrId));
                    rmndr.setUser(sentTo);
                     rmndr.setType("newsletter");
                    String msg = rd.sendReminder(rmndr);
                    }
                    request.setAttribute("Message", "Updated SuccessFully!");
                }

                userPath = "/admin/SendnewsLetters";
                response.sendRedirect(request.getHeader("Referer"));
            
        }
//        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void SendReminder(HttpServletRequest request, HttpServletResponse response) {
        ReminderDao reminder = new ReminderDao();
            ReminderBean rmndr = new ReminderBean();
            UserBean user = new UserBean();
            user.setId(Integer.parseInt(request.getParameter("id")));
            rmndr.setUser(user);
            rmndr.setMessage("This Message is to Inform you that one of your resources is going to expire or has Already Expired!"
                    + "\nPlease Check it and return it if needed to avoid Penalities");
            rmndr.setType("reminder");
            System.out.println("MID");

            UserBean created_by = new UserBean();
            created_by.setId(Integer.parseInt(request.getSession(false).getAttribute("UserID").toString()));

            rmndr.setCreatedBy(created_by);
            String msg = reminder.sendReminder(rmndr);
        
        request.setAttribute("Message", msg);
         try {
                response.sendRedirect(request.getHeader("Referer"));
            } catch (IOException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }

    private void RequestExtension(HttpServletRequest request, HttpServletResponse response) {
        if(request.getParameter("id")!=null){
            int id = Integer.parseInt(request.getParameter("id"));
            String status = request.getParameter("status");
            ExtendRequestResourcesBean erb = new ExtendRequestResourcesBean();
            erb.setId(id);
            IssuedResourceBean irb = new IssuedResourceBean();
            irb.setId(Integer.parseInt(request.getParameter("resource_id")));
            erb.setIssuedResources(irb);
            erb.setStatus(status);
            UserBean user = new UserBean();
            user.setId(Integer.parseInt(request.getSession(false).getAttribute("UserID").toString()));
            ExtendRequestResourcesDao erd = new ExtendRequestResourcesDao();
            erb.setApprovedBy(user);
            String message = erd.setStatus(erb);
            request.setAttribute("message", message);
            try {
                response.sendRedirect(request.getHeader("Referer"));
            } catch (IOException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }

        
        }else
        {
            ExtendRequestResourcesDao erd = new ExtendRequestResourcesDao();
            Vector<ExtendRequestResourcesBean> erb = erd.getAllRequest();
            request.setAttribute("extendResourceBean", erb);
//          request.setAtr
        }
    }
    
    private void AllPenalties(HttpServletRequest request, HttpServletResponse response){
        Vector<UserPenaltyBean> list =new Vector<UserPenaltyBean>();
        UserPenaltyDao upd = new UserPenaltyDao();
        list = upd.getAllPenalties();
        request.setAttribute("Penalties", list);
    }

    

}
