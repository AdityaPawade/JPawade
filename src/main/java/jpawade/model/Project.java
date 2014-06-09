package jpawade.model;

import java.util.ArrayList;

import jpawade.modules.StaticModules;

public class Project {
	private int id;
	private int next;
	private int previous;
	private int serviceId;
	private String title;
	private String description;
	private String url;
	private String service;
	private ArrayList<String> picUrls;
	private String singlePic;

	public void format() {
		title = StaticModules.dbFormat(title);
		description = StaticModules.dbFormat(description);
		url = StaticModules.dbFormat(url);
		service = StaticModules.dbFormat(service);
		singlePic = StaticModules.dbFormat(singlePic);
	}

	public void escape() {
		title = StaticModules.escapeHtml(title);
		description = StaticModules.escapeHtml(description);
		url = StaticModules.escapeHtml(url);
		service = StaticModules.escapeHtml(service);
		singlePic = StaticModules.escapeHtml(singlePic);
	}

	public Project(String title, String description, String url, String service) {
		this.title = title;
		this.description = description;
		this.url = url;
		this.service = service;
	}

	public Project(int id, String title, String description, String url,
			int serviceId) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.url = url;
		this.serviceId = serviceId;
	}

	public Project(int id, String title, String description, String url,
			String service) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.url = url;
		this.service = service;
	}

	public Project(int id, String title, String service, String singlePic) {
		this.id = id;
		this.title = title;
		this.service = service;
		this.singlePic = singlePic;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	public int getPrevious() {
		return previous;
	}

	public void setPrevious(int previous) {
		this.previous = previous;
	}

	public String getSinglePic() {
		return singlePic;
	}

	public void setSinglePic(String singlePic) {
		this.singlePic = singlePic;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public ArrayList<String> getPicUrls() {
		return picUrls;
	}

	public void setPicUrls(ArrayList<String> picUrls) {
		this.picUrls = picUrls;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

}
