package cms.tag;

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
			if (path.equals("/cms/index.jsp"))
				out.println("<li><a href='./' class='selected'>Home</a></li>");
			else
				out.println("<li><a href='./'>Home</a></li>");

			if (path.equals("/cms/blog.jsp")
					|| path.equals("/cms/blog-post.jsp"))
				out.println("<li><a href='Blog' class='selected'>Blog</a>"
						+ "<ul><li><a href='Post'>Post</a></li></ul>"
						+ "</li>");
			else
				out.println("<li><a href='Blog'>Blog</a>"
						+ "<ul><li><a href='Post'>Post</a></li></ul>"
						+ "</li>");

			if (path.equals("/cms/portfolio.jsp")
					|| path.equals("/cms/project.jsp"))
				out.println("<li><a href='Portfolio' class='selected'>Portfolio</a>"
						+ "<ul><li><a href='Project'>Project</a></li></ul>"
						+ "</li>");
			else
				out.println("<li><a href='Portfolio'>Portfolio</a>"
						+ "<ul><li><a href='Project'>Project</a></li></ul>"
						+ "</li>");

			if (path.equals("/cms/hosted.jsp"))
				out.println("<li><a href='Hosted' class='selected'>Hosted</a></li>");
			else
				out.println("<li><a href='Hosted'>Hosted</a></li>");

			if (path.equals("/cms/services.jsp"))
				out.println("<li><a href='Services' class='selected'>Services</a></li>");
			else
				out.println("<li><a href='Services'>Services</a></li>");

			if (path.equals("/cms/news.jsp"))
				out.println("<li><a href='News' class='selected'>News</a></li>");
			else
				out.println("<li><a href='News'>News</a></li>");

			if (path.equals("/cms/contact.jsp"))
				out.println("<li><a href='Contact' class='selected'>Contact</a></li>");
			else
				out.println("<li><a href='Contact'>Contact</a></li>");

			out.println("<li><a href='Login?ext=1'>Logout</a></li>");

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
