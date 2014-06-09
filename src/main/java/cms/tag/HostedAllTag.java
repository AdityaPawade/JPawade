package cms.tag;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import jpawade.model.Hosted;

public class HostedAllTag extends BodyTagSupport {
	// private static final Logger slf4jLogger =
	// LoggerFactory.getLogger(BlogTag.class);
	private static final long serialVersionUID = 1L;
	ArrayList<Hosted> hosted;
	int size = 0;
	int index = 0;

	@Override
	public int doStartTag() throws JspException {
		hosted = (ArrayList<Hosted>) pageContext.getRequest().getAttribute(
				"allHosted");

		if (hosted == null)
			return SKIP_BODY;

		size = hosted.size();
		index = 0;

		if (index < size)
			return EVAL_BODY_BUFFERED;
		else
			return SKIP_BODY;
	}

	@Override
	public int doAfterBody() throws JspException {
		JspWriter out = getBodyContent().getEnclosingWriter();
		String body = getBodyContent().getString();

		Hosted host = hosted.get(index);

		body = body.replace("$$PATH$$", host.getPath());
		body = body.replace("$$TYPE$$", host.getType() + " ");
		body = body.replace("$$EDIT$$", "<a href='Hosted?id=" + host.getId()
				+ "'>" + host.getName() + "</a>");
		body = body.replace("$$LINK$$", "<a href='../Hosted?id=" + host.getId()
				+ "'>" + host.getName() + "</a>");

		try {
			out.println(body);
		} catch (IOException e) {
			e.printStackTrace();
		}

		getBodyContent().clearBody();
		index++;
		if (index < size)
			return EVAL_BODY_AGAIN;
		else
			return SKIP_BODY;
	}
}
