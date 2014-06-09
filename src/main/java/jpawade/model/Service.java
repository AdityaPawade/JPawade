package jpawade.model;

import jpawade.modules.StaticModules;

public class Service {
	private int id;
	private String service;

	public void format() {
		service = StaticModules.dbFormat(service);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public Service(int id, String service) {
		this.id = id;
		this.service = service;
	}

}
