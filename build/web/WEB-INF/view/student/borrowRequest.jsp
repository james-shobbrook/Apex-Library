<%-- 
    Document   : borrowRequest
    Created on : Jul 17, 2019, 10:54:44 PM
    Author     : james
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

      <!--wrapper for content -->
<div class="st-content">
  <!-- extra div  -->
  <div class="st-content-inner padding-none">

    <div class="container-fluid">

      <div class="page-section">
        <h1 class="text-display-1 margin-none">Borrow Request for a Resource</h1>
      </div>
        <div style = "color:red">
            <%=(request.getAttribute("message") == null) ? "" : request.getAttribute("message")%>
        </div>
    </div>
      
      <div class="tab-content">
        <div id="account" class="tab-pane active">
            <form class="form-horizontal" action="${pageContext.request.contextPath}/user/borrowRequest" method="post">
            <div class="form-group">
              <label for="inputEmail3" class="col-md-2 control-label">Resource</label>
              <div class="col-md-8">
                <div class="row">
                    <input type="hidden" name="resource_id" value="${ResourceBean.getId()}">
                  <div class="col-md-6">
                    <div class="">
                        <input type="text" autocomplete="off" required="required" name="from_date" class="date form-control" id="exampleInputFirstName" placeholder="From Date.."><span class="ma-form-highlight"></span><span class="ma-form-bar"></span>
                      <label for="exampleInputFirstName">From Date</label>
                    </div>
                  </div>
                  <div class="col-md-6">
                    <div class="">
                      <input type="text" autocomplete="off" required name="to_date" class="date form-control" id="exampleInputLastName" placeholder="To Date..."><span class="ma-form-highlight"></span><span class="ma-form-bar"></span>
                      <label for="exampleInputLastName">To Date</label>
                    </div>
                  </div>
                </div>
                  <div class="row">
                      <input type="radio" required name="status_type" value="On-Site">On-Site
                      <br>
                      <input type="radio" name="status_type" value="Borrow"> Borrow
                  </div>
              </div>
            </div>
            <div class="form-group margin-none">
              <div class="col-md-offset-2 col-md-10">
                <button type="submit" class="btn btn-primary paper-shadow relative" data-z="0.5" data-hover-z="1" data-animated="">Request</button>
              </div>
            </div>
          </form>
        </div>
      </div>
  </div>
</div>