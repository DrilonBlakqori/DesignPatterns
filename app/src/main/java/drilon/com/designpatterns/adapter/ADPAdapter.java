package drilon.com.designpatterns.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import drilon.com.designpatterns.R;
import drilon.com.designpatterns.model.SimpleRecyclerModel;

public class ADPAdapter extends RecyclerView.Adapter<ADPAdapter.AdapterPoolViewHolder> {

	private ArrayList<SimpleRecyclerModel> items;

	public ADPAdapter() {
		items = new ArrayList<>();
	}

	@Override
	public AdapterPoolViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adapter_pool, parent, false);

		return new AdapterPoolViewHolder(view);
	}

	@Override
	public void onBindViewHolder(AdapterPoolViewHolder holder, int position) {
		holder.title.setText(items.get(position).getTitle());
	}

	@Override
	public int getItemCount() {
		return items.size();
	}

	public void addAll(List<SimpleRecyclerModel> newItems) {
		items.clear();
		items.addAll(newItems);
		notifyDataSetChanged();
	}

	class AdapterPoolViewHolder extends RecyclerView.ViewHolder {

		@BindView(R.id.title)
		TextView title;

		public AdapterPoolViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}
	}
}
