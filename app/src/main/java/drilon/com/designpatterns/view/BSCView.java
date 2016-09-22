package drilon.com.designpatterns.view;

import com.hannesdorfmann.mosby.mvp.MvpView;

public interface BSCView extends MvpView {

	boolean isSauceChecked();

	boolean isCucumberChecked();

	boolean isTomatoChecked();

	boolean isCheeseChecked();

	boolean isSaladChecked();

	void showMessage(String message);

	void showDialog(String description);
}
