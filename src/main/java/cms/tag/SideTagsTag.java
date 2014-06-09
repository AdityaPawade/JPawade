package cms.tag;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class SideTagsTag extends BodyTagSupport {
	// private static final Logger slf4jLogger =
	// LoggerFactory.getLogger(SideTagsTag.class);
	private static final long serialVersionUID = 1L;
	ArrayList<String> tags;
	int size = 0;
	int index = 0;

	@Override
	public int doStartTag() throws JspException {
		tags = (ArrayList<String>) pageContext.getRequest()
				.getAttribute("tags");

		if (tags == null)
			return SKIP_BODY;

		size = tags.size();
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

		body = body.replace("$$TAG$$", tags.get(index));
		body = body.replace("$$TARGET$$", tags.get(index));

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
