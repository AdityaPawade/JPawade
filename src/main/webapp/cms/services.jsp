<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<%@ taglib uri="JPTcms" prefix="JPTags"
 %>

<!DOCTYPE HTML>
<html lang="en-US">
<head>
<meta charset="UTF-8">
<title>JPawade Services</title>
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
    <div class="tab-wrapper">
      <ul id="tab-menu">
        <li class="selected"><img src="../style/images/services/mobile.png" alt="" /> Mobile Applications</li>
        <li><img src="../style/images/services/web.jpg" alt="" /> Web Development</li>
        <li><img src="../style/images/services/desktop.png" alt="" /> Desktop Applications</li>
        <li><img src="../style/images/services/game.jpg" alt="" /> Game Development</li>
        <li><img src="../style/images/services/animation.png" alt="" /> Animation</li>
      </ul>
      <div class="tab-content">
        <div class="tab show">
          <h3>Mobile Applications</h3>
          <div class="one-half">
            <div class="one-fifth"><img src="../style/images/services/android-app.jpg" /></div>
            <div class="three-fourth last">
              <h4>Android Applications</h4>
              <p>Android is a Linux-based OS designed for touchscreen mobile devices. Applications make use of Google APIs and other Web Services</p>
            </div>
          </div>
          <div class="one-half last">
            <div class="one-fifth"><img src="../style/images/services/android-game.png" /></div>
            <div class="three-fourth last">
              <h4>Android Games</h4>
              <p>3D and 2D Games for the Android OS. Games have been made using OpenGL ES. These include First Person Shooters or Side Scrollers.</p>
            </div>
          </div>
          <div class="clearfix"></div>
        </div>
        <div class="tab">
          <h3>Web Development</h3>
          <div class="one-half">
            <div class="one-fifth"><img src="../style/images/services/jsp.jpg" /></div>
            <div class="three-fourth last">
              <h4>JSP</h4>
              <p>JSP is a technology used to create dynamically generated web pages using Java. To deploy and run JavaServer Pages, a compatible web server like Apache Tomcat or Jetty, is required.</p>
            </div>
          </div>
          <div class="one-half last">
            <div class="one-fifth"><img src="../style/images/services/php.png" /></div>
            <div class="three-fourth last">
              <h4>PHP</h4>
              <p>PHP is a server-side scripting language designed for web development. PHP can be deployed on most web servers free of charge.</p>
            </div>
          </div>
          <div class="clearfix"></div>
          <div class="divider2"></div>
          <div class="one-half">
            <div class="one-fifth"><img src="../style/images/services/asp.net.png" /></div>
            <div class="three-fourth last">
              <h4>ASP.NET</h4>
              <p>ASP.NET is a server-side Web application framework designed for Web development to produce dynamic Web pages. It was developed by Microsoft as a successor to ASP technology.</p>
            </div>
          </div>
          <div class="clearfix"></div>
        </div>
        <div class="tab">
          <h3>Desktop Applications</h3>
          <div class="one-half">
            <div class="one-fifth"><img src="../style/images/services/javafx.jpg" /></div>
            <div class="three-fourth last">
              <h4>JavaFX</h4>
              <p>JavaFX is a software platform for creating and delivering rich internet applications that can run across a wide variety of devices.</p>
            </div>
          </div>
          <div class="one-half last">
            <div class="one-fifth"><img src="../style/images/services/swing.gif" /></div>
            <div class="three-fourth last">
              <h4>Swing</h4>
              <p>Swing is the primary Java GUI widget toolkit. It is an API for providing a graphical user interface (GUI) for Java programs.</p>
            </div>
          </div>
          <div class="clearfix"></div>
        </div>
        <div class="tab">
          <h3>Game Development</h3>
          <div class="one-half">
            <div class="one-fifth"><img src="../style/images/services/opengl.jpg" /></div>
            <div class="three-fourth last">
              <h4>OpenGL</h4>
              <p>OpenGL (Open Graphics Library) is a cross-language, multi-platform API for rendering 2D and 3D computer graphics.</p>
            </div>
          </div>
          <div class="one-half last">
            <div class="one-fifth"><img src="../style/images/services/opengles.png" /></div>
            <div class="three-fourth last">
              <h4>OpenGL ES</h4>
              <p>OpenGL ES is a subset of the OpenGL computer graphics rendering API for rendering 2D and 3D graphics for embedded systems like smart phones and tablets</p>
            </div>
          </div>
          <div class="clearfix"></div>
          <div class="divider2"></div>
          <div class="one-half">
            <div class="one-fifth"><img src="../style/images/services/pyopengl.png" /></div>
            <div class="three-fourth last">
              <h4>PyOpenGL</h4>
              <p>PyOpenGL is the most common cross platform Python binding to OpenGL and related APIs. The binding is created using the standard ctypes library.</p>
            </div>
          </div>
          <div class="one-half last">
            <div class="one-fifth"><img src="../style/images/services/flash.jpg" /></div>
            <div class="three-fourth last">
              <h4>Flash</h4>
              <p>Flash is a multimedia and software platform used for authoring of vector graphics, animation, games and rich Internet applications (RIAs) that can be viewed, played and executed in Adobe Flash Player.</p>
            </div>
          </div>
          <div class="clearfix"></div>
        </div>
        <div class="tab">
          <h3>Animation</h3>
          <div class="one-half">
            <div class="one-fifth"><img src="../style/images/services/blender.png" /></div>
            <div class="three-fourth last">
              <h4>3D Animation</h4>
              <p>3D Animation using tools like blender. All the models for the games have been made using Blender, which is a free 3D modelling tool.</p>
            </div>
          </div>
          <div class="one-half last">
            <div class="one-fifth"><img src="../style/images/services/flash.jpg" /></div>
            <div class="three-fourth last">
              <h4>2D Animation</h4>
              <p>2D Animation using flash. Flash manipulates vector and raster graphics to provide animation of text, drawings, and still images.</p>
            </div>
          </div>
          <div class="clearfix"></div>
        </div>
      </div>
    </div>
    <div class="clear"></div>
  </div>
  <!-- End Wrapper -->
  
  <div class="clearfix"></div>
  <div class="push"></div>
</div>

<!-- Begin Footer -->
<%@ include file="footer.jsp" %>
<!-- End Footer --> 

<script type="text/javascript">

$(document).ready(function() {	
  //Get all the LI from the #tabMenu UL
  $('#tab-menu > li').click(function(){
    //remove the selected class from all LI    
    $('#tab-menu > li').removeClass('selected');
    //Reassign the LI
    $(this).addClass('selected');
    //Hide all the DIV in .tab-content
    $('.tab-content div.tab').slideUp('slow');
    //Look for the right DIV in boxBody according to the Navigation UL index, therefore, the arrangement is very important.
    $('.tab-content div.tab:eq(' + $('#tab-menu > li').index(this) + ')').slideDown('slow');
  }).mouseover(function() {
    //Add and remove class, Personally I dont think this is the right way to do it, anyone please suggest    
    $(this).addClass('mouseover');
    $(this).removeClass('mouseout');   
  }).mouseout(function() {
    //Add and remove class
    $(this).addClass('mouseout');
    $(this).removeClass('mouseover');    
  });
});


$(function() {
            var offset = $("#tab-menu").offset();
            var topPadding = 15;
            $(window).scroll(function() {
                if ($(window).scrollTop() > offset.top) {
                    $("#tab-menu").stop().animate({
                        marginTop: $(window).scrollTop() - offset.top + topPadding
                    });
                } else {
                    $("#tab-menu").stop().animate({
                        marginTop: 0
                    });
                };
            });
});
</script>
</body>
</html>