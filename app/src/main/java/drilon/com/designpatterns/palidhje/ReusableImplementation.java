package drilon.com.designpatterns.palidhje;

import android.graphics.Color;
import android.view.View;

public class ReusableImplementation implements Reusable {

	//atributet e pandryshueshme
	private Color color;
	private View view;
	// atributet e ndryshueshme
	private String name;
	private int viewCount;

	@Override
	public void recycle() {
		name = null;
		viewCount = -1;
	}
}
