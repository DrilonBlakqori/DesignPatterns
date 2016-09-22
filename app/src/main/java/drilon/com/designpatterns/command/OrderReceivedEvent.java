package drilon.com.designpatterns.command;

import drilon.com.designpatterns.model.Burger;

public class OrderReceivedEvent {

	public Burger burger;

	public OrderReceivedEvent(Burger burger) {
		this.burger = burger;
	}
}
