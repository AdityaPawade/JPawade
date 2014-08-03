<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<%@	taglib uri="JPTcms" prefix="JPTags"
 %>

<!DOCTYPE HTML>
<html lang="en-US">
<head>
<meta charset="UTF-8">
<title>JPawade Contact</title>
<link rel="stylesheet" type="text/css" href="style.css" media="all" />
<link rel="stylesheet" media="all" href="style/type/folks.css" />
<!--[if IE 7]>
<link rel="stylesheet" type="text/css" href="style/css/ie7.css" media="all" />
<![endif]-->
<script type="text/javascript" src="style/js/jquery-1.5.min.js"></script>
<script type="text/javascript" src="style/js/jquery.jcarousel.js"></script>
<script type="text/javascript" src="style/js/newscarousel.js"></script>
<script type="text/javascript" src="style/js/ddsmoothmenu.js"></script>
<script type="text/javascript" src="style/js/scripts.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
      $('.replyEmail').click(function() {
        $('#newsInput').attr('value', $(this).attr("title"));
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
    <div id="news"> 
      
      <!-- Begin News Navigation -->
      <div id="newslist">
        <ul>
          <!-- Begin Entry Heading 1 -->
          <li>
            <form method="Post">
              <h4 class="title">
                <input id="newsInput" name="email" type="text" placeholder="Email">
              </h4>
              <p><textarea id="newsMessage" name="message" placeholder="Message"></textarea></p>
              <input value="mail" name="func" type="hidden">
              <input type="submit" value="Send Email">
            </form>
          </li>
          <JPTags:contact>
          <li>
          	<form method="Post">
	            <h4 class="title">$$NAME$$ ( 
		             <a href="#" title="$$EMAIL$$" class="replyEmail">$$EMAIL$$</a> ) : 
		             <a href="$$WEBSITE$$" title="$$WEBSITE$$" >$$WEBSITE$$</a>
		             <span>- $$DATE$$</span>
		             <input type="submit" value="Remove" class="newsRemove">
	            </h4>
	            <p>$$MESSAGE$$</p>
	            <input value="remove" name="func" type="hidden">
	            <input value="$$ID$$" name="messageId" type="hidden">
	        </form>
          </li>
          </JPTags:contact>
          <!-- End Entry Heading 1 --> 
        </ul>
      </div>
      <div class="clearfix"></div>
      <div id="scroll"> <a href="#" id="newslist-prev" class="jbutton"></a> <a href="#" id="newslist-next" class="jbutton"></a> </div>
      <div class="clearfix"></div>
    </div>
    <!-- End News Navigation --> 
    
  </div>
  <div class="clearfix"></div>
  <div class="push"></div>
</div>
<!-- End Wrapper --> 

<!-- Begin Footer -->
<%@ include file="footer.jsp" %>
<!-- End Footer -->
</body>
</html>