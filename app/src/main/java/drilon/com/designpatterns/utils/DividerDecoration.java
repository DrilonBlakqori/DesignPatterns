package drilon.com.designpatterns.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import drilon.com.designpatterns.R;

public class DividerDecoration extends RecyclerView.ItemDecoration {

	private Drawable divider;
	private int dividerHeight;
	private int sideMargin;

	public DividerDecoration(Context context) {
		divider = new ColorDrawable(ContextCompat
			.getColor(context, R.color.divider_color));
		dividerHeight = context.getResources()
			.getDimensionPixelSize(R.dimen.divider_height);
		sideMargin = context.getResources()
			.getDimensionPixelSize(R.dimen.divider_side_margin);
	}

	@Override
	public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
		int left = parent.getPaddingLeft() + sideMargin;
		int right = parent.getWidth() - parent.getPaddingRight() - sideMargin;

		int childCount = parent.getChildCount();
		for (int i = 0; i < childCount; i++) {
			View child = parent.getChildAt(i);

			RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)
				child.getLayoutParams();

			int top = child.getBottom() + params.bottomMargin;
			int bottom = top + dividerHeight;

			divider.setBounds(left, top, right, bottom);
			divider.draw(c);
		}
	}
}
