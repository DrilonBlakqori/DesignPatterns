package drilon.com.designpatterns.presenter;

import android.content.Context;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import drilon.com.designpatterns.R;
import drilon.com.designpatterns.command.Commander;
import drilon.com.designpatterns.command.OrderReceivedEvent;
import drilon.com.designpatterns.model.Burger;
import drilon.com.designpatterns.utils.Utils;
import drilon.com.designpatterns.view.BSCView;

public class BSCPresenter extends MvpBasePresenter<BSCView> {

	private int type;
	private Context context;

	public BSCPresenter(Context context) {
		this.context = context;
	}

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
			getView().showMessage(Utils.generateBurgerMessage(Utils.TYPE_BUILD, burger));
			Commander.getInstance().post(new OrderReceivedEvent(burger));
		}
	}

	public void fabClicked() {
		if (isViewAttached()) {
			//noinspection ConstantConditions
			getView().showDialog(context.getString(R.string.bsc_description));
		}
	}

}

