package drilon.com.designpatterns.model;

public class Burger {

	public static final int CHICKEN = 0;
	public static final int BEEF = 1;
	public static final int FISH = 2;

	private final String name;
	private final String sauce;
	private boolean cheese;
	private boolean tomato;
	private boolean cucumber;
	private boolean salad;

	private Burger(int burgerType, boolean sauce) {
		switch (burgerType) {
			case CHICKEN:
				name = "Chicken Burger";
				this.sauce = sauce ? "Mushroom sauce" : "";
				break;
			case BEEF:
				name = "Beef Burger";
				this.sauce = sauce ? "Spicy sauce" : "";
				break;
			case FISH:
				name = "Fish Burger";
				this.sauce = sauce ? "Lemon Sauce" : "";
				break;
			default:
				name = "";
				this.sauce = "";
				break;
		}
	}

	public static class BurgerBuilder {

		private Burger burger;

		public BurgerBuilder(int burgerType, boolean sauce) {
			burger = new Burger(burgerType, sauce);
		}

		public BurgerBuilder setCheese(boolean cheese) {
			burger.cheese = cheese;
			return this;
		}

		public BurgerBuilder setTomato(boolean tomato) {
			burger.tomato = tomato;
			return this;
		}

		public BurgerBuilder setCucumber(boolean cucumber) {
			burger.cucumber = cucumber;
			return this;
		}

		public BurgerBuilder setSalad(boolean salad) {
			burger.salad = salad;
			return this;
		}

		public Burger build() {
			return burger;
		}
	}

	public String getName() {
		return name;
	}

	public boolean hasCheese() {
		return cheese;
	}

	public boolean hasTomato() {
		return tomato;
	}

	public boolean hasCucumber() {
		return cucumber;
	}

	public boolean hasSalad() {
		return salad;
	}

	public String getSauce() {
		return sauce;
	}
}
