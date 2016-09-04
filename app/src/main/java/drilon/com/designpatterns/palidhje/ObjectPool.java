package drilon.com.designpatterns.palidhje;

import android.support.v4.util.Pools;

public class ObjectPool {

	private static final Pools.SimplePool<Reusable> POOL =
		new Pools.SimplePool<>(20);

	public static Reusable get() {
		return POOL.acquire();
	}

	public static void release(Reusable reusable) {
		reusable.recycle();
		POOL.release(reusable);
	}
}
