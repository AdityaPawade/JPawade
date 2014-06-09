package cms.tag;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import jpawade.model.Contact;

public class ContactTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;
	ArrayList<Contact> messages;
	SimpleDateFormat format = new SimpleDateFormat("MMMM dd,yyyy");
	int size = 0;
	int index = 0;

	@Override
	public int doStartTag() throws JspException {
		messages = (ArrayList<Contact>) pageContext.getRequest().getAttribute(
				"messages");

		if (messages == null)
			return SKIP_BODY;

		size = messages.size();
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

		Contact currentMessage = messages.get(index);

		body = body
				.replace("$$DATE$$", format.format(currentMessage.getDate()));
		body = body.replace("$$NAME$$", currentMessage.getName());
		body = body.replace("$$EMAIL$$", currentMessage.getEmail());
		body = body.replace("$$ID$$", currentMessage.getId() + "");
		body = body.replace("$$MESSAGE$$", currentMessage.getMessage());
		if (currentMessage.getWebsite() != null)
			body = body.replace("$$WEBSITE$$", currentMessage.getWebsite());
		else
			body = body.replace("$$WEBSITE$$", "");

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
