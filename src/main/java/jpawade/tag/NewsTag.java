package jpawade.tag;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import jpawade.model.News;

public class NewsTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;
	ArrayList<News> news;
	SimpleDateFormat format = new SimpleDateFormat("MMMM dd,yyyy");
	int size = 0;
	int index = 0;

	@Override
	public int doStartTag() throws JspException {
		news = (ArrayList<News>) pageContext.getRequest().getAttribute("news");

		if (news == null)
			return SKIP_BODY;

		size = news.size();
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

		News currentNews = news.get(index);

		body = body.replace("$$DATE$$", format.format(currentNews.getDate()));
		body = body.replace("$$TITLE$$", currentNews.getTitle());
		body = body.replace("$$MESSAGE$$", currentNews.getMessage());

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
