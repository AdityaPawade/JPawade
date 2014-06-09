package jpawade.model;

import jpawade.modules.StaticModules;

public class Tag {
	public int post_id;
	public String tag;

	public void format() {
		tag = StaticModules.dbFormat(tag);
	}

	public Tag(int post_id, String tag) {
		this.post_id = post_id;
		this.tag = tag;
	}
}
