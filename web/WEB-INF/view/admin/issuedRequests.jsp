<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : allResources
    Created on : Jul 17, 2019, 2:41:59 PM
    Author     : james
--%>
<div class="st-content">
  <!-- extra div u -->
  <div class="st-content-inner padding-none">

    <div class="container-fluid">

      <div class="page-section">
        <h1 class="text-display-1 margin-none">All Requests</h1>
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
                          <th>Status Type</th>
                          <th>Status</th>
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
                          <td class="text-capitalize"><button data-toggle="modal" class="btn btn-xs openModal" data-id="${rsrc.getResource().getId()}_${rsrc.getId()}_${rsrc.getRequestedStartDate()}_${rsrc.getRequestedEndDate()}" data-target="#myModal" >Take Action</button></td>
                      </tr>
                    </c:forEach>
                  </tbody>
              </table>
          </div>
      </div>
  </div>
</div>

                    
<%-- 
    Approving or Changing status for a Resource
        --%>
   
<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title"></h4>
      </div>
     <form action="${pageContext.request.contextPath}/admin/UpdateissuedResource" name="issuedrequests" method="post">
      <div class="modal-body">
            <input type="hidden" name="resource_id"  value="">
           <input type="hidden" name="issued_id" value="">
            <div class="form-group">
                <input type="radio" name="status" value="approve" />Accept
                <input type="radio" name="status" value="disapprove" /> Decline
            </div>
            <div class="approve_status row" style="display: none">

            </div>
          
      </div>
      <div class="modal-footer">
        <input type="submit" value="Submit" class="btn btn-success pull-right">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
     </form>
    </div>
  </div>
</div>
        