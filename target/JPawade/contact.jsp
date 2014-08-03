<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<%@ taglib uri="JPT" prefix="JPTags"
 %>
 
 <!DOCTYPE HTML>
<html lang="en-US">
<head>
<meta charset="UTF-8">
<title>JPawade Contact</title>

<meta name="description" content="Contact Aditya Pawade">
<meta property="og:title" content="JPawade Contact"/>
<meta property="og:type" content="blog"/>
<meta property="og:description" content="Contact Aditya Pawade"/>
<link rel="author" href="https://plus.google.com/+AdityaPawade"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META NAME="ROBOTS" CONTENT="INDEX, FOLLOW">

<link rel="stylesheet" type="text/css" href="style.css" media="all" />
<link rel="stylesheet" media="all" href="style/type/folks.css" />
<!--[if IE 7]>
<link rel="stylesheet" type="text/css" href="style/css/ie7.css" media="all" />
<![endif]-->
<script type="text/javascript" src="style/js/jquery-1.5.min.js"></script>
<script type="text/javascript" src="style/js/ddsmoothmenu.js"></script>
<script type="text/javascript" src="style/js/scripts.js"></script>
<script type="text/javascript" src="style/js/jquery.infieldlabel.min.js"></script>
<script type="text/javascript" src="style/js/twitter.min.js"></script>
<script type="text/javascript">
	$(function(){ $("label").inFieldLabels(); });
</script>
<script type="text/javascript">  
        
$(document).ready(function() {
	
    $("#ajax-contact-form").submit(function() {
        $('#load').append('<img src="style/images/ajax-loader.gif" alt="Currently Loading" id="loading" />');

        var fem = $(this).serialize(),
        note = $('#note');
	
        $.ajax({
            type: "POST",
            url: "Contact",
            data: fem,
            success: function(msg) {
				if ( note.height() ) {			
					note.slideUp(500, function() { $(this).hide(); });
				} 
				else note.hide();

				$('#loading').fadeOut(300, function() {
					$(this).remove();

					// Message Sent? Show the 'Thank You' message and hide the form
					result = (msg === 'OK') ? '<div class="success">Your message has been sent. Thank you!</div>' : '<div class="error">' + msg + '</div>';

          			var resultFunc = (msg === 'OK') ? function() {
                		window.location.replace("Contact");
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
  <%@ include file="header.jsp" %>
  <!-- End Header Wrapper --> 
  
  <!-- Begin Wrapper -->
  <div id="wrapper"> 
    
    <!-- Begin Content -->
    <div class="content">
      <h3>Contact Information</h3>
      <p>Ask me anything. Just leave me mail and I'll get back to you as soon as possible. You can also contact me on Linkedin, Facebook or Google Plus.</p>
      <!-- Begin Form -->
      <div class="contact-left">
        <div id="contact-form"> 
          
          <!--begin:notice message block-->
          <div id="note"></div>
          <!--begin:notice message block-->
          
          <form id="ajax-contact-form" method="post" action="javascript:alert('success!');">
            <div class="labels">
              <p>
                <input class="required inpt" type="text" name="name" id="name" value="" placeholder="Name" required />
              </p>
              <p>
                <input class="required inpt" type="text" name="email" id="email" value="" placeholder="Email" required />
              </p>
              <p>
                <input type="text" name="website" id="website" value="" placeholder="Website" />
              </p>
            </div>
            <div class="comments">
              <p>
                <textarea class="textbox" name="message" rows="6" cols="30" required></textarea>
              </p>
              <br />
              <JPTags:capcha>
	              <p>
	                <input type="hidden" name="capid" id="capid" value="$$CAPID$$" />
	              </p>
	              <p>
	                <label for="answer">$$QUES$$</label>
	                <input class="required inpt" type="text" name="answer" id="answer" value="" required/>
	              </p>
              </JPTags:capcha>
            </div>
            <label id="load"></label>
            <input id="submit-button" class="button gray stripe" type="submit" name="submit" value="Send Message" />
          </form>
        </div>
        <!-- End Form --> 
      </div>
      <div class="contact-right">
        <div class="one-half">
          <h4>Mobile</h4>
          <p>
            <span class="highlight3">Home:</span> +91 9823721707 <br/>
            <span class="highlight3">Work :</span> +91 8870028834
          </p>
        </div>
        <div class="one-half last">
          <h4>Social</h4>
          <p>
            <span class="highlight3"></span> <a href="http://in.linkedin.com/in/adityapawade/">Linkedin</a>  <br/>
            <span class="highlight3"></span> <a href="https://www.facebook.com/jimpawade">Facebook</a> <br/>
            <span class="highlight3"></span> <a href="https://plus.google.com/u/0/+AdityaPawade">Google Plus</a>
          </p>
        </div>
        <div class="clear"></div>
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