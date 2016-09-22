package drilon.com.designpatterns.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
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
import butterknife.OnClick;
import drilon.com.designpatterns.R;
import drilon.com.designpatterns.adapter.ADPAdapter;
import drilon.com.designpatterns.dialog.DescriptionDialogFragment;
import drilon.com.designpatterns.presenter.ADPPresenter;
import drilon.com.designpatterns.utils.DividerDecoration;
import drilon.com.designpatterns.utils.LeftLineDecoration;
import drilon.com.designpatterns.view.ADPView;

public class APDActivity extends MvpActivity<ADPView, ADPPresenter> implements ADPView {

	@BindView(R.id.toolbar)
	Toolbar toolbar;
	@BindView(R.id.recyclerView)
	RecyclerView recyclerView;
	@BindView(R.id.dividerCheckBox)
	AppCompatCheckBox dividerCheckBox;
	@BindView(R.id.leftLinesCheckBox)
	AppCompatCheckBox leftLinesCheckBox;

	private ADPAdapter adapter;
	private DividerDecoration dividerDecoration;
	private LeftLineDecoration leftLineDecoration;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adp);
		ButterKnife.bind(this);
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
	public ADPPresenter createPresenter() {
		return new ADPPresenter(this);
	}

	public void setupRecycler() {
		adapter = new ADPAdapter();
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

	@OnClick(R.id.fab)
	public void fabClicked() {
		presenter.fabClicked();
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
