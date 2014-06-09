package cms.tag;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import jpawade.model.Project;
import jpawade.model.Service;

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
		ArrayList<Service> services = (ArrayList<Service>) pageContext
				.getRequest().getAttribute("services");

		if (project != null) {
			project.escape();
			body = body.replace("$$TITLE$$", project.getTitle());
			body = body.replace("$$ID$$", project.getId() + "");
			body = body.replace("$$NEXT$$", project.getNext() + "");
			body = body.replace("$$PREVIOUS$$", project.getPrevious() + "");
			body = body.replace("$$DESCRIPTION$$", project.getDescription());
			String url = project.getUrl();
			if (url == null) {
				body = body.replace("$$URL$$", "");
			} else {
				body = body.replace("$$URL$$", url);
			}

			String pics = "";
			for (String pic_url : project.getPicUrls()) {
				pics = pics.concat("<img src='../style/images/" + pic_url
						+ "' alt='' />");
			}
			body = body.replace("$$IMAGES$$", pics);

			String options = "<select id='projectService' name='service'>";

			options = options.concat("<option value='" + project.getServiceId()
					+ "'>" + project.getService() + "</option>");

			for (Service service : services) {
				if (service.getId() != project.getServiceId())
					options = options.concat("<option value='"
							+ service.getId() + "'>" + service.getService()
							+ "</option>");
			}
			options = options.concat("</select>");
			body = body.replace("$$SERVICES$$", options);

		} else {
			body = body.replace("$$TITLE$$", "");
			body = body.replace("$$ID$$", "-1");
			body = body.replace("$$NEXT$$", "");
			body = body.replace("$$PREVIOUS$$", "");
			body = body.replace("$$DESCRIPTION$$", "");
			body = body.replace("$$URL$$", "");
			body = body.replace("$$IMAGES$$", "");

			String options = "";
			options = options
					.concat("<select id='projectService' name='service'>");
			for (Service service : services) {
				options = options.concat("<option value='" + service.getId()
						+ "'>" + service.getService() + "</option>");
			}
			options = options.concat("</select>");
			body = body.replace("$$SERVICES$$", options);

		}

		try {
			out.println(body);
		} catch (IOException e) {
			slf4jLogger.debug("Project tag exception : " + e.getMessage());
		}

		getBodyContent().clearBody();
		return SKIP_BODY;
	}
}
