package drilon.com.designpatterns.presenter;

import android.content.Context;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.ArrayList;

import drilon.com.designpatterns.R;
import drilon.com.designpatterns.model.SimpleRecyclerModel;
import drilon.com.designpatterns.view.ADPView;

public class ADPPresenter extends MvpBasePresenter<ADPView> {

	Context context;

	public ADPPresenter(Context context) {
		this.context = context;
	}

	public ArrayList<SimpleRecyclerModel> getData() {
		ArrayList<SimpleRecyclerModel> list = new ArrayList<>();
		for (int i = 0; i < 500; i++) {
			list.add(new SimpleRecyclerModel("Item " + i));
		}

		return list;
	}

	public void fabClicked() {
		if (isViewAttached()) {
			//noinspection ConstantConditions
			getView().showDialog(context.getString(R.string.adp_description));
		}
	}
}
