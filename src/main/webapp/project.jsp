<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<%@ taglib uri="JPT" prefix="JPTags"
 %>

<!DOCTYPE HTML>
<html lang="en-US">
<head>
<meta charset="UTF-8">
<title>JPawade Project</title>

<meta name="description" content="Aditya Pawade's Project">
<meta property="og:title" content="JPawade Project"/>
<meta property="og:type" content="blog"/>
<meta property="og:description" content="Aditya Pawade's Project"/>
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
  
    <div id="portfolio-single">
      <JPTags:project>
      <div class="image">$$IMAGES$$</div>
      <div class="text">
        <h3>$$TITLE$$</h3>
        <p>$$DESCRIPTION$$</p>
        $$URL$$
        <div class="divider3"></div>
        <a href="Project?id=$$PREVIOUS$$" class="p-project">&laquo; Previous Project</a> <a href="Project?id=$$NEXT$$" class="n-project">Next Project &raquo;</a> </div>
      </JPTags:project>
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
			$("#gallery a[rel^='prettyPhoto']").prettyPhoto({theme:'light_square',autoplay_slideshow: false});
		});
		
		
		
		$(function() {
            var offset = $(".text").offset();
            var topPadding = 15;
            $(window).scroll(function() {
                if ($(window).scrollTop() > offset.top) {
                    $(".text").stop().animate({
                        marginTop: $(window).scrollTop() - offset.top + topPadding
                    });
                } else {
                    $(".text").stop().animate({
                        marginTop: 0
                    });
                };
            });
        });
</script>
</body>
</html>