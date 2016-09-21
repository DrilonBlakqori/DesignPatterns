package drilon.com.designpatterns.model;

public class PatternModel {

	private String[] names;

	public PatternModel(String[] names) {
		this.names = names;
	}

	public String getName(int i) {
		return names[i];
	}

	public String getInitials(int i) {

		return "" + names[i].charAt(0);
	}
}
