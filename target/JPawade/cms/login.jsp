<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<%@ taglib uri="JPT" prefix="JPTags"
 %>
 
 <!DOCTYPE HTML>
<html lang="en-US">
<head>
<meta charset="UTF-8">
<title>JPawade Login</title>
<link rel="stylesheet" type="text/css" href="style.css" media="all" />
<link rel="stylesheet" media="all" href="style/type/folks.css" />
<script type="text/javascript" src="style/js/jquery-1.5.min.js"></script>
<script type="text/javascript" src="style/js/jquery.md5.js"></script>
<script type="text/javascript">  
        
$(document).ready(function() {
    $("#ajax-login-form").submit(function() {
        $('#load').append('<center><img src="ajax-loader.gif" alt="Currently Loading" id="loading" /></center>');

        var passHash = $.md5($('#pass').val());
        var name = $('#name').val();
        var ser = "name=" +name+"&pass="+passHash;

        var fem = $(this).serialize(),
        note = $('#note');
	
        $.ajax({
            type: "POST",
            url: "Login",
            data: ser,
            success: function(msg) {
				if ( note.height() ) {			
					note.slideUp(500, function() { $(this).hide(); });
				} 
				else note.hide();

				$('#loading').fadeOut(300, function() {
					$(this).remove();

					// Message Sent? Show the 'Thank You' message and hide the form
					result = (msg === 'OK') ? '<div class="success">Login Successful</div>' : '<div class="error">' + msg + '</div>';

          			var resultFunc = (msg === 'OK') ? function() {
                		window.location.replace("./");
              		} : function() {
              		};

					var i = setInterval(function() {
						if ( !note.is(':visible') ) {
							note.html(result).slideDown(500,resultFunc);
							clearInterval(i);
						}
					}, 40);    
				}); // end loading image fadeOut
            }
        });

        return false;
    });
});


</script>
</head>
<body>
<div id="container"> 
  <!-- Begin Header Wrapper -->
  <div id="page-top">
    <div id="header-wrapper"> 
      <!-- Begin Header -->
      <div id="header">
        <div id="logo"><a href="./"><img src="../style/images/logo.png" alt="JPawade" /></a></div> 
      </div>
      <!-- End Header --> 
    </div>
  </div>
  <!-- End Header Wrapper --> 
  
  <!-- Begin Wrapper -->
  <div id="wrapper"> 
    
    <!-- Begin Content -->
    <div class="loginContent">
      <h3>Login</h3>
      <!-- Begin Form -->
      <div class="login-form">
        <div id="login-form"> 
          
          <!--begin:notice message block-->
          <div id="note"></div>
          <!--begin:notice message block-->
          
          <form id="ajax-login-form" method="post" action="javascript:alert('success!');">
            <div class="labels">
              <p>
                <input class="required inpt" type="text" name="name" id="name" value="" placeholder="Name" required />
              </p>
              <p>
                <input class="required inpt" type="password" name="pass" id="pass" value="" placeholder="Password" required />
              </p>
            </div>
            <label id="load" style="display:none"></label>
            <input id="submit-button" class="button gray stripe" type="submit" name="submit" value="Login" />
          </form>
        </div>
        <div class="clear"></div>
        <!-- End Form --> 
      </div>
      <div class="clear"></div>
    </div>
    <!-- End Content --> 
    
  </div>
  <!-- End Wrapper -->
  
  <div class="clearfix"></div>
  <div class="push"></div>
</div>

<!-- Begin Footer -->
<%@ include file="footer.jsp" %>
<!-- End Footer -->
</body>
</html>