package jpawade.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeaderTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory
			.getLogger(HeaderTag.class);
	private String path;

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();

		try {
			if (path.equals("/index.jsp"))
				out.println("<li><a href='./' class='selected'>Home</a></li>");
			else
				out.println("<li><a href='./'>Home</a></li>");
			if (path.equals("/blog.jsp") || path.equals("/blog-post.jsp"))
				out.println("<li><a href='Blog' class='selected'>Blog</a></li>");
			else
				out.println("<li><a href='Blog'>Blog</a></li>");
			if (path.equals("/portfolio.jsp") || path.equals("/project.jsp"))
				out.println("<li><a href='Portfolio' class='selected'>Portfolio</a></li>");
			else
				out.println("<li><a href='Portfolio'>Portfolio</a></li>");
			if (path.equals("/services.jsp"))
				out.println("<li><a href='Services' class='selected'>Services</a></li>");
			else
				out.println("<li><a href='Services'>Services</a></li>");
			if (path.equals("/news.jsp"))
				out.println("<li><a href='News' class='selected'>News</a></li>");
			else
				out.println("<li><a href='News'>News</a></li>");
			if (path.equals("/contact.jsp"))
				out.println("<li><a href='Contact' class='selected'>Contact</a></li>");
			else
				out.println("<li><a href='Contact'>Contact</a></li>");
		} catch (IOException e) {
			slf4jLogger.debug("Header IO Error : " + e.getMessage());
		}

		return SKIP_BODY;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
