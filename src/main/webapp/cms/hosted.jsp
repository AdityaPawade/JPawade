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
<script type="text/javascript">
$(document).ready(function(){
	$("#removeHosted").click(function() {
        var id = $('#hostedId').val();
        var ser = "id=" +id+"&func=remove";
        $.ajax({
              type: "POST",
              url: "Hosted",
              data: ser,
              success: function(msg) {
                alert(msg);
                window.location.replace("./Hosted");
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
  
    <div id="portfolio-single">
      <JPTags:hosted>
        <div class="hostedList">
          <table>
              <tr>
                <th>Link</th><th>Type</th><th>File</th><th>Edit</th>
              </tr>
              <JPTags:allHosted>
	              <tr>
	                <td>$$LINK$$</td><td>$$TYPE$$</td><td>$$PATH$$</td><td>$$EDIT$$</td>
	              </tr>
              </JPTags:allHosted>
          </table>
        </div>
      <form method="post" enctype="multipart/form-data">
        <div class="details">
          <h3>
          	<input placeholder="Name" class="projectInp" name="name" type="text" value="$$NAME$$">
          </h3>
          <p>
            <input type="text" placeholder="dummy" class="projectInp" value="$$PATH$$">
          </p>
          <p>
            <input id="fileUpl" name="file" type="file" multiple>
          </p>
          <p>
          	<input id="hostedId" class="projectInp" name="id" type="hidden" value="$$ID$$">
          	<input id="hostedId" class="projectInp" name="func" type="hidden" value="modify">
          	<input placeholder="Type" class="projectInp" name="type" type="text" value="$$TYPE$$">
          </p>
          <button id="removeHosted" value="Remove">Remove</button>
          <input id="projectSubmit" type="submit">
		 </div>
      </form>
      </JPTags:hosted>
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