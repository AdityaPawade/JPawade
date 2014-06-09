<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<%@ taglib uri="JPTcms" prefix="JPTags"
 %>

<!DOCTYPE HTML>
<html lang="en-US">
<head>
<meta charset="UTF-8">
<title>JPawade Blog Post</title>
<link rel="stylesheet" type="text/css" href="style.css" media="all" />
<link rel="stylesheet" media="all" href="style/type/folks.css" />
<!--[if IE 7]>
<link rel="stylesheet" type="text/css" href="style/css/ie7.css" media="all" />
<![endif]-->
<script type="text/javascript" src="style/js/jquery-1.5.min.js"></script>
<script type="text/javascript" src="style/js/ddsmoothmenu.js"></script>
<script type="text/javascript" src="style/js/scripts.js"></script>
<script type="text/javascript" src="style/js/jquery.infieldlabel.min.js"></script>
<script type="text/javascript">
  $(function(){ $("label").inFieldLabels(); });
</script>
<script type="text/javascript">
    function readURL(input) {
      if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
          $('#imgPreview').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
      }
    }

    $(document).ready(function() {
      $('.reply-link').click(function() {
        $('#replyId').attr('value', $(this).attr("id"));
        $('#leave-reply-text').text($(this).attr("title"));
      });

      $('.delete-comment').click(function() {
        var postId = $(this).attr("title");
        var func = "delComment";
        var ser = "postId=" +postId+"&func="+func;	
        $.ajax({
            type: "POST",
            url: "Post",
            data: ser,
            success: function(msg) {
				alert(msg);
				location.reload();
            }
        });
      });

      $('#del-post').click(function() {
        var postId = $(this).attr("value");
        var func = "delPost";
        var ser = "postId=" +postId+"&func="+func;  
        $.ajax({
            type: "POST",
            url: "Post",
            data: ser,
            success: function(msg) {
        		alert(msg);
                window.location.replace("./Blog");
            }
        });
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
    <div id="post-wrapper">
      <JPTags:post details="true">
        <div class="post">
          <form method="post" action="Post" enctype="multipart/form-data">
            <h1>
              <button id="del-post" value="$$TARGET$$">Delete</button>
            </h1>
            <h2 class="title">
              <input type="text" name="title" value="$$TITLE$$" class="postInput" placeholder="Title"> 
            </h2>
            <input type="hidden" name="func" value="post">
            <input type="hidden" name="postId" value="$$TARGET$$">
            <input type="file" name="image" id="imgUpl">
            <br><br>
            $$IMAGE$$
            <p>
              <textarea id="postContent" name="content" placeholder="Content">$$CONTENT$$</textarea>
              <input type="text" name="description" value="$$DESC$$" class="postInput" placeholder="Description"> 
            </p>
            <div class="tags">
              <div class="top-border"></div> Tags: 
              <input type="text" value="$$TAGS$$" class="postInput" name="tags" placeholder="Tags">
            </div>
            <br>
            <input type="submit">
          </form>
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
                  <span class="date">- $$DATE$$</span> 
                  <a title="$$ID$$" class="delete-comment" href="#comment-wrapper">Delete</a>
                </div>
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
                <input type="hidden" name="replyId" value="id" id="replyId">
                <input type="hidden" name="func" value="comment">
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