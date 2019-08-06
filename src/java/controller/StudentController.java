/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.ExtendRequestResourcesBean;
import bean.IssuedResourceBean;
import bean.ReminderBean;
import bean.RequestNewResourceBean;
import bean.ResourceBean;
import bean.ResourceRatingBean;
import bean.UserBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.ExtendRequestResourcesDao;
import models.IssuedResourceDao;
import models.ReminderDao;
import models.RequestNewResourceDao;
import models.ResourceDao;
import models.ResourceRatingDao;
import util.General;

/**
 *
 * @author james
 */
@WebServlet(name="StudentController", 
        loadOnStartup = 1,
        urlPatterns={"/user/dashboard","/user/allResources",
            "/user/borrowRequest","/user/myRequests","/user/remindersNewsletters",
              "/user/ReturnIssued","/user/RequestNewResource"})

public class StudentController extends HttpServlet {

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
        if(!General.Checklogin(request,response,"user"))
        {
            return;
        }

        String userPath = request.getServletPath();
        
        if(userPath.equals("/user/dashboard")){
            userPath = "student/dashboard";
        }
        else if(userPath.equals("/user/allResources")){
            userPath = "/student/allResources";
            ResourceDao rd = new ResourceDao();
            Vector<ResourceBean> rsrc = rd.getAllResourcesBean();
            request.setAttribute("ResourceBean", rsrc);            
        }
        else if(userPath.equals("/user/borrowRequest")){
            userPath = "/student/borrowRequest";
            int id =Integer.parseInt(request.getParameter("id"));
            ResourceDao rd = new ResourceDao();
            ResourceBean rsrc = rd.getByID(id);
            request.setAttribute("ResourceBean", rsrc);            
        }
        else if(userPath.equals("/user/myRequests")){
            userPath = "/student/myRequested";
            IssuedResourceDao ird = new IssuedResourceDao();
            int id =Integer.parseInt(request.getSession(false).getAttribute("UserID").toString());
            Vector<IssuedResourceBean> list = ird.getUserIssuedRequests(id,request.getSession(false).getAttribute("User").toString());
            request.setAttribute("IssuedResourceBean", list);            
        }
        else if(userPath.equals("/user/remindersNewsletters")){
            userPath = "/student/reminderNewsletters";
            ReminderDao rmnd = new ReminderDao();
            Vector<ReminderBean> reminder = rmnd.getAllReminders(Integer.parseInt(request.getSession(false).getAttribute("UserID").toString()));
            Vector<ReminderBean> newsletters = rmnd.getAllNewsletters(Integer.parseInt(request.getSession(false).getAttribute("UserID").toString()));

            request.setAttribute("reminders", reminder);
            request.setAttribute("newsletters", newsletters);
            
        }
        else if(userPath.equals("/user/RequestNewResource")){
            RequestNewResourceDao rnd = new RequestNewResourceDao();
            Vector<RequestNewResourceBean> list = rnd.getAllResourcesBean();
                request.setAttribute("RequestNewResourceBean", list);
                userPath = "/student/newRequestResource";    
        }
        
        else if(userPath.equals("/user/ExtensionRequests")){
                loadExtendRequestResourcePage(request, response);
            return;
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
          String userPath = request.getServletPath();
    
        if(!General.Checklogin(request,response,"user"))
        {
            return;
        }
        if(userPath.equals("/user/borrowRequest")){
             if(addRequest(request, response) == false){
                  request.setAttribute("message", "Requested Successfully!");
             }
              else
                request.setAttribute("message", "Ops Something went wrong!!");
           userPath = "/student/borrowRequest";            
        }
        else if(userPath.equals("/user/ReturnIssued")){
             ReturnIssued(request, response);
                  request.setAttribute("message", "Return Successfully!");
            response.sendRedirect(request.getHeader("Referer"));
        }
        else if(userPath.equals("/user/IssuedExtension")){
             String msg = IssuedExtension(request, response);
             request.setAttribute("message", msg);
             userPath = "/student/RequestExtension";
             loadExtendRequestResourcePage(request,response);
             return;
             
        }
        else if(userPath.equals("/user/RequestNewResource")){
            RequestNewResourceBean rsrc = new RequestNewResourceBean();
            rsrc.setName(request.getParameter("name"));
            rsrc.setType(request.getParameter("type"));
            rsrc.setCategory(request.getParameter("category"));
            UserBean user;
            user = new UserBean(request.getSession(false).getAttribute("UserName").toString(),
                    request.getSession(false).getAttribute("UserDocID").toString(), request.getSession(false).getAttribute("User").toString(), 
                    Integer.parseInt(request.getSession(false).getAttribute("UserID").toString()));
            rsrc.setCreatedBy(user);
            RequestNewResourceDao rsrcDao = new RequestNewResourceDao();
            boolean inserted = rsrcDao.addNewResource(rsrc);
            if(inserted==false)
                request.setAttribute("message", "Added Successfully!");
            else
                    request.setAttribute("message", "Ops Something went wrong!!");
            
            request.setAttribute("rsrc", rsrc);
            request.getRequestDispatcher(General.ViewURL+"/student/newRequestResource.jsp").forward(request, response);
            return; 
        }
        String url = General.ViewURL + userPath + ".jsp";
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        processRequest(request, response);
    }
    
    /**
     * Handles the HTTP Request method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private boolean addRequest(HttpServletRequest request, HttpServletResponse response){
        IssuedResourceBean irb = new IssuedResourceBean();
        IssuedResourceDao ird = new IssuedResourceDao();
        UserBean user;
            user = new UserBean(request.getSession(false).getAttribute("UserName").toString(),
                    request.getSession(false).getAttribute("UserDocID").toString(), request.getSession(false).getAttribute("User").toString(), 
                    Integer.parseInt(request.getSession(false).getAttribute("UserID").toString()));
        irb.setRequestedBy(user);
        irb.setStatusType(request.getParameter("status_type"));
        irb.setRequestedStartDate(request.getParameter("from_date"));
        irb.setRequestedEndDate(request.getParameter("to_date"));
        irb.setResource(new ResourceDao().getByID(Integer.parseInt(request.getParameter("resource_id"))));
        return ird.addRequest(irb);
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

    private boolean ReturnIssued(HttpServletRequest request, HttpServletResponse response) {
        IssuedResourceBean irb = new IssuedResourceBean();
        IssuedResourceDao ird = new IssuedResourceDao();
        irb.setStatusType("returned");
        irb.setId(Integer.parseInt(request.getParameter("issued_id")));
        ResourceRatingBean rating = new ResourceRatingBean();
        ResourceRatingDao rd = new ResourceRatingDao();
        ResourceBean rb = new ResourceBean();
        ResourceDao rsrcd = new ResourceDao();
        rb.setId(Integer.parseInt(request.getParameter("resource_id")));
        
        UserBean user = new UserBean();
        user.setId(Integer.parseInt(request.getSession(false).getAttribute("UserID").toString()));
        rating.setRating(Integer.parseInt(request.getParameter("rating")));
        rating.setResource(rb);
        rating.setCreatedBy(user);
        rd.addRating(rating);
        rsrcd.addRating(rd.getResourceRating(rating.getResource().getId()),rating.getResource().getId());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        
        irb.setReturnDate(dateFormat.format(date));
        irb.setResource(rb);
        return ird.ReturnRequest(irb);
    }

    private String IssuedExtension(HttpServletRequest request, HttpServletResponse response) {
        IssuedResourceBean irb = new IssuedResourceBean();
        ExtendRequestResourcesBean erb = new ExtendRequestResourcesBean();
        ExtendRequestResourcesDao erd = new ExtendRequestResourcesDao();
        
        UserBean user;
            user = new UserBean(request.getSession(false).getAttribute("UserName").toString(),
                    request.getSession(false).getAttribute("UserDocID").toString(), request.getSession(false).getAttribute("User").toString(), 
                    Integer.parseInt(request.getSession(false).getAttribute("UserID").toString()));
        erb.setRequestedBy(user);
//        erb.setStatus(request.getParameter("status_type"));
        irb.setId(Integer.parseInt(request.getParameter("issued_id")));
        erb.setFromDate(request.getParameter("from_date"));
        erb.setToDate(request.getParameter("to_date"));
        erb.setIssuedResources(irb);
        
        irb.setResource(new ResourceDao().getByID(Integer.parseInt(request.getParameter("resource_id"))));
        return erd.addRequest(erb);
    }

    private void loadExtendRequestResourcePage(HttpServletRequest request, HttpServletResponse response) {
        ExtendRequestResourcesDao erd = new ExtendRequestResourcesDao();
        int session_user_id = Integer.parseInt(request.getSession(false).getAttribute("UserID").toString());
        Vector<ExtendRequestResourcesBean> erb = erd.getUserRequest(session_user_id);
        request.setAttribute("extendResourceBean", erb);
        String url = General.ViewURL +  "student/RequestExtension.jsp";
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

