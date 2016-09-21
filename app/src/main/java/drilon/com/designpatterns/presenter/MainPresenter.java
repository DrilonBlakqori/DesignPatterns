package drilon.com.designpatterns.presenter;

import android.content.Context;
import android.content.Intent;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.ArrayList;

import drilon.com.designpatterns.activities.APDActivity;
import drilon.com.designpatterns.activities.BSCActivity;
import drilon.com.designpatterns.model.PatternModel;
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

	public ArrayList<PatternModel> generateData() {
		ArrayList<PatternModel> items = new ArrayList<>();
		items.add(new PatternModel(new String[]{"Builder" , "Command" , "Singleton"}));
		items.add(new PatternModel(new String[]{"Adapter" , "Pool" , "Decorator"}));
		return items;
	}
}
