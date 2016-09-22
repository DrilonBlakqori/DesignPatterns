package drilon.com.designpatterns.utils;

import drilon.com.designpatterns.model.Burger;

public class Utils {

	public static final int TYPE_BUILD = 0;
	public static final int TYPE_CHECK = 1;

	public static String generateBurgerMessage(int type, Burger burger) {
		String message = "";
		switch (type) {
			case TYPE_BUILD:
				message = "You have built a ";
				break;
			case TYPE_CHECK:
				message = "You have checked the order: ";
				break;
		}
		message += burger.getName();

		if (burger.getSauce() != null && !burger.getSauce().equals("")) {
			message += "(with free " + burger.getSauce() + ")";
		}
		boolean first = true;
		if (burger.hasCheese()) {
			message += " with: cheese";
			first = false;
		}
		if (burger.hasSalad()) {
			if (first) {
				message += " with: salad";
				first = false;
			} else {
				message += ", salad";
			}
		}
		if (burger.hasCucumber()) {
			if (first) {
				message += " with: cucumber";
				first = false;
			} else {
				message += ", cucumber";
			}
		}
		if (burger.hasTomato()) {
			if (first) {
				message += " with: tomato";
			} else {
				message += ", tomato";
			}
		}
		message += ".";
		return message;
	}
}
