<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<%@ taglib uri="JPT" prefix="JPTags"
 %>

<!DOCTYPE HTML>
<html lang="en-US">
<head>
<meta charset="UTF-8">
<title>JPawade Blog Post</title>

<meta name="description" content="Aditya Pawade's Blog Post">
<meta property="og:title" content="JPawade Blog Post"/>
<meta property="og:type" content="article"/>
<meta property="og:description" content="Aditya Pawade's Blog Post"/>
<link rel="author" href="https://plus.google.com/+AdityaPawade"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META NAME="ROBOTS" CONTENT="INDEX, FOLLOW">

<link rel="stylesheet" type="text/css" href="style.css" media="all" />
<link rel="stylesheet" media="all" href="style/type/folks.css" />

<!--[if IE 7]>
<link rel="stylesheet" type="text/css" href="style/css/ie7.css" media="all" />
<![endif]-->

<link rel="stylesheet" type="text/css" href="style/css/sh/shCore.css">
<link rel="stylesheet" type="text/css" href="style/css/sh/shThemeDefault.css">
<script type="text/javascript" src="style/js/sh/shCore.js"></script>
<script type="text/javascript" src="style/js/sh/shBrushJava.js"></script>
<script type="text/javascript" src="style/js/sh/shCSharp.js"></script>
<script type="text/javascript" src="style/js/sh/shCpp.js"></script>

<script type="text/javascript" src="style/js/jquery-1.5.min.js"></script>
<script type="text/javascript" src="style/js/ddsmoothmenu.js"></script>
<script type="text/javascript" src="style/js/scripts.js"></script>
<script type="text/javascript" src="style/js/jquery.infieldlabel.min.js"></script>
<script type="text/javascript" src="scripts/mygwtwidgets.nocache.js"></script>
<script type="text/javascript">
  $(function(){ $("label").inFieldLabels(); });
</script>
<script type="text/javascript">
      $(document).ready(function() {
        $('.reply-link').click(function() {
          $('#replyId').attr('value', $(this).attr("id"));
          $('#leave-reply-text').text($(this).attr("title"));
        });
        SyntaxHighlighter.all();
      });
</script>
</head>
<body>
<%@ include file="analyticstracking.jsp" %>
<div id="container"> 
  <!-- Begin Header Wrapper -->
  <%@ include file="header.jsp" %>
  <!-- End Header Wrapper --> 
  
  <!-- Begin Wrapper -->
  <div id="wrapper">
    <div id="post-wrapper">
      <JPTags:post details="true">
        <div class="post">
          <h2 class="title">$$TITLE$$</h2>
          <div class="meta">
            <div class="top-border"></div> Posted on 
            <div class="date">$$DATE$$</div> |   
            <a href="#" title="">$$COMMSIZE$$Comments</a> |   
            <a id="" title="Leave a Reply" class="reply-link" href="#comment-form">Reply</a>
          </div>
          $$IMAGE$$
          <p>$$CONTENT$$</p>
          <div class="tags">
            <div class="top-border"></div> Tags: 
            $$TAGS$$ 
          </div>
        </div>
      </JPTags:post>
      
      <!-- Begin Comments -->
      
      <div id="comment-wrapper">
        <JPTags:post details="false">
          <h3>$$COMMSIZE$$ Responses to "$$TITLE$$"</h3>
        </JPTags:post>
        <!-- Begin Comments -->
        <div id="comments">
          <ol id="singlecomments" class="commentlist">
          <JPTags:comment>
              <div class="message">
                <div class="info">
                  <h3><a href="#">$$NAME$$</a></h3>
                  <span class="date">- $$DATE$$</span> </div>
                <p>$$MESSAGE$$</p>
                <a id="$$ID$$" title="Leave a Reply for $$NAME$$" class="reply-link" href="#comment-form">Reply</a>
              </div>
              <div class="clear"></div>
           </JPTags:comment>
          </ol>
        </div>
        <!-- End Comments --> 
        
        <!-- Begin Form -->
        <div id="comment-form" class="comment-form">
          <h3><label id="leave-reply-text">Leave a Reply</label></h3>
          <form name="form_name" method="post" action="Post">
            <div class="comment-input">
              <p>
                <input type="text" name="name" value="" required id="name" placeholder="Name">
              </p>
              <p>
                <input type="text" name="email" value="" required id="email" placeholder="Email">
              </p>
              <p>
                <input type="text" name="website" value="" id="website" placeholder="Website">
              </p>
              <p>
                <input type="hidden" name="replyId" value="id" id="replyId">
                <JPTags:post details="false">
                <input type="hidden" name="postId" value="$$TARGET$$" id="postId">
                </JPTags:post>
              </p>
            </div>
            <div class="comment-textarea">
              <textarea name="message" id="textarea" rows="5" cols="30" required ></textarea>
              <input id="submit-button" class="button gray stripe" type="submit" name="submit" value="Send Message" />
            </div>
            <div class="clear"></div>
          </form>
          <div class="clear"></div>
        </div>
        <!-- End Form --> 
        
      </div>
      
      <!-- End Comments --> 
      
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