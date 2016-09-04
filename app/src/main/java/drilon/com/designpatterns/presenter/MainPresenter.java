package drilon.com.designpatterns.presenter;

import android.content.Context;
import android.content.Intent;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.ArrayList;

import drilon.com.designpatterns.activities.AdapterPoolActivity;
import drilon.com.designpatterns.activities.BuilderActivity;
import drilon.com.designpatterns.model.PatternModel;
import drilon.com.designpatterns.view.MainView;

public class MainPresenter extends MvpBasePresenter<MainView> {

	Context context;

	public MainPresenter(Context context) {
		this.context = context;
	}

	public void onItemClicked(int position) {
		switch (position) {
			case 0:
				context.startActivity(new Intent(context, BuilderActivity.class));
				break;
			case 1:
				context.startActivity(new Intent(context, BuilderActivity.class));
				break;
			case 2:
				context.startActivity(new Intent(context, AdapterPoolActivity.class));
				break;
			case 3:
				break;
			case 4:
				context.startActivity(new Intent(context, BuilderActivity.class));
				break;
			case 5:
				context.startActivity(new Intent(context, BuilderActivity.class));
				break;
			case 6:
				context.startActivity(new Intent(context, BuilderActivity.class));
				break;
		}
	}

	public ArrayList<PatternModel> generateData() {
		ArrayList<PatternModel> items = new ArrayList<>();
		items.add(new PatternModel("Builder"));
		items.add(new PatternModel("Singleton"));
		items.add(new PatternModel("Adapter & Pool"));
		items.add(new PatternModel("Decorator"));
		items.add(new PatternModel("Command"));
		items.add(new PatternModel("Iterator"));
		return items;
	}
}
