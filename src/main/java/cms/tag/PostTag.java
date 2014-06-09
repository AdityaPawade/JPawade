package cms.tag;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import jpawade.model.BlogPost;

public class PostTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;
	SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
	boolean details = false;

	public void setDetails(boolean details) {
		this.details = details;
	}

	@Override
	public int doAfterBody() throws JspException {
		JspWriter out = getBodyContent().getEnclosingWriter();
		String body = getBodyContent().getString();

		BlogPost post = (BlogPost) pageContext.getRequest()
				.getAttribute("post");

		if (post != null) {

			post.escape();

			body = body.replace("$$TITLE$$", post.getTitle());
			body = body
					.replace("$$COMMSIZE$$", post.getComments().size() + " ");
			body = body.replace("$$TARGET$$", post.getId() + "");

			if (details) {
				body = body.replace("$$DATE$$", format.format(post.getDate()));
				body = body.replace("$$CONTENT$$", post.getContent());
				body = body.replace("$$DESC$$", post.getDesc());
				if (post.getPicUrl() != null) {
					body = body.replace("$$IMAGE$$",
							"<img src='../style/images/" + post.getPicUrl()
									+ "' alt='' id='imgPreview' />");
				} else {
					body = body.replace("$$IMAGE$$",
							"<img src='' alt='' id='imgPreview' />");
				}
				String tags = "";
				ArrayList<String> tagList = post.getTags();
				for (String tag : tagList) {
					if (!tags.equals(""))
						tags = tags.concat(",");
					tags = tags.concat(tag);
				}
				body = body.replace("$$TAGS$$", tags);
			}
		} else {
			body = body.replace("$$TITLE$$", "");
			body = body.replace("$$COMMSIZE$$", "0 ");
			body = body.replace("$$TARGET$$", "-1");

			body = body.replace("$$DATE$$", "");
			body = body.replace("$$DESC$$", "");
			body = body.replace("$$CONTENT$$", "");
			body = body.replace("$$IMAGE$$",
					"<img src='' alt='' id='imgPreview' />");

			body = body.replace("$$TAGS$$", "");
		}
		try {
			out.println(body);
		} catch (IOException e) {
			e.printStackTrace();
		}

		getBodyContent().clearBody();
		return SKIP_BODY;
	}
}
