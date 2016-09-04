package drilon.com.designpatterns.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import drilon.com.designpatterns.R;
import drilon.com.designpatterns.adapter.AdapterPoolAdapter;
import drilon.com.designpatterns.presenter.AdapterPoolPresenter;
import drilon.com.designpatterns.view.AdapterPoolView;

public class AdapterPoolActivity extends MvpActivity<AdapterPoolView, AdapterPoolPresenter> implements AdapterPoolView {

	@BindView(R.id.toolbar)
	Toolbar toolbar;
	@BindView(R.id.recyclerView)
	RecyclerView recyclerView;

	AdapterPoolAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adapter_pool);
		ButterKnife.bind(this);
		setSupportActionBar(toolbar);
		toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		setupRecycler();
	}

	@NonNull
	@Override
	public AdapterPoolPresenter createPresenter() {
		return new AdapterPoolPresenter(this);
	}

	public void setupRecycler() {
		adapter = new AdapterPoolAdapter();
		recyclerView.setAdapter(adapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		adapter.addAll(presenter.getData());
	}

}
