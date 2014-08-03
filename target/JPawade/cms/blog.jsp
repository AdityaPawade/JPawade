<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<%@	taglib uri="JPTcms" prefix="JPTags"
 %>

<!DOCTYPE HTML>
<html lang="en-US">
<head>
<meta charset="UTF-8">
<title>JPawade Blog</title>
<link rel="stylesheet" type="text/css" href="style.css" media="all" />
<link rel="stylesheet" media="all" href="style/type/folks.css" />
<!--[if IE 7]>
<link rel="stylesheet" type="text/css" href="style/css/ie7.css" media="all" />
<![endif]-->
<script type="text/javascript" src="style/js/jquery-1.5.min.js"></script>
<script type="text/javascript" src="style/js/ddsmoothmenu.js"></script>
<script type="text/javascript" src="style/js/scripts.js"></script>
</head>
<body>
<div id="container"> 
  <!-- Begin Header Wrapper -->
  <%@ include file="header.jsp" %>
  <!-- End Header Wrapper --> 
  
  <!-- Begin Wrapper -->
  <div id="wrapper">
    <div id="post-wrapper">
      <JPTags:posts>
	      <div class="post">
	        <h2 class="title"><a href="$$TARGET$$" title="">$$TITLE$$</a></h2>
	        <div class="meta">
	          <div class="top-border"></div> Posted on 
	          <div class="date">$$DATE$$</div> | <div class="date">$$COMMSIZE$$ Comments</div> 
	        </div>
	        $$IMAGE$$
	        <p>$$DESC$$<a href="$$TARGET$$" class="more">Continue Reading &raquo;</a></p>
	        <div class="tags">
	          <div class="top-border"></div> Tags: 
	          $$TAGS$$ 
	         </div>
	      </div>
      </JPTags:posts>
      
      <!-- Begin Page Navigation -->
      <ul class="page-navi">
        <JPTags:pager>$$PAGER$$</JPTags:pager>
      </ul>
      <!--End Page Navigation --> 
      
    </div>
    <%@ include file="sidebar.jsp" %>
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