<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<%@ taglib uri="JPTcms" prefix="JPTags"
 %>

<!DOCTYPE HTML>
<html lang="en-US">
<head>
<meta charset="UTF-8">
<title>JPawade Project</title>
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
<div id="container"> 
  <!-- Begin Header Wrapper -->
  <%@ include file="header.jsp" %>
  <!-- End Header Wrapper --> 
  
  <!-- Begin Wrapper -->
  <div id="wrapper">
  
    <div id="portfolio-single">
      <form method="post" enctype="multipart/form-data">
        <JPTags:project>
        <div class="image">
          <button id="removeProj" type="button">Remove Project</button>
        	<input id="imgUpl" name="image" type="file" multiple><br><br>
          <div id="projectImages">
          	$$IMAGES$$
          </div>
        </div>
        <div class="text">
          <h3>
          	<input placeholder="Title" class="projectInp" name="title" type="text" value="$$TITLE$$">
          </h3>
          <p>
          	<textarea placeholder="Description" id="projectDesc" name="desc">$$DESCRIPTION$$</textarea>
          </p>
          <input placeholder="URL" class="projectInp" type="text" name="url" value="$$URL$$">
          <input type="hidden" id="projectId" name="projectId" value="$$ID$$">
          <input type="hidden" name="func" value="modify">
          $$SERVICES$$
          <input id="projectSubmit" type="submit">
          <div class="divider3"></div>
          <a href="Project?id=$$PREVIOUS$$" class="p-project">&laquo; Previous Project</a> <a href="Project?id=$$NEXT$$" class="n-project">Next Project &raquo;</a>
        </div>
        </JPTags:project>
      </form>
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
    
    function readURL(input) {
      $('#projectImages').html("");
      if (input.files) {
        var images = "";
        
        for (var i = 0; i < input.files.length; i++) {
          var reader = new FileReader();
          reader.onload = function (e) {
              $('#projectImages').html($('#projectImages').html() + "<img src='" + e.target.result + "' alt='' />")
          }
          reader.readAsDataURL(input.files[i]);
        };
      }
    }

	$(document).ready(function(){
		$("#gallery a[rel^='prettyPhoto']").prettyPhoto({theme:'light_square',autoplay_slideshow: false});

      	$("#imgUpl").change(function(){
          readURL(this);
      	});

      	$("#removeProj").click(function() {
	        var id = $('#projectId').val();
	        var ser = "projectId=" +id+"&func=remove";
	        $.ajax({
	              type: "POST",
	              url: "Project",
	              data: ser,
	              success: function(msg) {
	                alert(msg);
	                if(msg == 'OK')
	                  window.location.replace("./Portfolio");
	              }
	        });
      	});
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