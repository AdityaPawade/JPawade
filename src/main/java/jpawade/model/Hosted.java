package jpawade.model;

import jpawade.modules.StaticModules;

public class Hosted {
	private int id;
	private String name;
	private String path;
	private String type;

	public void format() {
		name = StaticModules.dbFormat(name);
		type = StaticModules.dbFormat(type);
		path = StaticModules.dbFormat(path);
	}

	public void escape() {
		name = StaticModules.escapeHtml(name);
		type = StaticModules.escapeHtml(type);
		path = StaticModules.escapeHtml(path);
	}

	public Hosted(String name, String path, String type) {
		this.name = name;
		this.path = path;
		this.type = type;
	}

	public Hosted(int id, String name, String path, String type) {
		this.id = id;
		this.name = name;
		this.path = path;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
