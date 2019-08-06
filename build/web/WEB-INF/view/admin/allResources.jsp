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
                      </tr>
                  </thead>
                  <tbody>
                    <% int i=1;%>
                    <c:forEach var="rsrc" items="${ResourceBean}">
                      <tr>
                          <td><%=i%></td>
                          <td class="text-capitalize">${rsrc.getName()}</td>
                          <td class="text-capitalize">${rsrc.getType()}</td>
                          <td class="text-capitalize">${rsrc.getCategory()}</td>
                          <td class="text-capitalize">${rsrc.getUser().getName()}</td>
                      </tr>
                     <% i++; %>
                    </c:forEach>
                  </tbody>
              </table>
          </div>
      </div>
  </div>
</div>
   