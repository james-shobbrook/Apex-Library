<%-- 
    Document   : SendnewsLetters
    Created on : Jul 17, 2019, 2:16:52 PM
    Author     : james
--%>
      <!--wrapper for content -->
<div class="st-content">
  <!-- extra div  -->
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
            <form class="form-horizontal" action="${pageContext.request.contextPath}/admin/SendNewsLetters" method="POST">
            <div class="form-group">
              <label for="inputEmail3" class="col-md-2 control-label">Create New Newsletters</label>
              <textarea cols="130" required rows="10" name="newsletter_text"></textarea>
            </div>
            <style>
                #s2id_country{
                    width:100%;
                }
            </style>
            <div class="form-group">
                <label for="country" class="col-md-2 control-label">Select Users</label>
                <div class="col-md-6">
                    <select id="country" data-toggle="select2" name="user_id" multiple="multiple" width="100%" required>
                      <c:forEach var="user" items="${Users}">
                          <option value="${user.getId()}">${user.getName()}</option>
                      </c:forEach>
                  </select>
                </div>
            </div>
            <br/>
            <div class="form-group margin-none">
              <div class="col-md-offset-2 col-md-10">
                <button type="submit" class="btn btn-primary pull-right paper-shadow relative" data-z="0.5" data-hover-z="1" data-animated="">Send</button>
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
                          <th>Newsletter</th>
                          <th>Sent to</th>
                          <th>Sent TimeStamp</th>
                      </tr>
                  </thead>
                  <tbody>
                      <% int i =1; %>
                    <c:forEach var="newsletter" items="${NewsLetters}">
                      <tr>
                          <td><%=i%></td>
                          <td class="text-capitalize">${newsletter.getMessage()}</td>
                          <td class="text-capitalize">${newsletter.getUser().getName()}</td>
                          <td class="text-capitalize">${newsletter.getDateTime()}</td>                          
                      </tr>
                       <% i++; %>
                    </c:forEach>
                  </tbody>
              </table>
          </div>
      </div>

  </div>
</div>