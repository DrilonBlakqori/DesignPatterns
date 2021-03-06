package drilon.com.designpatterns.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import drilon.com.designpatterns.R;
import drilon.com.designpatterns.dialog.DescriptionDialogFragment;
import drilon.com.designpatterns.presenter.BSCPresenter;
import drilon.com.designpatterns.view.BSCView;

public class BSCActivity extends MvpActivity<BSCView, BSCPresenter> implements BSCView {

	@BindView(R.id.toolbar)
	Toolbar toolbar;
	@BindView(R.id.sauceCheck)
	AppCompatCheckBox sauceCheck;
	@BindView(R.id.cheeseCheck)
	AppCompatCheckBox cheeseCheck;
	@BindView(R.id.tomatoCheck)
	AppCompatCheckBox tomatoCheck;
	@BindView(R.id.cucumbercheck)
	AppCompatCheckBox cucumberCheck;
	@BindView(R.id.saladCheck)
	AppCompatCheckBox saladCheck;
	@BindView(R.id.burgerSpinner)
	AppCompatSpinner burgerSpinner;

	@OnClick(R.id.buildButton)
	public void onBuildClicked() {
		presenter.onBuildClicked();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bsc);
		ButterKnife.bind(this);
		setSupportActionBar(toolbar);
		toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		setupSpinner();
	}

	private void setupSpinner() {
		ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
			R.array.burger_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		burgerSpinner.setAdapter(adapter);
		burgerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				presenter.onSpinnerItemSelected(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
	}

	@OnClick(R.id.fab)
	public void fabClicked() {
		presenter.fabClicked();
	}

	@NonNull
	@Override
	public BSCPresenter createPresenter() {
		return new BSCPresenter(this);
	}

	@Override
	public boolean isSauceChecked() {
		return sauceCheck.isChecked();
	}

	@Override
	public boolean isCucumberChecked() {
		return cucumberCheck.isChecked();
	}

	@Override
	public boolean isTomatoChecked() {
		return tomatoCheck.isChecked();
	}

	@Override
	public boolean isCheeseChecked() {
		return cheeseCheck.isChecked();
	}

	@Override
	public boolean isSaladChecked() {
		return saladCheck.isChecked();
	}

	@Override
	public void showMessage(String message) {
		Toast.makeText(BSCActivity.this, message, Toast.LENGTH_LONG).show();
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
