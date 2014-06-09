package jpawade.model;

public class Archive {
	private int count;
	private String date;

	public Archive(int count, String date) {
		this.count = count;
		this.date = date;
	}

	public String getArchive() {
		return (this.date + " (" + this.count + ")");
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
