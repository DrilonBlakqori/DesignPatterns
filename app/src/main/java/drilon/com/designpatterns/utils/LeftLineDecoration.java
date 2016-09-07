package drilon.com.designpatterns.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import drilon.com.designpatterns.R;

public class LeftLineDecoration extends RecyclerView.ItemDecoration {

	private Drawable divider;
	private int lineWidth;
	private int horizontalMargin;
	private int verticalMargin;

	public LeftLineDecoration(Context context) {
		divider = new ColorDrawable(ContextCompat.getColor(context, R.color.colorAccent));
		lineWidth = context.getResources().getDimensionPixelSize(R.dimen.left_line_width);
		horizontalMargin = context.getResources().getDimensionPixelSize(R.dimen.left_line_horizontal_margin);
		verticalMargin = context.getResources().getDimensionPixelSize(R.dimen.left_line_vertical_margin);
	}

	@Override
	public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
		int left = parent.getLeft() + horizontalMargin;
		int right = left + lineWidth;

		int childCount = parent.getChildCount();
		for (int i = 0; i < childCount; i++) {
			View child = parent.getChildAt(i);

			RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

			int top = child.getTop() - params.topMargin + verticalMargin;
			int bottom = child.getBottom() + params.bottomMargin - verticalMargin;

			divider.setBounds(left, top, right, bottom);
			divider.draw(c);
		}
	}
}
