<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<%@ taglib uri="JPT" prefix="JPTags"
 %>
 
 <!DOCTYPE HTML>
<html lang="en-US">
<head>
<meta charset="UTF-8">
<title>JPawade</title>
<link rel="stylesheet" type="text/css" href="style.css" media="all" />
<link rel="stylesheet" media="all" href="style/type/folks.css" />
<script type="text/javascript" src="style/js/jquery-1.5.min.js"></script>
<script type="text/javascript" src="style/js/jquery.md5.js"></script>
<script type="text/javascript">  
        
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
    <div class="hostedContent">
      <JPTags:hosted>
      <h3>$$TITLE$$</h3>
      <object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" width="600" height="480" id="movie_name" align="middle">
		  <param name="movie" value="hosted/$$PATH$$"/>
		  <!--[if !IE]>-->
		  <object type="application/x-shockwave-flash" data="hosted/$$PATH$$" width="600" height="480">
		      <param name="movie" value="movie_name"/>
		  <!--<![endif]-->
		      <a href="http://www.adobe.com/go/getflash">
		          <img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="Get Adobe Flash player"/>
		      </a>
		  <!--[if !IE]>-->
		  </object>
		  <!--<![endif]-->
	   </object>
	   </JPTags:hosted>
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