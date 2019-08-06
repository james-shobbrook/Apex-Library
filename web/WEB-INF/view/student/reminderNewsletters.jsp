<%-- 
    Document   : reminderNewsletters
    Created on : Jul 17, 2019, 2:49:45 PM
    Author     : james
--%>

<div class="st-content">
  <!-- extra div -->
  <div class="st-content-inner padding-none">

    <div class="container-fluid">

      <div class="page-section">
        <h1 class="text-display-1 margin-none">All Reminders and Newsletters</h1>
      </div>
    </div>
      
      <div class="tab-content">
          <div id="account" class="tab-pane active">
              <table class="table table-hover table-striped">
                  <thead>
                      <tr>
                          <th colspan="5" style="text-align:center">
                              Reminders
                          </th>
                      </tr>
                      <tr>
                          <th>Sr#</th>
                          <th>Message</th>
                          <th>Type</th>
                          <th>Created By</th>
                          <th>Date</th>
                      </tr>
                  </thead>
                  <tbody>
                      <% int i=1; %>
                    <c:forEach var="reminder" items="${reminders}">
                      <tr>
                          <td><%=i++%></td>
                          <td class="text-capitalize">${reminder.getMessage()}</td>
                          <td class="text-capitalize">${reminder.getType()}</td>
                          <td class="text-capitalize">${reminder.getCreatedBy().getName()}</td>
                          <td class="text-capitalize">${reminder.getDateTime()}</td>
                      </tr>
                    </c:forEach>
                  </tbody>
              </table>
          </div>
      </div>
      <div class="tab-content">
          <div id="account" class="tab-pane active">
              <table class="table table-hover table-striped">
                  <thead>
                      <tr>
                          <th colspan="5" style="text-align:center">
                              NewsLetters
                          </th>
                      </tr>
                      <tr>
                          <th>Sr#</th>
                          <th>Message</th>
                          <th>Type</th>
                          <th>Created By</th>
                          <th>Date</th>
                      </tr>
                  </thead>
                  <tbody>
                      <% i=1; %>
                    <c:forEach var="newsletter" items="${newsletters}">
                      <tr>
                          <td><%=i++%></td>
                          <td class="text-capitalize">${newsletter.getMessage()}</td>
                          <td class="text-capitalize">${newsletter.getType()}</td>
                          <td class="text-capitalize">${newsletter.getCreatedBy().getName()}</td>
                          <td class="text-capitalize">${newsletter.getDateTime()}</td>
                      </tr>
                    </c:forEach>
                  </tbody>
              </table>
          </div>
      </div>
  </div>
</div>
   