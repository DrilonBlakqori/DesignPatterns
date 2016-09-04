package drilon.com.designpatterns.model;

public class PatternModel {

	String name;

	public PatternModel(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getInitials() {
		String[] names = name.split("&");
		String initials = "";
		for (String name : names) {
			initials += name.trim().charAt(0);
		}
		return initials;
	}
}
