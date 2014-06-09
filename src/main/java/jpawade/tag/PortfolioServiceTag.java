package jpawade.tag;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class PortfolioServiceTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;
	ArrayList<String> services;
	int size = 0;
	int index = 0;

	@Override
	public int doStartTag() throws JspException {
		services = (ArrayList<String>) pageContext.getRequest().getAttribute(
				"services");

		if (services == null)
			return SKIP_BODY;

		size = services.size();
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

		body = body.replace("$$SERVICE$$", services.get(index));
		body = body.replace("$$SERVICECLASS$$",
				services.get(index).replace(' ', '_'));

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
