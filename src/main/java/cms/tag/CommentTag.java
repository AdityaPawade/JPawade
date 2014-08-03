package cms.tag;

import jpawade.model.BlogPost;
import jpawade.model.Comment;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class CommentTag extends BodyTagSupport {
	// private static final Logger slf4jLogger =
	// LoggerFactory.getLogger(CommentTag.class);
	private static final long serialVersionUID = 1L;
	List<Comment> comments;
	SimpleDateFormat format;
	int size = 0;
	String template = "";
	String temp = "";
	String finalBody = "";

	@Override
	public int doStartTag() throws JspException {
		BlogPost post = (BlogPost) pageContext.getRequest()
				.getAttribute("post");

		if (post == null)
			return SKIP_BODY;

		comments = post.getComments();
		size = post.getCommentsSize();

		template = "";
		temp = "";
		finalBody = "";

		format = new SimpleDateFormat("dd-MM-yyyy");

		if (comments.size() != 0)
			return EVAL_BODY_BUFFERED;
		else
			return SKIP_BODY;
	}

	@Override
	public int doAfterBody() throws JspException {
		JspWriter out = getBodyContent().getEnclosingWriter();
		String body = getBodyContent().getString();

		template = template.concat(body);

		for (int i = 0; i < comments.size(); i++) {
			if (comments.get(i).getParentId() == -1)
				dispComments(i);
		}

		try {
			out.println(finalBody);
		} catch (IOException e) {
			e.printStackTrace();
		}

		getBodyContent().clearBody();
		// index++;

		return SKIP_BODY;
	}

	private void dispComments(int index) {
		finalBody = finalBody.concat("<li class= 'clearfix'>");
		temp = "";
		temp = temp.concat(template);
		temp = temp.replace("$$NAME$$", comments.get(index).getName());
		temp = temp.replace("$$MESSAGE$$", comments.get(index).getMessage());
		temp = temp.replace("$$DATE$$",
				format.format(comments.get(index).getDate()));
		temp = temp.replace("$$ID$$", comments.get(index).getId() + "");

		finalBody = finalBody.concat(temp);

		for (int i = 0; i < comments.size(); i++) {
			if (comments.get(i).getParentId() == comments.get(index).getId()) {
				finalBody = finalBody.concat("<ul class='children'>");
				dispComments(i);
				finalBody = finalBody.concat("</ul>");
			}
		}

		finalBody = finalBody.concat("</li>");
	}
}
