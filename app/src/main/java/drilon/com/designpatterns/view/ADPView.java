package drilon.com.designpatterns.view;

import com.hannesdorfmann.mosby.mvp.MvpView;

public interface ADPView extends MvpView {
	void showDialog(String description);
}
