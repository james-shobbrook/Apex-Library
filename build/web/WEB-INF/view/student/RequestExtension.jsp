<%-- 
    Document   : RequestExtension
    Created on : Jul 17, 2019, 12:33:19 PM
    Author     : james
--%>
<div class="st-content">
  <!-- extra div  -->
  <div class="st-content-inner padding-none">

    <div class="container-fluid">

      <div class="page-section">
        <h1 class="text-display-1 margin-none">Request for Extension</h1>
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
                          <th>Status</th>
                      </tr>
                  </thead>
                  <tbody>
                      <% int i=1; %>
                    <c:forEach var="rsrc" items="${extendResourceBean}">
                      <tr>
                          <td><%=i++%></td>
                          <td class="text-capitalize">${rsrc.getIssuedResources().getResource().getName()}</td>
                          <td class="text-capitalize">${rsrc.getIssuedResources().getResource().getType()}</td>
                          <td class="text-capitalize">${rsrc.getIssuedResources().getResource().getCategory()}</td>
                          <td class="text-capitalize">${rsrc.getFromDate() } - ${rsrc.getToDate()}</td>
                          <td class="text-capitalize">${rsrc.getStatus()} </td>
                      </tr>
                    </c:forEach>
                  </tbody>
              </table>
          </div>
      </div>
  </div>
</div>
   
            