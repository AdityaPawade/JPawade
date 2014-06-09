package jpawade.model;

import java.util.Date;

import jpawade.modules.StaticModules;

public class News {
	private int id;
	private String title;
	private String message;
	private Date date;

	public void format() {
		title = StaticModules.dbFormat(title);
		message = StaticModules.dbFormat(message);
	}

	public News(int id, String title, String message, Date date) {
		this.id = id;
		this.title = title;
		this.message = message;
		this.date = date;
	}

	public News(String title, String message) {
		this.title = title;
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
