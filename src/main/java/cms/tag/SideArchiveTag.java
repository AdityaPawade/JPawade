package cms.tag;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import jpawade.model.Archive;

public class SideArchiveTag extends BodyTagSupport {
	// private static final Logger slf4jLogger =
	// LoggerFactory.getLogger(SideArchiveTag.class);
	private static final long serialVersionUID = 1L;
	ArrayList<Archive> archives;
	int size = 0;
	int index = 0;

	@Override
	public int doStartTag() throws JspException {
		archives = (ArrayList<Archive>) pageContext.getRequest().getAttribute(
				"archives");

		if (archives == null)
			return SKIP_BODY;

		size = archives.size();
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

		body = body.replace("$$ARCHIVE$$", archives.get(index).getArchive());
		body = body.replace("$$TARGET$$", archives.get(index).getDate());

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
