package drilon.com.designpatterns.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import drilon.com.designpatterns.R;
import drilon.com.designpatterns.adapter.MainAdapter;
import drilon.com.designpatterns.command.Commander;
import drilon.com.designpatterns.command.OrderReceivedEvent;
import drilon.com.designpatterns.dialog.DescriptionDialogFragment;
import drilon.com.designpatterns.model.Burger;
import drilon.com.designpatterns.presenter.MainPresenter;
import drilon.com.designpatterns.view.MainView;

public class MainActivity extends MvpActivity<MainView, MainPresenter> implements MainView {

	@BindView(R.id.toolbar)
	Toolbar toolbar;
	@BindView(R.id.mainRecycler)
	RecyclerView recyclerView;
	@BindView(R.id.fab)
	FloatingActionButton fab;

	MainAdapter adapter;
	private Burger burger;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		setupRecycler();
		Commander.getInstance().subscribe(this, OrderReceivedEvent.class);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Commander.getInstance().unSubscribe(this, OrderReceivedEvent.class);
	}

	@NonNull
	@Override
	public MainPresenter createPresenter() {
		return new MainPresenter(this);
	}

	@Override
	public void onItemClick(int position) {
		presenter.onItemClicked(position);
	}

	public void setupRecycler() {
		adapter = new MainAdapter(this);
		recyclerView.setAdapter(adapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		adapter.addAll(presenter.generateData());
	}

	@OnClick(R.id.fab)
	public void fabClicked() {
		presenter.fabClicked();
	}

	public void onEvent(OrderReceivedEvent event) {
		burger = event.burger;
		adapter.enableCheckBox(true);
	}

	public void orderChecked() {
		presenter.orderChecked(burger);
		burger = null;
	}

	@Override
	public void showMessage(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void showDialog(String description) {
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		Fragment prev = getFragmentManager().findFragmentByTag("dialog");
		if (prev != null) {
			ft.remove(prev);
		}
		ft.addToBackStack(null);

		// Create and show the dialog.
		DescriptionDialogFragment newFragment = DescriptionDialogFragment.getInstance(description);
		newFragment.show(ft, "dialog");
	}
}
