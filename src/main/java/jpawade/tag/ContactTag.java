package jpawade.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import jpawade.model.Capcha;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContactTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory
			.getLogger(ContactTag.class);

	@Override
	public int doAfterBody() throws JspException {
		JspWriter out = getBodyContent().getEnclosingWriter();
		String body = getBodyContent().getString();

		Capcha capcha = (Capcha) pageContext.getRequest()
				.getAttribute("capcha");

		if (capcha == null)
			return SKIP_BODY;

		body = body.replace("$$CAPID$$", capcha.getId() + "");
		body = body.replace("$$QUES$$", capcha.getQuestion());

		try {
			out.println(body);
		} catch (IOException e) {
			slf4jLogger.debug("Project tag exception : " + e.getMessage());
		}

		getBodyContent().clearBody();
		return SKIP_BODY;
	}
}
