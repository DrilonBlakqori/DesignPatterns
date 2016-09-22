package drilon.com.designpatterns.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import drilon.com.designpatterns.R;

public class DescriptionDialogFragment extends DialogFragment {

	public static final String DIALOG_TEXT = "dialog_text";

	@BindView(R.id.text)
	TextView textView;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.description_dialog, container, false);
		ButterKnife.bind(this, view);
		textView.setText(getArguments().getString(DIALOG_TEXT));
		return view;
	}

	public static DescriptionDialogFragment getInstance(String description) {
		DescriptionDialogFragment descriptionDialogFragment = new DescriptionDialogFragment();
		Bundle bundle = new Bundle();
		bundle.putString(DIALOG_TEXT, description);
		descriptionDialogFragment.setArguments(bundle);
		return descriptionDialogFragment;
	}
}
