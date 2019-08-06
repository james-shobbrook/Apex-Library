<%-- 
    Document   : newResource
    Created on : Jul 25, 2019, 2:09:55 AM
    Author     : james
--%>

      <!--wrapper for content -->
<div class="st-content">
  <!-- extra div -->
  <div class="st-content-inner padding-none">

    <div class="container-fluid">

      <div class="page-section">
        <h1 class="text-display-1 margin-none">Add New Resource</h1>
      </div>
        <div style = "color:red">
            <%=(request.getAttribute("message") == null) ? "" : request.getAttribute("message")%>
        </div>
    </div>
      
      <div class="tab-content">
        <div id="account" class="tab-pane active">
            <form class="form-horizontal" action="${pageContext.request.contextPath}/user/RequestNewResource" method="post">
            <div class="form-group">
              <label for="inputEmail3" class="col-md-2 control-label">Resource</label>
              <div class="col-md-8">
                <div class="row">
                  <div class="col-md-6">
                    <div class="form-control-material">
                      <input type="text" required name="name" class="form-control" id="exampleInputFirstName" placeholder="Resource Name.."><span class="ma-form-highlight"></span><span class="ma-form-bar"></span>
                      <label for="exampleInputFirstName">Name</label>
                    </div>
                  </div>
                  <div class="col-md-6">
                    <div class="form-control-material">
                      <input required type="text" name="type" class="form-control" id="exampleInputLastName" placeholder="Type..."><span class="ma-form-highlight"></span><span class="ma-form-bar"></span>
                      <label for="exampleInputLastName">Type</label>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="form-group">
              <label for="inputEmail3" class="col-md-2 control-label">Dewey Decimal</label>
              <div class="col-md-6">
                <div class="form-control-material">
                  <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
                    <input required type="number" name="category" class="form-control" id="inputEmail3" placeholder="Category.."><span class="ma-form-highlight"></span><span class="ma-form-bar"></span>
                    <label for="inputEmail3">Category</label>
                  </div>
                </div>
              </div>
            </div>
            <div class="form-group margin-none">
              <div class="col-md-offset-2 col-md-10">
                <button type="submit" class="btn btn-primary paper-shadow relative" data-z="0.5" data-hover-z="1" data-animated="">Save Changes</button>
              </div>
            </div>
          </form>
        </div>
      </div>
            
            <hr/>
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
                      </tr>
                  </thead>
                  <tbody>
                    <c:forEach var="rsrc" items="${RequestNewResourceBean}">
                      <tr>
                          <td>1</td>
                          <td class="text-capitalize">${rsrc.getName()}</td>
                          <td class="text-capitalize">${rsrc.getType()}</td>
                          <td class="text-capitalize">${rsrc.getCategory()}</td>
                          <td class="text-capitalize">${rsrc.getCreatedBy().getName()}</td>
                          <td class="text-capitalize">${rsrc.getStatus()}</td>
                          
                      </tr>
                    </c:forEach>
                  </tbody>
              </table>
          </div>
      </div>

  </div>
</div>