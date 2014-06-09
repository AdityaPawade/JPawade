package cms.tag;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import jpawade.model.Project;

public class PortfolioProjectTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;
	ArrayList<Project> projects;
	int size = 0;
	int index = 0;

	@Override
	public int doStartTag() throws JspException {
		projects = (ArrayList<Project>) pageContext.getRequest().getAttribute(
				"projects");

		if (projects == null)
			return SKIP_BODY;

		size = projects.size();
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

		Project project = projects.get(index);

		body = body.replace("$$ID$$", project.getId() + "");
		body = body.replace("$$SERVICE$$",
				project.getService().replace(' ', '_'));
		body = body.replace("$$TITLE$$", project.getTitle());
		body = body.replace("$$IMAGE$$", project.getSinglePic());

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
