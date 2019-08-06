
  <div id="content">
    <div class="container-fluid">
       
      <div class="lock-container">
        <div class="panel panel-default text-center paper-shadow" data-z="0.5">
          <h1 class="text-display-1 text-center margin-bottom-none">Sign In</h1>
          <img src="${pageContext.request.contextPath}/imgs/apex.png" class="img-circle width-80">
          <form action="login" method="POST">
            <div class="panel-body">
              <div class="form-group">
                <div class="form-control-material">
                  <input class="form-control" id="username" type="text" placeholder="Username" name="username">
                  <label for="username">Username</label>
                </div>
              </div>
              <div class="form-group">
                <div class="form-control-material">
                  <input class="form-control" name="password" id="password" type="password" placeholder="Enter Password">
                  <label for="password">Password</label>
                </div>
              </div>
               <div style="color:red"><%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%></div>
                <button class="btn btn-primary" name="submit" type="submit">Login<i class="fa fa-fw fa-unlock-alt"></i></button>
                
            </div>
          </form>
        </div>
      </div>
      
      
  