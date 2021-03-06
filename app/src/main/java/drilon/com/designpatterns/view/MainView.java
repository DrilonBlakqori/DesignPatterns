package drilon.com.designpatterns.view;

import com.hannesdorfmann.mosby.mvp.MvpView;

public interface MainView extends MvpView {
	void onItemClick(int position);

	void showMessage(String message);

	void showDialog(String description);
}
