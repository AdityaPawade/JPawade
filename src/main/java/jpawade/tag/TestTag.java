package jpawade.tag;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import jpawade.model.BlogPost;

public class TestTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;
	ArrayList<BlogPost> posts;
	int size = 0;

	@Override
	public int doStartTag() throws JspException {
		posts = (ArrayList<BlogPost>) pageContext.getRequest().getAttribute(
				"posts");
		size = posts.size();
		return EVAL_BODY_BUFFERED;
	}

	@Override
	public int doAfterBody() throws JspException {
		JspWriter out = getBodyContent().getEnclosingWriter();
		String body = getBodyContent().getString();

		if (size == 3)
			body = body.replace("$$Title$$", "1st Blog Post");
		if (size == 2)
			body = body.replace("$$Date$$", "2013 - 06 - 12");
		if (size == 1)
			body = body.replace("$$Content$$", "Content of blog post");

		try {
			out.println(body.substring(size) + " " + size + "<br/>");
		} catch (IOException e) {
			e.printStackTrace();
		}

		getBodyContent().clearBody();
		size--;
		if (size >= 0)
			return EVAL_BODY_AGAIN;
		else
			return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {

		return EVAL_PAGE;
	}

}
