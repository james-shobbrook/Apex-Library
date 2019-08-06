<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : allResources
    Created on : Jul 17, 2019, 2:41:59 PM
    Author     : james
--%>
<div class="st-content">
  <!-- extra div for emulating position:fixed of the menu -->
  <div class="st-content-inner padding-none">

    <div class="container-fluid">

      <div class="page-section">
        <h1 class="text-display-1 margin-none">All Resources</h1>
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
                          <th>Rating</th>
                          <th>Status</th>
                          <th>Action</th>
                      </tr>
                  </thead>
                  <tbody>
                      <% int i=1; %>
                    <c:forEach var="rsrc" items="${ResourceBean}">
                      <tr>
                          <td><%=i++%></td>
                          <td class="text-capitalize">${rsrc.getName()}</td>
                          <td class="text-capitalize">${rsrc.getType()}</td>
                          <td class="text-capitalize">${rsrc.getCategory()}</td>
                          <td class="text-capitalize">${rsrc.getRating()}</td>
                          <td class="text-capitalize">${rsrc.getStatus() == "1" ? "Availaible" : rsrc.getStatus() == "0" ? "Borrowed" : "On-Site"}</td>
                          <td>
                              <c:choose>
                                  <c:when test="${rsrc.getStatus() == 1}">
                                      <a href="${pageContext.request.contextPath}/user/borrowRequest?id=${rsrc.getId()}">Borrow</a>
                                  </c:when>
                                  <c:otherwise>
                                        -
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
   