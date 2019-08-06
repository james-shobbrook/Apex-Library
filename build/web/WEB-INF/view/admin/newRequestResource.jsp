<%-- 
    Document   : newResource
    Created on : Jul 17, 2019, 2:09:55 AM
    Author     : james
--%>
<div class="st-content">
  <!-- extra div for emulating position:fixed of the menu -->
  <div class="st-content-inner padding-none">

    <div class="container-fluid">

      <div class="page-section">
        <h1 class="text-display-1 margin-none">All Resource</h1>
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
                          <th>Created By</th>
                          <th>Status</th>
                          <th>Action</th>
                      </tr>
                  </thead>
                  <tbody>
                      <% int i=1; %>
                    <c:forEach var="rsrc" items="${RequestNewResourceBean}">
                      <tr>
                          <td><%=i%></td>
                          <td class="text-capitalize">${rsrc.getName()}</td>
                          <td class="text-capitalize">${rsrc.getType()}</td>
                          <td class="text-capitalize">${rsrc.getCategory()}</td>
                          <td class="text-capitalize">${rsrc.getCreatedBy().getName()}</td>
                          <td class="text-capitalize">${rsrc.getStatus()}</td>
                          <td class="text-capitalize">
                              <c:choose>
                                  <c:when test="${rsrc.getStatus().equals(\"pending\")}">
                                        <a href="${pageContext.request.contextPath}/admin/requestedNewResource?id=${rsrc.getId()}&status=purchased" class="btn btn-success">Purchased</a> / 
                                        <a href="${pageContext.request.contextPath}/admin/requestedNewResource?id=${rsrc.getId()}&status=reject" class="btn btn-info">Declined</a>
                                  </c:when>
                                  <c:otherwise>
                                  ------------
                                  </c:otherwise>
                              </c:choose>

                          </td>
                          
                      </tr>
                      <% i++; %>
                    </c:forEach>
                  </tbody>
              </table>
          </div>
      </div>
  </div>
</div>
