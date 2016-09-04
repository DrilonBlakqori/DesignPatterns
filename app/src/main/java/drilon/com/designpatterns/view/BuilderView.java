package drilon.com.designpatterns.view;

import com.hannesdorfmann.mosby.mvp.MvpView;

public interface BuilderView extends MvpView {

	boolean isSauceChecked();

	boolean isCucumberChecked();

	boolean isTomatoChecked();

	boolean isCheeseChecked();

	boolean isSaladChecked();

	void showMessage(String message);
}
