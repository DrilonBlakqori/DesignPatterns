package drilon.com.designpatterns.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import drilon.com.designpatterns.model.Burger;
import drilon.com.designpatterns.view.BuilderView;

public class BuilderPresenter extends MvpBasePresenter<BuilderView> {

	private int type;

	public void onSpinnerItemSelected(int position) {
		switch (position) {
			case 0:
				type = Burger.CHICKEN;
				break;
			case 1:
				type = Burger.BEEF;
				break;
			case 2:
				type = Burger.FISH;
				break;
		}
	}

	public void onBuildClicked() {
		if (isViewAttached()) {
			@SuppressWarnings("ConstantConditions") Burger burger = new Burger.BurgerBuilder(type, getView().isSauceChecked())
				.setCheese(getView().isCheeseChecked())
				.setCucumber(getView().isCucumberChecked())
				.setSalad(getView().isSaladChecked())
				.setTomato(getView().isTomatoChecked())
				.build();
			getView().showMessage(generateBurgerMessage(burger));
		}
	}

	private String generateBurgerMessage(Burger burger) {
		String message = "You have built a " + burger.getName();

		if (burger.getSauce() != null) {
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

