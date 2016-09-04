package drilon.com.designpatterns.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import drilon.com.designpatterns.R;
import drilon.com.designpatterns.adapter.MainAdapter;
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

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		setSupportActionBar(toolbar);
		toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
		setupRecycler();
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
}
