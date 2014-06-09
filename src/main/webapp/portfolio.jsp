<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<%@ taglib uri="JPT" prefix="JPTags"
 %>

<!DOCTYPE HTML>
<html lang="en-US">
<head>
<meta charset="UTF-8">
<title>JPawade Portfolio</title>

<meta name="description" content="Aditya Pawade's Portfolio">
<meta property="og:title" content="JPawade Portfolio"/>
<meta property="og:type" content="blog"/>
<meta property="og:description" content="Aditya Pawade's Portfolio"/>
<link rel="author" href="https://plus.google.com/+AdityaPawade"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META NAME="ROBOTS" CONTENT="INDEX, FOLLOW">

<link rel="stylesheet" type="text/css" href="style.css" media="all" />
<link rel="stylesheet" media="all" href="style/css/prettyPhoto.css" />
<link rel="stylesheet" media="all" href="style/type/folks.css" />
<!--[if IE 7]>
<link rel="stylesheet" type="text/css" href="style/css/ie7.css" media="all" />
<![endif]-->
<script type="text/javascript" src="style/js/jquery-1.5.min.js"></script>
<script type="text/javascript" src="style/js/ddsmoothmenu.js"></script>
<script type="text/javascript" src="style/js/quicksand.js"></script>
<script type="text/javascript" src="style/js/portfolio.js"></script>
<script type="text/javascript" src="style/js/scripts.js"></script>
<script type="text/javascript" src="style/js/jquery.prettyPhoto.js"></script>
</head>
<body>
<%@ include file="analyticstracking.jsp" %>
<div id="container"> 
  <!-- Begin Header Wrapper -->
  <%@ include file="header.jsp" %>
  <!-- End Header Wrapper --> 
  
  <!-- Begin Wrapper -->
  <div id="wrapper">
    <div id="portfolio"> 
      <!-- Begin Portfolio Navigation -->
      <ul class="gallerynav">
        <li class="selected-1"><a href="#" data-value="all">All<span></span></a></li>
        <JPTags:portfolioService>
        	<li><a href="#" data-value="$$SERVICECLASS$$">$$SERVICE$$<span></span></a></li>
        </JPTags:portfolioService>
      </ul>
      <!-- End Portfolio Navigation --> 
      
      <!-- Begin Portfolio Elements -->
      <ul id="gallery" class="grid">
        
        <!-- Begin Image 1 -->
        <JPTags:portfolioProject>
        	<li data-id="id-$$ID$$" class="$$SERVICE$$"> 
        		<a href="Project?id=$$ID$$" title="$$TITLE$$"> 
        			<img src="style/images/$$IMAGE$$" alt="" />
        		</a> 
        	</li>
        </JPTags:portfolioProject>
        <!-- End Image 1 -->
      </ul>
      <!-- End Portfolio Elements --> 
      
    </div>
  </div>
  
  <!-- End Wrapper -->
  <div class="clearfix"></div>
  <div class="push"></div>
</div>

<!-- Begin Footer -->
<%@ include file="footer.jsp" %>
<!-- End Footer --> 
<script type="text/javascript">
		$(document).ready(function(){
			
			$("ul.grid img").hide()
			$("ul.grid img").each(function(i) {
			  $(this).delay(i * 200).fadeIn();
			});
			
		});
</script>
</body>
</html>