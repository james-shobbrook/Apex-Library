<%-- 
    Document   : myRequested
    Created on : Jul 17, 2019, 8:05:46 PM
    Author     : james
--%>

<div class="st-content">
  <!-- extra div  -->
  <div class="st-content-inner padding-none">

    <div class="container-fluid">

      <div class="page-section">
        <h1 class="text-display-1 margin-none">All Issued</h1>
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
                          <td class="text-capitalize">${rsrc.getRequestedStartDate() } - ${rsrc.getRequestedEndDate()}</td>
                          <td class="text-capitalize">${rsrc.getIssuedStartDate() == null ? "Pending" : rsrc.getIssuedStartDate()} - ${rsrc.getIssuedEndDate()!=null ? rsrc.getIssuedEndDate()  : "Pending"}</td>
                          <td class="text-capitalize">${rsrc.getStatusType()} </td>
                          <td class="text-capitalize">${rsrc.getStatus()} </td>
                          <td class="text-capitalize">
                              <c:choose>
                                  <c:when test="${rsrc.getIssuedStartDate() == null}">
                                      -----------
                                  </c:when>
                                  <c:when test="${rsrc.getReturnDate() != null}">
                                      Return Date - ${rsrc.getReturnDate()}
                                  </c:when>
                                  <c:otherwise>
                                    <button type='button' data-toggle="modal" class="btn btn-xs issuedModal" data-id="${rsrc.getResource().getId()}_${rsrc.getId()}_${rsrc.getIssuedStartDate()}_${rsrc.getIssuedEndDate()}" data-target="#myModal" >Return Book</button>
                                      /
                                    <button type='button' data-toggle="modal" class="btn btn-xs issuedModal" data-id="${rsrc.getResource().getId()}_${rsrc.getId()}" data-target="#myModal"  >Request for Extension</button>
                                  </c:otherwise>
                              </c:choose>
                              
                          </td>
                      </tr>
                    </c:forEach>
                  </tbody>
              </table>
          </div>
      </div>
  </div>
</div>
                    
                                        
<%-- 
   Approving or Changing status for Resource
        --%>
   
<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title"></h4>
        <p class="alert-danger penalty" style="display:none">You have <span class="penalty_value">${penalty.getValue()}</span> Pence Penalty, for <span class="penalty_days">${penalty_days}</span> days.</p
      </div>
     <form action="${pageContext.request.contextPath}/user/" name="issuedextension" method="post">
      <div class="modal-body">
            <input type="hidden" name="resource_id"  value="">
           <input type="hidden" name="issued_id" value="">
            <div class="issued_extension row" style="display: none">

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
        