/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    $(document).ready(function(){
        var data = '';
        var modaltitle='';
        $('.openmodal').click(function(){
            data = $(this).data('id');
            data=data.split("_");
            modaltitle=$(this).parent().siblings('.text-capitalize').first().text().toUpperCase()
            resource_id = data[0];
            issued_id = data[1];

            $("input[name='issued_id']").val(issued_id)
            $("input[name='resource_id']").val(resource_id)
            $('h4.modal-title').text(modaltitle);
        })
        
//        Approve or Reject
        $('input[name="status"]').on('change',function(){
            rsrc_status=$(this).val()
            if(rsrc_status == "approve"){
                
                var st =  '<div class="form-group">\
              <label for="inputEmail3" class="col-md-2 control-label">Resource</label>\
              <div class="col-md-8">\
                <div class="row">\
                    <input type="hidden" name="resource_id" value="">\
                    <input type="hidden" name="issued_id" value="">\
                  <div class="col-md-6">\
                    <div class="">\
                        <input autocomplete="off" type="text" required="required" name="from_date" class="date form-control from_date" id="exampleInputFirstName" placeholder="From Date.."><span class="ma-form-highlight"></span><span class="ma-form-bar"></span>\
                      <label for="exampleInputFirstName">From Date</label>\
                    </div>\
                  </div>\
                  <div class="col-md-6">\
                    <div class="">\
                      <input autocomplete="off" type="text" required name="to_date"  class="date form-control to_date" id="exampleInputLastName" placeholder="To Date..."><span class="ma-form-highlight"></span><span class="ma-form-bar"></span>\
                      <label for="exampleInputLastName">To Date</label>\
                    </div>\
                  </div>\
                </div>\
              </div>\
            </div>';
            
            $('.approve_status').append(st);
            $('.modal-body').append('<div class="row">\
            <p style="color:red" class="date-status"></p>\
           </div>');

            $(".from_date").val(data[2])
            $(".to_date").val(data[3])
            
            $('input.date').datepicker({
                format: 'yyyy-mm-dd',
                startDate: '0d'
            });
            
            $(".modal-footer > .btn").first().attr("type","button").val("Check Availability").addClass("checking-btn");
            Checking();
        
            $('.approve_status').show();
            }
            else{
                
                $('.approve_status').children().remove();
            }
        })
        
    
        $('input.date').datepicker({
            format: 'yyyy-mm-dd',
            startDate: '0d'
        });
        
       $(document).on('click','.checking-btn',function(){
            $(".modal-footer > .btn").first().attr("type","button").val("Check Availability").addClass("checking-btn");
            Checking();
        })
    function Checking(){
            $.get('checkAvailability', {
                        resource_id : data[0],
                        issued_id : data[1],
                        from_date:  $(".from_date").val(),
                        to_date: $(".to_date").val()
                }, function(responseText) {
                    console.log(responseText)
                    if(responseText == "Good to go!")
                        $(".modal-footer > .btn").first().attr("type","submit").val("Submit").removeClass("checking-btn");
                    else
                        $(".modal-footer > .btn").first().attr("type","button").val("Check Availability").addClass("checking-btn");
                    $('.date-status').text(responseText);
                        
               });
       }
       
       
       
       $(document).on('change','#myRange',function(){
           $('#rangeval').text($(this).val())
       })
       
       $('.issuedModal').click(function(){
           var check=$(this).text()
           $('.issued_extension').children().remove();
           var data ='';
           if(check == "Return Book"){
               var RatingStr = '<div class="slidecontainer">\
                         <label for="rating">Please give Rating to this resource</label>\
                        <p>Range Value: 05/<span id="rangeval"></span></p>\
                      <input type="range" min="1" max="5" value="03" name="rating" class="slider" id="myRange">\
                </div>'; 
               var Formaction = $('form[name="issuedextension"]').attr('action');
               Formaction=Formaction.replace("IssuedExtension","")
               $('form[name="issuedextension"]').attr('action',Formaction+"ReturnIssued");
               $('.modal-title').text('Return Resource');
                $('.issued_extension').append(RatingStr);
               $('.issued_extension').show();
               
               
           }else{
                var st =  '<div class="form-group">\
              <label for="inputEmail3" class="col-md-2 control-label">Resource</label>\
              <div class="">\
                <div class="row">\
                    <input type="hidden" name="resource_id" value="">\
                    <input type="hidden" name="issued_id" value="">\
                  <div class="col-md-4">\
                    <div class="">\
                        <input autocomplete="off" type="text" required="required" name="from_date" class="date form-control from_date" id="exampleInputFirstName" placeholder="From Date.."><span class="ma-form-highlight"></span><span class="ma-form-bar"></span>\
                      <label for="exampleInputFirstName">From Date</label>\
                    </div>\
                  </div>\
                  <div class="col-md-4">\
                    <div class="">\
                      <input type="text" autocomplete="off" required name="to_date"  class="date form-control to_date" id="exampleInputLastName" placeholder="To Date..."><span class="ma-form-highlight"></span><span class="ma-form-bar"></span>\
                      <label for="exampleInputLastName">To Date</label>\
                    </div>\
                  </div>\
                </div>\
              </div>\
            </div>';
               var Formaction = $('form[name="issuedextension"]').attr('action');
               Formaction=Formaction.replace("ReturnIssued","")
               $('form[name="issuedextension"]').attr('action',Formaction+"IssuedExtension");
               $('.modal-title').text('Extension of Resource');
               $('.issued_extension').append(st);
               $('.issued_extension').show();
               $('input.date').datepicker({
                    format: 'yyyy-mm-dd',
                    startDate: '0d'
                });
           }
           
           data = $(this).data('id');
            data=data.split("_");
            modaltitle=$(this).parent().siblings('.text-capitalize').first().text().toUpperCase()
            resource_id = data[0];
            issued_id = data[1];
            
            $("input[name='issued_id']").val(issued_id)
            $("input[name='resource_id']").val(resource_id)
            rfrom_date = data[2]; 
            rto_date = data[3]; 
            date1 = new Date();
             date2 = new Date(rto_date);
             diffTime = date1.getTime() - date2.getTime();
             diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24)); 
             penalty = 0;
             console.log(diffDays);
             if(diffDays > 0){
                 penalty = diffDays * 10;
                $('.penalty_value').text(penalty) 
                $(this).parent().siblings("td.penaltytd").text(penelty+" Pence")
                $('.penalty_days').text(diffDays) 
                $('.penalty').show()
             }
          
       })
       
    })
    
