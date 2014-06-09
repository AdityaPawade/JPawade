package jpawade.model;

import java.util.Date;

import jpawade.modules.StaticModules;

public class Comment {
	private int id;
	private int postId;
	private Integer parentId;
	private String message;
	private String name;
	private String email;
	private String website;
	private Date date;

	public void format() {
		this.message = StaticModules.dbFormat(message);
		this.name = StaticModules.dbFormat(name);
		this.email = StaticModules.dbFormat(email);
		this.website = StaticModules.dbFormat(website);
	}

	public Comment(int id, Integer parentId, String message, String name,
			String email, String website, Date date) {
		this.id = id;
		this.parentId = parentId;
		this.message = message;
		this.name = name;
		this.email = email;
		this.website = website;
		this.date = date;
	}

	public Comment(int postId, Integer parentId, String message, String name,
			String email, String website) {
		this.postId = postId;
		this.parentId = parentId;
		this.message = message;
		this.name = name;
		this.email = email;
		this.website = website;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
