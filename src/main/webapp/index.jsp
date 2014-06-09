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

<meta name="description" content="Aditya Pawade's Blog and Portfolio">
<meta property="og:title" content="JPawade"/>
<meta property="og:type" content="blog"/>
<meta property="og:description" content="Aditya Pawade's Blog and Portfolio"/>
<link rel="author" href="https://plus.google.com/+AdityaPawade"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META NAME="ROBOTS" CONTENT="INDEX, FOLLOW">

<link rel="stylesheet" type="text/css" href="style.css" media="all" />
<link rel="stylesheet" media="all" href="style/type/folks.css" />
<!--[if IE 7]>
<link rel="stylesheet" type="text/css" href="style/css/ie7.css" media="all" />
<![endif]-->
<script type="text/javascript" src="style/js/jquery-1.5.min.js"></script>
<script type="text/javascript" src="style/js/jquery.cycle.all.min.js"></script>
<script type="text/javascript" src="style/js/ddsmoothmenu.js"></script>
<script type="text/javascript" src="style/js/scripts.js"></script>
<script type="text/javascript">
$(function() {
			if ($('#sliderholder-cycle').length) {
			// timeouts per slide (in seconds) 
			var timeouts = [150,390,25]; 
			function calculateTimeout(currElement, nextElement, opts, isForward) { 
			    var index = opts.currSlide; 
			    return timeouts[index] * 1000;
			}
			jQuery('#sliderholder-cycle').cycle({
				fx: 'fade',
				pager: '.slidernav',
				prev:    '.sliderprev',
        		next:    '.slidernext',
				speed: 1000,
				timeoutFn: calculateTimeout,
				pagerEvent: 'click',
    			pauseOnPagerHover: true,
    			cleartype: 1
			});
			jQuery('#sliderholder-cycle').css("display", "block");
			jQuery('.slidernav').css("display", "block");
			
			}
}); 
</script>
</head>
<body>
<%@ include file="analyticstracking.jsp" %>
<div id="container"> 
  <!-- Begin Header Wrapper -->
  <%@ include file="header.jsp" %>
  <!-- End Header Wrapper --> 
  
  <!-- Begin Slider -->
  <div id="cycle-wrapper">
    <div id="sliderholder-cycle"> 
      <img src="style/images/home/psy1.jpg" width="960" height="380" />
      <img src="style/images/home/psy2.jpg" width="960" height="380" />
      <img src="style/images/home/psy3.jpg" width="960" height="380" />
      <img src="style/images/home/psy4.jpg" width="960" height="380" /> 
    </div>
    <ul class="slidernav">
    </ul>
    <div class="sliderdir"> <a href="#"><span class="sliderprev">Prev</span></a> <a href="#"><span class="slidernext">Next</span></a> </div>
  </div>
  <!-- End Slider --> 
  
  <!-- Begin Wrapper -->
  <div id="wrapper"> 
    
    <!-- Begin Intro -->
    <div class="intro">
      <h1>I am Aditya Pawade. I develop Mobile, Desktop and Web Applications. 
      I also develop Games and Animation. Hope you find something interesting on this site.</h1>
    </div>
    <!-- End Intro --> 
    
    <!-- Begin About -->
    <div id="about">
      <div class="one-fourth"> <a href="#"><img src="style/images/home/mobile.png" alt="" /></a>
        <h4>Mobile Applications</h4>
        <p>Mobile Application and Game Development for Android OS.</p>
      </div>
      <div class="one-fourth"> <a href="#"><img src="style/images/home/web.jpg" alt="" /></a>
        <h4>Web Development</h4>
        <p>Website development using JSP, PHP and ASP.NET.</p>
      </div>
      <div class="one-fourth"> <a href="#"><img src="style/images/home/desktop.gif" alt="" /></a>
        <h4>Desktop Applications</h4>
        <p>Desktop GUI and Console Apps using Javafx, Swing, WPF, etc.</p>
      </div>
      <div class="one-fourth last"> <a href="#"><img src="style/images/home/game.jpg" alt="" /></a>
        <h4>Animation</h4>
        <p>3D, 2D Game Development and Animation using Opengl and Flash</p>
      </div>
    </div>
    <!-- End About --> 
    
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