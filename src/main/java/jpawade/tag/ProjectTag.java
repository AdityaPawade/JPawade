package jpawade.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import jpawade.model.Project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory
			.getLogger(ProjectTag.class);

	@Override
	public int doAfterBody() throws JspException {
		JspWriter out = getBodyContent().getEnclosingWriter();
		String body = getBodyContent().getString();

		Project project = (Project) pageContext.getRequest().getAttribute(
				"project");

		if (project == null)
			return SKIP_BODY;

		body = body.replace("$$TITLE$$", project.getTitle());
		body = body.replace("$$NEXT$$", project.getNext() + "");
		body = body.replace("$$PREVIOUS$$", project.getPrevious() + "");
		body = body.replace("$$DESCRIPTION$$", project.getDescription());
		String url = project.getUrl();
		if (url == null) {
			body = body.replace("$$URL$$", "");
		} else {
			body = body.replace("$$URL$$", "<a href='" + url + "'>" + url
					+ "</a>");
		}

		String pics = "";
		for (String pic_url : project.getPicUrls()) {
			pics = pics.concat("<img src='style/images/" + pic_url
					+ "' alt='' />");
		}
		body = body.replace("$$IMAGES$$", pics);

		try {
			out.println(body);
		} catch (IOException e) {
			slf4jLogger.debug("Project tag exception : " + e.getMessage());
		}

		getBodyContent().clearBody();
		return SKIP_BODY;
	}
}
