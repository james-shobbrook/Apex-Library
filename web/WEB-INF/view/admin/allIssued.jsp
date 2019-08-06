<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : allResources
    Created on : Jul 17, 2019, 2:41:59 PM
    Author     : james
--%>
<div class="st-content">
  <!-- extra div -->
  <div class="st-content-inner padding-none">

    <div class="container-fluid">

      <div class="page-section">
        <h1 class="text-display-1 margin-none">All Requests</h1>
      </div>
         <div style = "color:red">
            <%=(request.getAttribute("message") == null) ? "" : request.getAttribute("message")%>
        </div>
    </div>
      <div class="tab-content">
          <div id="account" class="tab-pane active">
              <table class="table table-hover table-striped">
                  <thead>
                      <tr>
                          <th>Sr#</th>
                          <th>Book Name</th>
                          <th>Type</th>
                          <th>Category</th>
                          <th>User Name - ID</th>
                          <th>Requested Dates</th>
                          <th>Approved Dates</th>
                          <th>Requested Status</th>
                          <th>Current Status</th>
                          <th>Action</th>
                      </tr>
                  </thead>
                  <input type="hidden" name="resource_id"
                  <tbody>
                      <% int i=1; %>
                    <c:forEach var="rsrc" items="${IssuedResourceBean}">
                      <tr>
                          <td><%=i++%></td>
                          <td class="text-capitalize">${rsrc.getResource().getName()}</td>
                          <td class="text-capitalize">${rsrc.getResource().getType()}</td>
                          <td class="text-capitalize">${rsrc.getResource().getCategory()}</td>
                          <td class="text-capitalize">${rsrc.getRequestedBy().getName()} - ${rsrc.getRequestedBy().getUserID()}</td>
                          <td class="text-capitalize">${rsrc.getRequestedStartDate() } - ${rsrc.getRequestedEndDate()}</td>
                          <td class="text-capitalize">${rsrc.getIssuedStartDate() == null ? "Pending" : rsrc.getIssuedStartDate()} - ${rsrc.getIssuedEndDate()!=null ? rsrc.getIssuedEndDate()  : "Pending"}</td>
                          <td class="text-capitalize">${rsrc.getStatusType()} </td>
                          <td class="text-capitalize">${rsrc.getStatus()} </td>
                          <td class="text-capitalize"><a href="${pageContext.request.contextPath}/admin/sendReminder?id=${rsrc.getRequestedBy().getId()}" class="btn btn-primary btn-xs">Send Reminder</a></td>
                      </tr>
                    </c:forEach>
                  </tbody>
              </table>
          </div>
      </div>
  </div>
</div>