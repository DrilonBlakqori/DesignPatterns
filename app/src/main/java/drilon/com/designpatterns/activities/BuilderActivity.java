package drilon.com.designpatterns.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.RecyclerView;
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
import drilon.com.designpatterns.model.Burger;
import drilon.com.designpatterns.presenter.BuilderPresenter;
import drilon.com.designpatterns.view.BuilderView;

public class BuilderActivity extends MvpActivity<BuilderView, BuilderPresenter> implements BuilderView {

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
		setContentView(R.layout.activity_builder);
		ButterKnife.bind(this);
		setSupportActionBar(toolbar);
		toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
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

	@NonNull
	@Override
	public BuilderPresenter createPresenter() {
		return new BuilderPresenter();
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
		Toast.makeText(BuilderActivity.this, message, Toast.LENGTH_LONG).show();
	}
}
