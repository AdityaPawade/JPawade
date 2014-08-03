<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<%@	taglib uri="JPTcms" prefix="JPTags"
 %>

<!DOCTYPE HTML>
<html lang="en-US">
<head>
<meta charset="UTF-8">
<title>JPawade News</title>
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
                <input id="newsInput" name="title" type="text" placeholder="Title">
              </h4>
              <p><textarea id="newsMessage" name="message" placeholder="Message"></textarea></p>
              <input value="add" name="func" type="hidden">
              <input type="submit">
            </form>
          </li>
          <JPTags:news>
          <li>
          	<form method="Post">
	            <h4 class="title">$$TITLE$$<span>- $$DATE$$</span><input type="submit" value="Remove" class="newsRemove"></h4>
	            <p>$$MESSAGE$$</p>
	            <input value="remove" name="func" type="hidden">
	            <input value="$$ID$$" name="newsId" type="hidden">
	        </form>
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