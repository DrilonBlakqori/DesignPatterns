package drilon.com.designpatterns.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import drilon.com.designpatterns.R;
import drilon.com.designpatterns.activities.MainActivity;
import drilon.com.designpatterns.model.PatternModel;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

	private ArrayList<PatternModel> items;
	private MainActivity mainActivity;

	public MainAdapter(MainActivity mainActivity) {

		this.mainActivity = mainActivity;
		items = new ArrayList<>();
	}

	@Override
	public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		View view = LayoutInflater.from(mainActivity).inflate(R.layout.item_main, parent, false);

		return new MainViewHolder(view);
	}

	@Override
	public void onBindViewHolder(final MainViewHolder holder, int position) {
		holder.icon.setText(items.get(position).getInitials());
		holder.text.setText(items.get(position).getName());
		holder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mainActivity.onItemClick(holder.getAdapterPosition());
			}
		});
	}


	@Override
	public int getItemCount() {
		return items.size();
	}

	public void addAll(ArrayList<PatternModel> newItems) {
		items.clear();
		items.addAll(newItems);
		notifyDataSetChanged();
	}

	class MainViewHolder extends RecyclerView.ViewHolder {

		@BindView(R.id.icon)
		TextView icon;
		@BindView(R.id.text)
		TextView text;

		public MainViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}
	}
}
