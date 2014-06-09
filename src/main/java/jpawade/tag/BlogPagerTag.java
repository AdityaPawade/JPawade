package jpawade.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class BlogPagerTag extends BodyTagSupport {
	// private static final Logger slf4jLogger =
	// LoggerFactory.getLogger(BlogPagerTag.class);
	private static final long serialVersionUID = 1L;
	int pages = 1;
	Integer pageNo = 1;
	int index = 1;
	String filter = "";

	@Override
	public int doStartTag() throws JspException {
		pageNo = (Integer) pageContext.getRequest().getAttribute("page");
		Double totalPages = (Double) pageContext.getRequest().getAttribute(
				"pages");
		filter = (String) pageContext.getRequest().getAttribute("filter");
		if (filter == null)
			filter = "";

		if (totalPages == null) {
			pages = 1;
		} else {
			pages = totalPages.intValue();
		}

		if (pageNo == null) {
			pageNo = 1;
		}

		index = 1;

		if (pages == 0)
			return SKIP_BODY;

		return EVAL_BODY_BUFFERED;
	}

	@Override
	public int doAfterBody() throws JspException {
		JspWriter out = getBodyContent().getEnclosingWriter();
		String body = getBodyContent().getString();

		if (index == pageNo) {
			body = body.replace("$$PAGER$$", "<li><a href='Blog?" + filter
					+ "page=" + index + "' class='current'>" + index
					+ "</a></li>");
		} else {
			body = body.replace("$$PAGER$$", "<li><a href='Blog?" + filter
					+ "page=" + index + "'>" + index + "</a></li>");
		}
		index++;

		if (index > pages) {
			int nextPage = 1;
			if (pageNo == (index - 1)) {
				nextPage = pageNo;
			} else {
				nextPage = pageNo + 1;
			}
			body = body.concat("<li><a href='Blog?" + filter + "page="
					+ nextPage + "''>&raquo;</a></li>");
		}

		try {
			out.println(body);
		} catch (IOException e) {
			e.printStackTrace();
		}

		getBodyContent().clearBody();

		if (index <= pages)
			return EVAL_BODY_AGAIN;
		else
			return SKIP_BODY;
	}
}
