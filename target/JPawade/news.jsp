<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<%@	taglib uri="JPT" prefix="JPTags"
 %>

<!DOCTYPE HTML>
<html lang="en-US">
<head>
<meta charset="UTF-8">
<title>JPawade News</title>

<meta name="description" content="Aditya Pawade's Site News">
<meta property="og:title" content="JPawade News"/>
<meta property="og:type" content="blog"/>
<meta property="og:description" content="Aditya Pawade's Site News"/>
<link rel="author" href="https://plus.google.com/+AdityaPawade"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META NAME="ROBOTS" CONTENT="INDEX, FOLLOW">

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
</head>
<body>
<%@ include file="analyticstracking.jsp" %>
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
          <JPTags:news>
          <li>
            <h4 class="title">$$TITLE$$<span>- $$DATE$$</span></h4>
            <p>$$MESSAGE$$</p>
          </li>
          </JPTags:news>
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