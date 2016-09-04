package drilon.com.designpatterns.presenter;

import android.content.Context;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.ArrayList;

import drilon.com.designpatterns.model.SimpleRecyclerModel;
import drilon.com.designpatterns.view.AdapterPoolView;

public class AdapterPoolPresenter extends MvpBasePresenter<AdapterPoolView> {

	Context context;

	public AdapterPoolPresenter(Context context) {
		this.context = context;
	}

	public ArrayList<SimpleRecyclerModel> getData() {
		ArrayList<SimpleRecyclerModel> list = new ArrayList<>();
		for (int i = 0; i < 500; i++) {
			list.add(new SimpleRecyclerModel("Item " + i));
		}

		return list;
	}
}
