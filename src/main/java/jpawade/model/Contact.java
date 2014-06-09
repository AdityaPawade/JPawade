package jpawade.model;

import java.util.Date;

import jpawade.modules.StaticModules;

public class Contact {
	private int id;
	private String name;
	private String email;
	private String message;
	private String website;
	private Date date;

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public void format() {
		name = StaticModules.dbFormat(name);
		email = StaticModules.dbFormat(email);
		message = StaticModules.dbFormat(message);
	}

	public Contact(int id, String name, String email, String message,
			String website, Date date) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.message = message;
		this.website = website;
		this.date = date;
	}

	public Contact(String name, String email, String message, String website) {
		this.name = name;
		this.email = email;
		this.message = message;
		this.website = website;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
}
