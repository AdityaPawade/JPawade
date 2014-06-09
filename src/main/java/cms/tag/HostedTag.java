package cms.tag;

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

		if (hosted != null) {
			hosted.escape();
			body = body.replace("$$ID$$", hosted.getId() + "");
			body = body.replace("$$NAME$$", hosted.getName());
			body = body.replace("$$TYPE$$", hosted.getType());
			body = body.replace("$$PATH$$", hosted.getPath());

		} else {
			body = body.replace("$$ID$$", "-1");
			body = body.replace("$$NAME$$", "");
			body = body.replace("$$TYPE$$", "");
			body = body.replace("$$PATH$$", "");
		}

		try {
			out.println(body);
		} catch (IOException e) {
			slf4jLogger.debug("Hosted tag exception : " + e.getMessage());
		}

		getBodyContent().clearBody();
		return SKIP_BODY;
	}
}
