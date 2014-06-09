package jpawade.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import jpawade.model.Hosted;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HostedTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory
			.getLogger(HostedTag.class);

	@Override
	public int doAfterBody() throws JspException {
		JspWriter out = getBodyContent().getEnclosingWriter();
		String body = getBodyContent().getString();

		Hosted hosted = (Hosted) pageContext.getRequest().getAttribute(
				"hosted");

		if (hosted == null)
			return SKIP_BODY;

		body = body.replace("$$TITLE$$", hosted.getName());
		body = body.replace("$$PATH$$",
				hosted.getType() + "/" + hosted.getPath());

		try {
			out.println(body);
		} catch (IOException e) {
			slf4jLogger.debug("Hosted tag exception : " + e.getMessage());
		}

		getBodyContent().clearBody();
		return SKIP_BODY;
	}
}
