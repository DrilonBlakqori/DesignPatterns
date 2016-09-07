package drilon.com.designpatterns.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import drilon.com.designpatterns.R;
import drilon.com.designpatterns.adapter.AdapterPoolAdapter;
import drilon.com.designpatterns.presenter.AdapterPoolDecoratorPresenter;
import drilon.com.designpatterns.utils.DividerDecoration;
import drilon.com.designpatterns.utils.LeftLineDecoration;
import drilon.com.designpatterns.view.AdapterPoolDecoratorView;

public class AdapterPoolDecoratorActivity extends MvpActivity<AdapterPoolDecoratorView, AdapterPoolDecoratorPresenter> implements AdapterPoolDecoratorView {

	@BindView(R.id.toolbar)
	Toolbar toolbar;
	@BindView(R.id.recyclerView)
	RecyclerView recyclerView;
	@BindView(R.id.dividerCheckBox)
	AppCompatCheckBox dividerCheckBox;
	@BindView(R.id.leftLinesCheckBox)
	AppCompatCheckBox leftLinesCheckBox;

	private AdapterPoolAdapter adapter;
	private DividerDecoration dividerDecoration;
	private LeftLineDecoration leftLineDecoration;

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
		setupDecoratorViews();
	}

	@NonNull
	@Override
	public AdapterPoolDecoratorPresenter createPresenter() {
		return new AdapterPoolDecoratorPresenter(this);
	}

	public void setupRecycler() {
		adapter = new AdapterPoolAdapter();
		recyclerView.setAdapter(adapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		adapter.addAll(presenter.getData());
	}

	private void setupDecoratorViews() {
		dividerDecoration = new DividerDecoration(this);
		leftLineDecoration = new LeftLineDecoration(this);
		dividerCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					recyclerView.addItemDecoration(dividerDecoration);
				} else {
					recyclerView.removeItemDecoration(dividerDecoration);
				}
				adapter.notifyDataSetChanged();
			}
		});
		leftLinesCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					recyclerView.addItemDecoration(leftLineDecoration);
				} else {
					recyclerView.removeItemDecoration(leftLineDecoration);
				}
				adapter.notifyDataSetChanged();
			}
		});
	}

}
