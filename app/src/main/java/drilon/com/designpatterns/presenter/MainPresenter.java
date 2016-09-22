package drilon.com.designpatterns.presenter;

import android.content.Context;
import android.content.Intent;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.ArrayList;

import drilon.com.designpatterns.R;
import drilon.com.designpatterns.activity.APDActivity;
import drilon.com.designpatterns.activity.BSCActivity;
import drilon.com.designpatterns.model.Burger;
import drilon.com.designpatterns.model.PatternModel;
import drilon.com.designpatterns.utils.Utils;
import drilon.com.designpatterns.view.MainView;

public class MainPresenter extends MvpBasePresenter<MainView> {

	private Context context;

	public MainPresenter(Context context) {
		this.context = context;
	}

	public void onItemClicked(int position) {
		switch (position) {
			case 0:
				context.startActivity(new Intent(context, BSCActivity.class));
				break;
			case 1:
				context.startActivity(new Intent(context, APDActivity.class));
				break;
		}
	}

	public void fabClicked() {
		if (isViewAttached()) {
			//noinspection ConstantConditions
			getView().showDialog(context.getString(R.string.main_description));
		}
	}

	public ArrayList<PatternModel> generateData() {
		ArrayList<PatternModel> items = new ArrayList<>();
		items.add(new PatternModel(new String[]{"Builder" , "Command" , "Singleton"}));
		items.add(new PatternModel(new String[]{"Adapter" , "Pool" , "Decorator"}));
		return items;
	}

	public void orderChecked(Burger burger) {
		if (burger != null) {
			if (isViewAttached()) {
				//noinspection ConstantConditions
				getView().showMessage(Utils.generateBurgerMessage(Utils.TYPE_CHECK, burger));
			}
		}
	}
}
