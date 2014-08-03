package jpawade.model;

import jpawade.modules.StaticModules;

import java.util.Date;
import java.util.List;

public class BlogPost {
	private int id;
	private Date date;
	private String title;
	private String content;
	private String picUrl;
	private String desc;
	private List<String> tags;
	private List<Comment> comments;
	private int commentsSize;
	private int totalSize;

	public void format() {
		title = StaticModules.dbFormat(title);
		content = StaticModules.dbFormat(content);
		picUrl = StaticModules.dbFormat(picUrl);
		desc = StaticModules.dbFormat(desc);
	}

	public void escape() {
		title = StaticModules.escapeHtml(title);
		content = StaticModules.escapeHtml(content);
		picUrl = StaticModules.escapeHtml(picUrl);
		desc = StaticModules.escapeHtml(desc);
	}

	public BlogPost(int id, Date date, String title, String picUrl,
			String text, int totalSize, List<String> tags, Boolean single) {
		this.id = id;
		this.date = date;
		this.title = title;
		this.picUrl = picUrl;
		this.tags = tags;
		this.totalSize = totalSize;

		if (single)
			this.content = text;
		else
			this.desc = text;
	}

	public BlogPost(int id, Date date, String title, String picUrl,
			String content, String desc, int totalSize, List<String> tags) {
		this.id = id;
		this.date = date;
		this.title = title;
		this.picUrl = picUrl;
		this.tags = tags;
		this.totalSize = totalSize;
		this.content = content;
		this.desc = desc;
	}

	public BlogPost(int id, Date date, String title, String content,
			String picUrl, String desc, List<String> tags) {
		this.id = id;
		this.date = date;
		this.title = title;
		this.content = content;
		this.picUrl = picUrl;
		this.desc = desc;
		this.tags = tags;
	}

	public BlogPost(int id, Date date, String title, int totalSize) {
		this.id = id;
		this.date = date;
		this.title = title;
		this.totalSize = totalSize;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public BlogPost() {
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public int getCommentsSize() {
		return commentsSize;
	}

	public void setCommentsSize(int commentsSize) {
		this.commentsSize = commentsSize;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

}
