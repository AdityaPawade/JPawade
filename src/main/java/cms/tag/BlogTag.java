package cms.tag;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import jpawade.model.BlogPost;

public class BlogTag extends BodyTagSupport {
	// private static final Logger slf4jLogger =
	// LoggerFactory.getLogger(BlogTag.class);
	private static final long serialVersionUID = 1L;
	SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
	ArrayList<BlogPost> posts;
	int size = 0;
	int index = 0;

	@Override
	public int doStartTag() throws JspException {
		posts = (ArrayList<BlogPost>) pageContext.getRequest().getAttribute(
				"posts");

		if (posts == null)
			return SKIP_BODY;

		size = posts.size();
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

		BlogPost post = posts.get(index);

		body = body.replace("$$TARGET$$", "Post?id=" + post.getId());
		body = body.replace("$$TITLE$$", post.getTitle());
		body = body.replace("$$DATE$$", format.format(post.getDate()));
		body = body.replace("$$DESC$$", post.getDesc() + " ");
		body = body.replace("$$COMMSIZE$$", post.getTotalSize() + "");

		if (post.getPicUrl() != null) {
			body = body.replace("$$IMAGE$$", "<img src='../style/images/"
					+ post.getPicUrl() + "' alt='' />");
		} else {
			body = body.replace("$$IMAGE$$", "");
		}
		String tags = "";
		ArrayList<String> tagList = post.getTags();
		for (String tag : tagList) {
			if (!tags.equals(""))
				tags = tags.concat(",");
			tags = tags.concat("<a href='Blog?tag=" + tag + "' title=''>" + tag
					+ "</a>");
		}
		body = body.replace("$$TAGS$$", tags);

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
