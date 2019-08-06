<%-- 
    Document   : AllPenalities
    Created on : Jul 17, 2019, 6:25:28 PM
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="st-content">
  
  <div class="st-content-inner padding-none">

    <div class="container-fluid">

      <div class="page-section">
        <h1 class="text-display-1 margin-none">All Penalties</h1>
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
                          <th>Penalty</th>
                          <th>Action</th>
                      </tr>
                  </thead>
                  <input type="hidden" name="resource_id"
                  <tbody>
                      <% int i=1; %>
                    <c:forEach var="rsrc" items="${Penalties}">
                      <tr>
                          <td><%=i++%></td>
                          <td class="text-capitalize">${rsrc.getIssuedResource().getResource().getName()}</td>
                          <td class="text-capitalize">${rsrc.getIssuedResource().getResource().getType()}</td>
                          <td class="text-capitalize">${rsrc.getIssuedResource().getResource().getCategory()}</td>
                          <td class="text-capitalize">${rsrc.getIssuedResource().getRequestedBy().getName()} - ${rsrc.getIssuedResource().getRequestedBy().getUserID()}</td>
                          <td class="text-capitalize">${rsrc.getIssuedResource().getRequestedStartDate() } - ${rsrc.getIssuedResource().getRequestedEndDate()}</td>
                          <td class="text-capitalize">${rsrc.getIssuedResource().getIssuedStartDate()} - ${rsrc.getIssuedResource().getIssuedEndDate()}</td>
                          <td class="text-capitalize">${rsrc.getIssuedResource().getStatusType()} </td>
                          <td class="text-capitalize">${rsrc.getIssuedResource().getStatus()} </td>
                          <td class="text-capitalize">${rsrc.getValue()} </td>
                          <td class="text-capitalize"><a href="${pageContext.request.contextPath}/admin/sendReminder?id=${rsrc.getIssuedResource().getRequestedBy().getId()}" class="btn btn-primary btn-xs">Send Reminder</a></td>
                      </tr>
                    </c:forEach>
                  </tbody>
              </table>
          </div>
      </div>
  </div>
</div>